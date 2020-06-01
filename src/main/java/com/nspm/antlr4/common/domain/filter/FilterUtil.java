package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.obj.IPItemPO;
import com.nspm.antlr4.common.enums.FilterRuleActionEnum;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.utils.ObjectUtil;
import com.nspm.antlr4.common.utils.TwoMemberObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zc
 * @date 2019/09/26
 */
public class FilterUtil {
    private static Logger logger = LoggerFactory.getLogger(FilterUtil.class);

    public FilterUtil() {
    }

    public static void switchSrcDstIP(FilterRuleListPO ruleList) {
        if (ruleList != null) {
            List<FilterRulePO> itemList = ruleList.getRuleList();
            if (itemList != null && !itemList.isEmpty()) {
                Iterator var2 = itemList.iterator();

                while(var2.hasNext()) {
                    FilterRulePO myRule = (FilterRulePO) var2.next();
                    MatchClausePO matchClause = myRule.getMatchClause();
                    if (matchClause != null) {
                        IPItemPO[] srcItems = matchClause.getSrcIp();
                        IPItemPO[] dstItems = matchClause.getDstIp();
                        matchClause.setSrcIp(dstItems);
                        matchClause.setDstIp(srcItems);
                    }
                }

            }
        }
    }

    public static FilterRulePO getImplicitDenyAll(IPTypeEnum ipType, String name) {
        if (name == null) {
            name = "System implicit deny rule";
        }

        MatchClausePO matchClause = new MatchClausePO();
        IPItemPO any = null;
        if (ipType == IPTypeEnum.IP4) {
            any = IPItemPO.IP4_ANY;
        } else if (ipType == IPTypeEnum.IP6) {
            any = IPItemPO.IP6_ANY;
        } else if (ipType == null) {
            any = IPItemPO.createAnyType();
        }

        matchClause.setSrcIp(new IPItemPO[]{any});
        matchClause.setDstIp(new IPItemPO[]{any});
        FilterRulePO result = new FilterRulePO(ipType, name,null,null, null, null, null, null, matchClause, null, FilterRuleActionEnum.DENY, null);
        result.setImplicit(true);
        return result;
    }

    public static FilterRulePO getImplicitPermitAll(IPTypeEnum ipType, String name) {
        if (name == null) {
            name = "System implicit permit rule";
        }

        MatchClausePO matchClause = new MatchClausePO();
        IPItemPO any = null;
        if (ipType == IPTypeEnum.IP4) {
            any = IPItemPO.IP4_ANY;
        } else if (ipType == IPTypeEnum.IP6) {
            any = IPItemPO.IP6_ANY;
        } else if (ipType == null) {
            any = IPItemPO.createAnyType();
        }

        matchClause.setSrcIp(new IPItemPO[]{any});
        matchClause.setDstIp(new IPItemPO[]{any});
        FilterRulePO result = new FilterRulePO(ipType, name, null, null, null, null, null, null, matchClause, null, FilterRuleActionEnum.PERMIT, null);
        result.setImplicit(true);
        return result;
    }

    public static FilterRulePO getReverseNATRule(FilterRulePO natRule, String deviceName) {
        if (natRule == null) {
            return null;
        } else {
            NATClausePO natClause = natRule.getNatClause();
            if (natClause == null) {
                return null;
            } else if (natClause.isReverse()) {
                throw new IllegalArgumentException("Cannot create reverse NAT of a reverse NAT.");
            } else {
                NATClausePO reverseNat = natClause.shallowCopy();
                reverseNat.setReverse(true);
                NATClausePO.NATField beforenf = natClause.getNatField();
                NATClausePO.NATField afternf = null;
                switch(beforenf) {
                    case BI_DIR_DST:
                        afternf = NATClausePO.NATField.BI_DIR_SRC;
                        break;
                    case BI_DIR_SRC:
                        afternf = NATClausePO.NATField.BI_DIR_DST;
                        break;
                    case BOTH:
                        afternf = NATClausePO.NATField.BOTH;
                        break;
                    case BOTH_DST:
                        afternf = NATClausePO.NATField.BOTH_SRC;
                        break;
                    case BOTH_SRC:
                        afternf = NATClausePO.NATField.BOTH_DST;
                        break;
                    case DST:
                        afternf = NATClausePO.NATField.SRC;
                        break;
                    case SRC:
                        afternf = NATClausePO.NATField.DST;
                        break;
                    case UNKNOWN_IDENTITY:
                        afternf = NATClausePO.NATField.UNKNOWN_IDENTITY;
                        break;
                    default:
                        afternf = beforenf;
                }

                reverseNat.setNatField(afternf);
                FilterRulePO myRule = natRule.shallowCopy();
                myRule.setNatClause(reverseNat);
                myRule.setInInterfaceGroupRefs(natRule.getOutInterfaceGroupRefs());
                myRule.setOutInterfaceGroupRefs(natRule.getInInterfaceGroupRefs());
                if (myRule.getUuid() != null) {
                    if (natRule.getOriginUuid() != null) {
                        myRule.setOriginUuid(natRule.getOriginUuid());
                        myRule.setOriginVersion(natRule.getOriginVersion());
                    } else {
                        myRule.setOriginUuid(natRule.getUuid());
//                        myRule.setOriginVersion(natRule.getVersion());
                    }

                    myRule.setUuid(ObjectUtil.getUUID());
                }

                myRule.setOtherAction(null);
                if (natRule.getMatchClause() != null) {
                    logger.warn("Reverse NAT rule should not be done on rule with match clause. Device: " + deviceName + ". Line:" + natRule.getLineNumbers());
                }

                return myRule;
            }
        }
    }

    public static TwoMemberObject<List<FilterRulePO>, List<FilterRulePO>> splitSrcDstNAT(List<FilterRulePO> natRules, boolean srcFirst) {
        if (natRules != null && !natRules.isEmpty()) {
            List<FilterRulePO> srcList = new ArrayList<>(natRules.size());
            List<FilterRulePO> dstList = new ArrayList<>(natRules.size());
            for (FilterRulePO myRule : natRules) {
                NATClausePO myClause = myRule.getNatClause();
                if (myClause == null) {
                    throw new IllegalArgumentException("Splitting NAT is only supported for NAT only list.");
                }

                boolean hasSrcNat = false;
                boolean hasDstNat = false;
                if (!ObjectUtil.isNullOrEmpty(myClause.getPostSrcIPItems()) || !ObjectUtil.isNullOrEmpty(myClause.getPostSrcPortSpecAsList())) {
                    if (!myClause.isReverse()) {
                        hasSrcNat = true;
                    } else {
                        hasDstNat = true;
                    }
                }

                if (!ObjectUtil.isNullOrEmpty(myClause.getPostDstIPItemsAsList()) || !ObjectUtil.isNullOrEmpty(myClause.getPostDstPortSpecAsList()) || myClause.getPostServices() != null && myClause.getPostServices().length > 0) {
                    if (!myClause.isReverse()) {
                        hasDstNat = true;
                    } else {
                        hasSrcNat = true;
                    }
                }

                if (hasSrcNat || hasDstNat) {
                    if (!hasSrcNat || hasDstNat) {
                        if (hasSrcNat) {
                            throw new UnsupportedOperationException("Not implemented yet.");
                        }
                        dstList.add(myRule);
                    } else {
                        srcList.add(myRule);
                    }
                } else {
                    logger.warn("Found a NAT rule without any postNAT values. Skip it. Line: {}", myRule.getLineNumbers());
                }
            }
            TwoMemberObject result;
            if (srcFirst) {
                result = new TwoMemberObject<>(srcList, dstList);
            } else {
                result = new TwoMemberObject<>(dstList, srcList);
            }
            return result;
        } else {
            return null;
        }
    }
}

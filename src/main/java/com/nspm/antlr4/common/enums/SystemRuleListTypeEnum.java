package com.nspm.antlr4.common.enums;

import com.nspm.antlr4.common.domain.filter.RuleListTypePO;

import java.util.EnumSet;

/**
 * @author luwei
 * @date 2019/9/11
 */
public enum SystemRuleListTypeEnum {

    /**
     * 路由
     */
    SYSTEM__ROUTING(new RuleListTypePO(true, "SYSTEM__ROUTING", null)),
    /**
     * 入方向访问控制策略
     */
    SYSTEM__INBOUND_INTERFACE_ACL(new RuleListTypePO(true, "SYSTEM__INBOUND_INTERFACE_ACL",
            EnumSet.of(RuleListTypePO.RuleListOption.PERMIT_ALL_AT_THE_END_OF_COMBINED_RULE_LIST))),
    /**
     * 出方向访问控制策略
     */
    SYSTEM__OUTBOUND_INTERFACE_ACL(new RuleListTypePO(true, "SYSTEM__OUTBOUND_INTERFACE_ACL",
            EnumSet.of(RuleListTypePO.RuleListOption.PERMIT_ALL_AT_THE_END_OF_COMBINED_RULE_LIST))),
    /**
     * 策略路由
     */
    SYSTEM__POLICY_ROUTING(new RuleListTypePO(true, "SYSTEM__POLICY_ROUTING",
            EnumSet.of(RuleListTypePO.RuleListOption.PERMIT_ALL_AT_THE_END_OF_COMBINED_RULE_LIST))),
    /**
     * nat策略-路由前匹配
     */
    SYSTEM__PRE_ROUTING_NAT(new RuleListTypePO(true, "SYSTEM__PRE_ROUTING_NAT",
            EnumSet.of(RuleListTypePO.RuleListOption.PERMIT_ALL_AT_THE_END_OF_COMBINED_RULE_LIST))),
    /**
     * nat策略-路由后匹配
     */
    SYSTEM__POST_ROUTING_NAT(new RuleListTypePO(true, "SYSTEM__POST_ROUTING_NAT",
            EnumSet.of(RuleListTypePO.RuleListOption.PERMIT_ALL_AT_THE_END_OF_COMBINED_RULE_LIST))),
    /**
     * 策略一
     */
    SYSTEM__POLICY_1(new RuleListTypePO(true, "SYSTEM__POLICY_1", null)),
    /**
     * 策略二
     */
    SYSTEM__POLICY_2(new RuleListTypePO(true, "SYSTEM__POLICY_2", null)),
    /**
     * nat策略
     */
    SYSTEM__NAT_LIST(new RuleListTypePO(true, "SYSTEM__NAT_LIST", null)),
    /**
     * 通用的访问控制策略
     */
    SYSTEM__GENERIC_ACL(new RuleListTypePO(true, "SYSTEM__GENERIC_ACL", null));

    public static final String PREFIX = "SYSTEM__";

    private RuleListTypePO ruleListType;

    private SystemRuleListTypeEnum(RuleListTypePO mytype) {
        this.ruleListType = mytype;
    }

    public RuleListTypePO getRuleListType() {
        return this.ruleListType;
    }

}

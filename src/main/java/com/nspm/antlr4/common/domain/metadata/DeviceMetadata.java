package com.nspm.antlr4.common.domain.metadata;

import com.nspm.antlr4.common.domain.filter.RuleListTypePO;
import com.nspm.antlr4.common.enums.SystemRuleListTypeEnum;
import com.nspm.antlr4.common.revision.DiffMethod;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

/**
 * @author zc
 * @date 2019/09/17
 */
public class DeviceMetadata {
    @ApiModelProperty("设备名")
    private String name;
    private Map<String, RuleListTypePO> customRuleListTypeMap;
    @ApiModelProperty("策略处理顺序列表")
    private List<String> ruleListProcessingOrder;
    @ApiModelProperty("预定义的服务对象集")
    private DevicePredefinedService predefinedService;
    private DiffMethod diffMethod;
    private String defaultRuleListType;
    private Boolean srcNatRuleMatchOrigPacket;
    private DeviceRulePlanMetadata rulePlanMetadata;

    public DeviceMetadata(String name, List<RuleListTypePO> customRuleListTypes, List<String> ruleProcessingOrder) {
        this.name = name;
        if (customRuleListTypes != null && !customRuleListTypes.isEmpty()) {
            this.customRuleListTypeMap = new HashMap<>();
            Iterator var4 = customRuleListTypes.iterator();

            while(var4.hasNext()) {
                RuleListTypePO myType = (RuleListTypePO)var4.next();
                this.customRuleListTypeMap.put(myType.getName(), myType);
            }

            this.ruleListProcessingOrder = ruleProcessingOrder;
        } else {
            this.customRuleListTypeMap = Collections.emptyMap();
            this.ruleListProcessingOrder = ruleProcessingOrder;
        }

    }

    public String getName() {
        return this.name;
    }

    public Map<String, RuleListTypePO> getCustomRuleListTypeMap() {
        return this.customRuleListTypeMap;
    }

    public List<String> getRuleListProcessingOrder() {
        return this.ruleListProcessingOrder;
    }

    public RuleListTypePO getRuleListType(String typeName) {
        if (typeName.startsWith("SYSTEM__")) {
            try {
                SystemRuleListTypeEnum mytype = SystemRuleListTypeEnum.valueOf(typeName);
                if (mytype != null) {
                    return mytype.getRuleListType();
                }
            } catch (Exception var3) {
            }
        }

        return this.customRuleListTypeMap.get(typeName);
    }

    public DevicePredefinedService getPredefinedService() {
        return this.predefinedService;
    }

    public void setPredefinedService(DevicePredefinedService predefinedService) {
        this.predefinedService = predefinedService;
    }

    public DiffMethod getDiffMethod() {
        return this.diffMethod;
    }

    public void setDiffMethod(DiffMethod diffMethod) {
        this.diffMethod = diffMethod;
    }

    public String getDefaultRuleListType() {
        return this.defaultRuleListType;
    }

    public void setDefaultRuleListType(String defaultRuleListType) {
        this.defaultRuleListType = defaultRuleListType;
    }

    public Boolean getSrcNatRuleMatchOrigPacket() {
        return this.srcNatRuleMatchOrigPacket;
    }

    public boolean isSrcNatRuleMatchOrigPacket() {
        return this.srcNatRuleMatchOrigPacket == null ? false : this.srcNatRuleMatchOrigPacket;
    }

    public void setSrcNatRuleMatchOrigPacket(Boolean srcNatRuleMatchOrigPacket) {
        this.srcNatRuleMatchOrigPacket = srcNatRuleMatchOrigPacket;
    }

    public DeviceRulePlanMetadata getRulePlanMetadata() {
        return this.rulePlanMetadata;
    }

    public void setRulePlanMetadata(DeviceRulePlanMetadata rulePlanMetadata) {
        this.rulePlanMetadata = rulePlanMetadata;
    }
}

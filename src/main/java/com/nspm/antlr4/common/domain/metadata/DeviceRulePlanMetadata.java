package com.nspm.antlr4.common.domain.metadata;

import com.nspm.antlr4.common.domain.obj.DevicePO;

import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author zc
 * @date 2020/03/27
 */
public class DeviceRulePlanMetadata {
    private EnumSet<DeviceRulePlanSupportOption> unsupportedOptions;
    private Integer objectNameMaxLength;
    private LinkedHashSet<String> addOnlyRuleListType;
    private Set<String> supportPlanRuleListTypes;

    public boolean validateSupportRulePlan(DevicePO device) {
        return true;
    }

    public EnumSet<DeviceRulePlanSupportOption> getUnsupportedOptions() {
        return this.unsupportedOptions;
    }

    public void setUnsupportedOptions(EnumSet<DeviceRulePlanSupportOption> unsupportedOptions) {
        this.unsupportedOptions = unsupportedOptions;
    }

    public Integer getObjectNameMaxLength() {
        return this.objectNameMaxLength;
    }

    public void setObjectNameMaxLength(Integer objectNameMaxLength) {
        this.objectNameMaxLength = objectNameMaxLength;
    }

    public LinkedHashSet<String> getAddOnlyRuleListType() {
        return this.addOnlyRuleListType;
    }

    public void setAddOnlyRuleListType(LinkedHashSet<String> addOnlyRuleListType) {
        this.addOnlyRuleListType = addOnlyRuleListType;
    }

    public Set<String> getSupportPlanRuleListTypes() {
        return this.supportPlanRuleListTypes;
    }

    public void setSupportPlanRuleListTypes(Set<String> supportPlanRuleListTypes) {
        this.supportPlanRuleListTypes = supportPlanRuleListTypes;
    }

    public static enum DeviceRulePlanSupportOption {
        RULE_LOCATION_BELOW,
        MULTIPLE_OBJECT_REFERENCE_IN_RULE,
        MULTIPLE_OBJECT_REFERENCE_IN_RULE_ON_ADD,
        RANGE_IN_IP_GROUP,
        IP_ITEM_IN_IP_GROUP,
        SRC_PORT,
        IP_NOT_LIMIT_ZONE,
        ADD_RULE_AFTER_EXISTED,
        RANGE_IP,
        CHINESE_IN_NAME;

        private DeviceRulePlanSupportOption() {
        }
    }
}

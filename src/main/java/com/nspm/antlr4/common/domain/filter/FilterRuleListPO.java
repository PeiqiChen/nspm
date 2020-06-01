package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel("策略集对象-插件解析通用格式")
public class FilterRuleListPO {

    @JsonIgnore
    private String uuid;
    @NotBlank
    @ApiModelProperty("策略集名字")
    private String name;
    @ApiModelProperty("描述")
    private String description;
    private String[] tags;
    @ApiModelProperty("行号")
    private ConfigLinePO lineNumbers;
    @NotBlank
    @ApiModelProperty("策略集详细类型")
    private String ruleListType;

    @ApiModelProperty("策略集的源域")
    private String inInterfaceGroupRef;

    @ApiModelProperty("策略集的目的域，若策略没有域，则将集的域赋值给策略")
    private String outInterfaceGroupRef;

    private Boolean isNatOnlyList;
    @ApiModelProperty("策略集的ip类型")
    private FilterRuleListOption option;
    @ApiModelProperty("策略集大致类型")
    private RuleType ruleType = null;
    @Valid
    @ApiModelProperty("策略集策略信息集")
    private List<FilterRulePO> ruleList;

    public FilterRuleListPO() {
    }

    public enum FilterRuleListOption {
        IP4_ONLY,
        IP6_ONLY,
        IP4_IP6_MIX
    }

    public FilterRuleListPO shallowCopy() {
        FilterRuleListPO result = new FilterRuleListPO(this.uuid, this.name, this.description, this.tags, this.lineNumbers, this.ruleListType, this.inInterfaceGroupRef, this.outInterfaceGroupRef, this.isNatOnlyList, this.ruleList, this.option, this.ruleType);

        return result;
    }


    FilterRuleListPO(String uuid, String name, String description, String[] tags, ConfigLinePO lineNumbers, String ruleListType, String inInterfaceGroupRef, String outInterfaceGroupRef, Boolean isNatOnlyList, List<FilterRulePO> ruleList, FilterRuleListOption option, RuleType type) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.lineNumbers = lineNumbers;
        this.ruleListType = ruleListType;
        this.inInterfaceGroupRef = inInterfaceGroupRef;
        this.outInterfaceGroupRef = outInterfaceGroupRef;
        this.isNatOnlyList = isNatOnlyList;
        this.ruleList = ruleList;
        this.option = option;
        this.ruleType = type;
    }


    public FilterRuleListPO(String name, String description, String[] tags, ConfigLinePO lineNumber, String ruleListType, String inInterfaceGroupRef, String outInterfaceGroupRef, List<FilterRulePO> ruleList) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.lineNumbers = lineNumber;
        this.ruleListType = ruleListType;
        this.inInterfaceGroupRef = inInterfaceGroupRef;
        this.outInterfaceGroupRef = outInterfaceGroupRef;
        this.ruleList = (ruleList == null ? null : new ArrayList(ruleList));
    }

    @JsonProperty
    public String getUuid() {
        return this.uuid;
    }

    @JsonIgnore
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getTags() {
        return this.tags;
    }

    public ConfigLinePO getLineNumbers() {
        return this.lineNumbers;
    }

    public String getRuleListType() {
        return this.ruleListType;
    }

    public String getInInterfaceGroupRef() {
        return this.inInterfaceGroupRef;
    }

    public String getOutInterfaceGroupRef() {
        return this.outInterfaceGroupRef;
    }

    public List<FilterRulePO> getRuleList() {
        return this.ruleList;
    }

    public FilterRuleListOption getOption() {
        return this.option;
    }

    public void setOption(FilterRuleListOption option) {
        this.option = option;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setLineNumbers(ConfigLinePO lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    public void setRuleListType(String ruleListType) {
        this.ruleListType = ruleListType;
    }

    public void setInInterfaceGroupRef(String inInterfaceGroupRef) {
        this.inInterfaceGroupRef = inInterfaceGroupRef;
    }

    public void setOutInterfaceGroupRef(String outInterfaceGroupRef) {
        this.outInterfaceGroupRef = outInterfaceGroupRef;
    }

    public void setRuleList(List<FilterRulePO> ruleList) {
        this.ruleList = ruleList;
    }

    public void addFilterRule(FilterRulePO rule) {
        if (rule == null) {
            return;
        }
        if (this.ruleList == null) {
            this.ruleList = new ArrayList();
        }

        this.ruleList.add(rule);
    }

    public boolean isNatOnlyList() {
        if (this.isNatOnlyList == null) {
            return false;
        }
        return this.isNatOnlyList.booleanValue();
    }

    public void setNatOnlyList(Boolean isNatOnlyList) {
        this.isNatOnlyList = isNatOnlyList;
    }

    public RuleType getRuleType() {
        return this.ruleType;
    }

    public void setRuleType(RuleType type) {
        this.ruleType = type;
    }

    public enum RuleType {
        /**
         * nat
         */
        NAT_ONLY,
        /**
         * 策略路由
         */
        POLICY_ROUTING_ONLY,
        /**
         * 策略
         */
        POLICY_ONLY,
        /**
         * 混合型
         */
        MIXED
    }

}

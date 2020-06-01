package com.nspm.antlr4.common.domain.filter;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import com.nspm.antlr4.common.enums.FilterRuleActionEnum;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;
import com.nspm.antlr4.common.utils.DiffIgnoreFields;
import com.nspm.antlr4.validation.ZeeCustomValidation;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel(value = "策略")
@Document(collection="FILTER_RULE")
@Data
public class FilterRulePO extends COWAuditBaseEntity implements ZeeCustomValidation, Serializable {

    @JsonIgnore
    private String uuid;
    private IPTypeEnum ipType;
    @ApiModelProperty("策略名字")
    private String name;
    @ApiModelProperty("配置文件中的策略id")
    private String ruleId;
    @ApiModelProperty("描述")
    private String description;
    private String[] tags;
    @Transient
    @JsonView({DiffIgnoreFields.class})
    @ApiModelProperty("行号")
    private ConfigLinePO lineNumbers;
    @JsonView({DiffIgnoreFields.class})
    private String deviceUuid;

    @ApiModelProperty("策略集uuid")
    @JsonView({DiffIgnoreFields.class})
    private String ruleListUuid;
    @ApiModelProperty("入接口数组，数组里面的元素即可以接口名称也可以是接口组名称")
    private String[] inInterfaceGroupRefs;
    @ApiModelProperty("出接口数组，数组里面的元素即可以接口名称也可以是接口组名称")
    private String[] outInterfaceGroupRefs;
    @Valid
    @ApiModelProperty("策略匹配条款模型")
    private MatchClausePO matchClause;
    @Valid
    private MatchGroupPO matchGroup;
    @NotNull
    @ApiModelProperty("允许或拒绝")
    private FilterRuleActionEnum action;
    @ApiModelProperty("其余的动作，可能有下一跳信息")
    private ActionClausePO otherAction;
    @Valid
    @ApiModelProperty("NAT匹配条款对象")
    private NATClausePO natClause;
    @ApiModelProperty("指示策略是否隐性的")
    private Boolean isImplicit;
    private String logOption;
    @ApiModelProperty("超时时间")
    private Long idleTimeout;
    @ApiModelProperty("过滤时间组的名称")
    private String filterTimeGroupName;
    @ApiModelProperty("策略生效状态：true不生效，false生效")
    private Boolean inactive;
    private String originUuid;
    private Long originVersion;

    public FilterRulePO() {
    }

    public FilterRulePO(ConfigLinePO lineNumbers, String inInterfaceGroupRef, String outInterfaceGroupRef, NATClausePO natClause) {
        this(IPTypeEnum.IP4, null, null, null, null, lineNumbers, inInterfaceGroupRef, outInterfaceGroupRef, null, null, FilterRuleActionEnum.PERMIT, natClause, null);
    }

    public FilterRulePO(IPTypeEnum ipType, String name, String ruleId, String description, List<String> tagsList, ConfigLinePO lineNumbers, String inInterfaceGroupRef, String outInterfaceGroupRef, MatchClausePO matchClause, MatchGroupPO matchGroup, FilterRuleActionEnum action, NATClausePO natClause) {
        this(ipType, name, ruleId, description, tagsList, lineNumbers, inInterfaceGroupRef, outInterfaceGroupRef, matchClause, matchGroup, action, natClause, null);
    }

    private FilterRulePO(IPTypeEnum ipType, String name, String ruleId, String description, List<String> tagsList, ConfigLinePO lineNumbers, String inInterfaceGroupRef, String outInterfaceGroupRef, MatchClausePO matchClause, MatchGroupPO matchGroup, FilterRuleActionEnum action, NATClausePO natClause, Boolean isImplicit) {
        if (action == null) {
            throw new IllegalArgumentException("'action' couldn't be null");
        } else if (matchClause == null && matchGroup == null && natClause == null) {
            throw new IllegalArgumentException("At least one of these shouldn't be null: matchClause, matchGroup, natClause");
        } else {
            String[] inifs = inInterfaceGroupRef == null ? null : new String[]{inInterfaceGroupRef};
            String[] outifs = outInterfaceGroupRef == null ? null : new String[]{outInterfaceGroupRef};
            this.ipType = ipType;
            this.name = name;
            this.ruleId = ruleId;
            this.description = description;
            this.tags = tagsList == null ? null : tagsList.toArray(new String[tagsList.size()]);
            this.lineNumbers = lineNumbers;
            this.inInterfaceGroupRefs = inifs;
            this.outInterfaceGroupRefs = outifs;
            this.matchClause = matchClause;
            this.matchGroup = matchGroup;
            this.action = action;
            this.natClause = natClause;
            this.isImplicit = isImplicit;
        }
    }

    public FilterRulePO shallowCopy() {
        FilterRulePO newRule = new FilterRulePO(this.ipType, this.name, this.ruleId, this.description, this.tags == null ? null : Arrays.asList(this.tags), this.lineNumbers, null, null, this.matchClause, this.matchGroup, this.action, this.natClause, this.isImplicit);

        newRule.inInterfaceGroupRefs = this.inInterfaceGroupRefs;
        newRule.outInterfaceGroupRefs = this.outInterfaceGroupRefs;
        newRule.uuid = this.uuid;
        newRule.otherAction = this.otherAction;
        newRule.logOption = this.logOption;
        newRule.idleTimeout = this.idleTimeout;
        newRule.filterTimeGroupName = this.filterTimeGroupName;
        newRule.inactive = this.inactive;
        newRule.deviceUuid = this.deviceUuid;
        newRule.ruleListUuid = this.ruleListUuid;
        return newRule;
    }

    public IPTypeEnum getIpType() {
        if (this.ipType == null) {
            return IPTypeEnum.IP4;
        }
        return this.ipType;
    }

    public void setIpType(IPTypeEnum ipType) {
        this.ipType = ipType;
    }

    @JsonProperty
    @JsonView({DiffIgnoreFields.class})
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

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleId() {
        return this.ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getTags() {
        return this.tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public ConfigLinePO getLineNumbers() {
        return this.lineNumbers;
    }

    public void setLineNumbers(ConfigLinePO lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    public String[] getInInterfaceGroupRefs() {
        return this.inInterfaceGroupRefs;
    }

    public void setInInterfaceGroupRefs(String[] inInterfaceGroupRefs) {
        this.inInterfaceGroupRefs = inInterfaceGroupRefs;
    }

    public String[] getOutInterfaceGroupRefs() {
        return this.outInterfaceGroupRefs;
    }

    /*     */
    public void setOutInterfaceGroupRefs(String[] outInterfaceGroupRefs) {
        this.outInterfaceGroupRefs = outInterfaceGroupRefs;
    }

    public MatchClausePO getMatchClause() {
        return this.matchClause;
    }

    public void setMatchClause(MatchClausePO matchClause) {
        this.matchClause = matchClause;
    }

    public MatchGroupPO getMatchGroup() {
        return this.matchGroup;
    }

    public void setMatchGroup(MatchGroupPO matchGroup) {
        this.matchGroup = matchGroup;
    }

    public FilterRuleActionEnum getAction() {
        return this.action;
    }

    public void setAction(FilterRuleActionEnum action) {
        this.action = action;
    }

    public NATClausePO getNatClause() {
        return this.natClause;
    }

    public void setNatClause(NATClausePO natClause) {
        this.natClause = natClause;
    }

    public boolean isImplicit() {
        if (this.isImplicit == null) {
            return false;
        }
        return this.isImplicit.booleanValue();
    }

    public void setImplicit(boolean isImplicit) {
        this.isImplicit = Boolean.valueOf(isImplicit);
    }

    public String getDisplayName() {
        if ((this.name != null) && (this.ruleId != null)) {
            return this.name + "(" + this.ruleId + ")";
        }
        if (this.name != null) {
            return this.name;
        }
        if (this.ruleId != null) {
            return this.ruleId;
        }
        if (this.lineNumbers != null) {
            return "line:" + this.lineNumbers.toString();
        }
        if (isImplicit()) {
            return "Unnamed Implicit rule";
        }
        return "Unnamed rule";
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    public void addInInterfaceGroupRef(String inIfName) {
        if (inIfName == null) {
            return;
        }
        if (this.inInterfaceGroupRefs == null) {
            this.inInterfaceGroupRefs = new String[]{inIfName};
        } else {
            String[] newArr = new String[this.inInterfaceGroupRefs.length + 1];
            System.arraycopy(this.inInterfaceGroupRefs, 0, newArr, 0, this.inInterfaceGroupRefs.length);
            newArr[(newArr.length - 1)] = inIfName;
            this.inInterfaceGroupRefs = newArr;
        }
    }

    public void addOutInterfaceGroupRef(String outIfName) {
        if (outIfName == null) {
            return;
        }
        if (this.outInterfaceGroupRefs == null) {
            this.outInterfaceGroupRefs = new String[]{outIfName};
        } else {
            String[] newArr = new String[this.outInterfaceGroupRefs.length + 1];
            System.arraycopy(this.outInterfaceGroupRefs, 0, newArr, 0, this.outInterfaceGroupRefs.length);
            newArr[(newArr.length - 1)] = outIfName;
            this.outInterfaceGroupRefs = newArr;
        }
    }

    public ActionClausePO getOtherAction() {
        return this.otherAction;
    }

    public void setOtherAction(ActionClausePO otherAction) {
        this.otherAction = otherAction;
    }

    public void addOtherActionOption(String opt) {
        if (this.otherAction == null) {
            this.otherAction = new ActionClausePO();
        }
        this.otherAction.addMiscAction(opt);
    }

    public String getLogOption() {
        return this.logOption;
    }

    public void setLogOption(String logOption) {
        this.logOption = logOption;
    }

    public void appendLogOption(String log) {
        if (this.logOption == null) {
            this.logOption = log;
            return;
        }

        this.logOption = (this.logOption + " " + log);
    }

    public String getFilterTimeGroupName() {
        return this.filterTimeGroupName;
    }

    public void setFilterTimeGroupName(String filterTimeGroupName) {
        this.filterTimeGroupName = filterTimeGroupName;
    }

    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getRuleListUuid() {
        return this.ruleListUuid;
    }

    public void setRuleListUuid(String ruleListUuid) {
        this.ruleListUuid = ruleListUuid;
    }

    public boolean isInactive() {
        if (this.inactive != null) {
            return this.inactive.booleanValue();
        }
        return false;
    }

    public Boolean getInactive() {
        return this.inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String getOriginUuid() {
        return this.originUuid;
    }

    public void setOriginUuid(String originUuid) {
        this.originUuid = originUuid;
    }

    public Long getOriginVersion() {
        return this.originVersion;
    }

    public void setOriginVersion(Long originVersion) {
        this.originVersion = originVersion;
    }

    public Long getIdleTimeout() {
        return this.idleTimeout;
    }

    public void setIdleTimeout(Long idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public boolean isNatOnly() {
        return (this.matchClause == null) && (this.matchGroup == null) && (this.natClause != null);
    }

    public boolean basicFieldsEquals(FilterRulePO other){
        if(this == other){
            return true;
        }

        if(other == null){
            return false;
        }

        if (this.ipType == null) {
            if (other.getIpType() != null) {
                return false;
            }
        } else if (!this.ipType.equals(other.getIpType())) {
            return false;
        }

        if (this.name == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!this.name.equals(other.getName())) {
            return false;
        }

        if (this.ruleId == null) {
            if (other.getRuleId() != null) {
                return false;
            }
        } else if (!this.ruleId.equals(other.getRuleId())) {
            return false;
        }

        if (this.description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!this.description.equals(other.description)) {
            return false;
        }

        if (!Arrays.equals(this.tags, other.tags)) {
            return false;
        }

        if(this.lineNumbers != null && other.getLineNumbers() != null && !this.lineNumbers.toString().equals(other.getLineNumbers().toString())){
            return false;
        }

        if(!this.ruleListUuid.equals(other.getRuleListUuid())){
            return false;
        }

        if (!Arrays.equals(this.inInterfaceGroupRefs, other.inInterfaceGroupRefs)) {
            return false;
        }

        if (!Arrays.equals(this.outInterfaceGroupRefs, other.outInterfaceGroupRefs)) {
            return false;
        }

        if(this.matchClause == null){
            if(other.getMatchClause() != null){
                return false;
            }
        }else if(!JSON.toJSONString(this.matchClause).equals(JSON.toJSONString(other.getMatchClause()))){
            return false;
        }

        if(this.matchGroup == null){
            if(other.getMatchGroup() != null){
                return false;
            }
        }else if(!JSON.toJSONString(this.matchGroup).equals(JSON.toJSONString(other.getMatchGroup()))){
            return false;
        }

        if(!this.action.equals(other.getAction())){
            return false;
        }


        if(this.otherAction == null){
            if(other.getOtherAction() != null){
                return false;
            }
        }else if(!JSON.toJSONString(this.otherAction).equals(JSON.toJSONString(other.getOtherAction()))){
            return false;
        }

        if(this.natClause == null){
            if(other.getNatClause() != null){
                return false;
            }
        }else if(!JSON.toJSONString(this.natClause).equals(JSON.toJSONString(other.getNatClause()))){
            return false;
        }

        if(this.isImplicit == null){
            if(other.isImplicit != null){
                return false;
            }
        }else if(!this.isImplicit.equals(other.isImplicit)){
            return false;
        }

        if (this.logOption == null) {
            if (other.logOption != null) {
                return false;
            }
        } else if (!this.logOption.equals(other.logOption)) {
            return false;
        }

        if (this.idleTimeout == null) {
            if (other.idleTimeout != null) {
                return false;
            }
        } else if (!this.idleTimeout.equals(other.idleTimeout)) {
            return false;
        }

        if (this.filterTimeGroupName == null) {
            if (other.filterTimeGroupName != null) {
                return false;
            }
        } else if (!this.filterTimeGroupName.equals(other.filterTimeGroupName)) {
            return false;
        }


        if (this.inactive == null) {
            if (other.inactive != null) {
                return false;
            }
        } else if (!this.inactive.equals(other.inactive)) {
            return false;
        }


        if (this.originUuid == null) {
            if (other.originUuid != null) {
                return false;
            }
        } else if (!this.originUuid.equals(other.originUuid)) {
            return false;
        }

        return true;
    }



    @Override
    public List<ZeeError> validate() {
        List<ZeeError> result = new ArrayList();

        if ((this.matchClause == null) && (this.matchGroup == null) && (this.natClause == null)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_FILTER_RULE_EMPTY, null, "Empty filter rule at '" + getDisplayName() + "'");
            result.add(mye);
        }

        return result;
    }

    public enum FilterRuleOption {
        FRAGMENTS,
        DSCP,

        ESTABLISHED,
        TCP_OPTIONS,
        IP_OPTIONS;

        FilterRuleOption() {
        }
    }

}

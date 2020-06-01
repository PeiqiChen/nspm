package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import com.nspm.antlr4.common.interfaces.DeviceObject;
import com.nspm.antlr4.common.utils.DiffIgnoreFields;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel(value = "服务组对象")
@Document(collection = "FILTER_SERVICE_GROUP")
@Data
@CompoundIndexes({@CompoundIndex(name="uuid_idx", def="{'uuid': 1}", unique=true), @CompoundIndex(name="deviceUuid_name_idx", def="{'deviceUuid': 1, 'name': -1 }")})
public class FilterServiceGroupPO extends COWAuditBaseEntity implements DeviceObject, Serializable {

    @ApiModelProperty(value = "uuid")
    @JsonView({DiffIgnoreFields.class})
    protected String uuid;

    @ApiModelProperty(value = "设备uuid")
    @JsonView({DiffIgnoreFields.class})
    protected String deviceUuid;


    @ApiModelProperty("描述")
    protected String description;

    @ApiModelProperty("设备对象名称")
    @NotBlank
    protected String name;

    private List<String> tags;

    @JsonView
    private ConfigLinePO lineNumbers;
    private Boolean isImplicit;
    @ApiModelProperty("包含过滤服务集")
    private List<FilterServicePO> includeFilterServices;
    @ApiModelProperty("包含过滤服务名称集")
    private List<String> includeFilterServiceNames;
    @ApiModelProperty("包含过滤服务组名称集")
    private List<String> includeFilterServiceGroupNames;

    private DeviceObjectTypeEnum deviceObjectType = DeviceObjectTypeEnum.SERVICE_GROUP_OBJECT;

    @ApiModelProperty("超时时间")
    private Long idleTimeout;

    public FilterServiceGroupPO() {
        this.deviceObjectType = DeviceObjectTypeEnum.SERVICE_GROUP_OBJECT;
    }

    public FilterServiceGroupPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, List<String> includeFilterServiceNamesList, List<String> includeFilterServiceGroupNamesList) {
        this(name, description, tagsList, lineNumbers, (Boolean)null, includeFilterServiceNamesList, includeFilterServiceGroupNamesList);
    }

    public FilterServiceGroupPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, Boolean isImplicit, List<String> includeFilterServiceNamesList, List<String> includeFilterServiceGroupNamesList) {
        this.deviceObjectType = DeviceObjectTypeEnum.SERVICE_GROUP_OBJECT;
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
            this.description = description;
            this.tags = tagsList;
            if ((isImplicit == null || !isImplicit) && lineNumbers == null) {
                throw new IllegalArgumentException("Line number is required for non-implicit service group object.");
            } else {
                this.lineNumbers = lineNumbers;
                this.isImplicit = isImplicit;
                this.includeFilterServiceNames = includeFilterServiceNamesList;
                this.includeFilterServiceGroupNames = includeFilterServiceGroupNamesList;
            }
        } else {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
    }

    public FilterServiceGroupPO(FilterServiceGroupPO item) {
        this.deviceObjectType = DeviceObjectTypeEnum.SERVICE_GROUP_OBJECT;
        if (item != null) {
            this.uuid = item.uuid;
            this.deviceUuid = item.deviceUuid;
            this.name = item.name;
            this.description = item.description;
            this.tags = item.tags;
            this.lineNumbers = item.lineNumbers;
            this.isImplicit = item.isImplicit;
            this.includeFilterServices = item.includeFilterServices;
            this.includeFilterServiceNames = item.includeFilterServiceNames;
            this.includeFilterServiceGroupNames = item.includeFilterServiceGroupNames;
            this.idleTimeout = item.idleTimeout;
            this.deviceObjectType = item.deviceObjectType;
        }
    }

    public void addIncludeFilterServiceName(String serviceName) {
        if (serviceName != null) {
            if (this.includeFilterServiceNames == null) {
                this.includeFilterServiceNames = new ArrayList<>();
            }

            this.includeFilterServiceNames.add(serviceName);
        }
    }

    public void addIncludeFilterServiceGroupName(String groupName) {
        if (groupName != null) {
            if (this.includeFilterServiceGroupNames == null) {
                this.includeFilterServiceGroupNames = new ArrayList<>();
            }

            this.includeFilterServiceGroupNames.add(groupName);
        }
    }

    public void addIncludeFilterService(FilterServicePO service) {
        if (service != null) {
            if (this.includeFilterServices == null) {
                this.includeFilterServices = new ArrayList<>();
            }

            this.includeFilterServices.add(service);
        }
    }

    @Override
    @JsonProperty
    public DeviceObjectTypeEnum getDeviceObjectType() {
        return this.deviceObjectType == null ? DeviceObjectTypeEnum.SERVICE_GROUP_OBJECT : this.deviceObjectType;
    }


    public String toArgsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.name != null) {
            sb.append("name:" + this.name + ",");
        }
        if (this.includeFilterServices != null) {
            sb.append("includeFilterServices:" + JSON.toJSONString(this.includeFilterServices) + ",");
        }
        if (this.includeFilterServiceNames != null) {
            sb.append("includeFilterServiceNames:" + JSON.toJSONString(this.includeFilterServiceNames) + ",");
        }
        if (this.includeFilterServiceGroupNames != null) {
            sb.append("includeFilterServiceGroupNames:" + JSON.toJSONString(this.includeFilterServiceGroupNames) + ",");
        }
        if (this.idleTimeout != null) {
            sb.append("idleTimeout:" + this.idleTimeout + ",");
        }
        if(this.description != null){
            sb.append("description:" + description + ",");
        }
        if(this.lineNumbers != null){
            sb.append("lineNumbers:" + this.lineNumbers.toString() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

}

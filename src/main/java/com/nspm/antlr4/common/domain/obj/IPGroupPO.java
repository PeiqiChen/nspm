package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.interfaces.DeviceObject;
import com.nspm.antlr4.common.utils.DiffIgnoreFields;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
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
@ApiModel(value = "地址组对象")
@Document(collection = "IP_GROUP")
@Getter
@Setter
@CompoundIndexes({@CompoundIndex(name="uuid_idx", def="{'uuid': 1}", unique=true), @CompoundIndex(name="deviceUuid_name_idx", def="{'deviceUuid': 1, 'name': -1 }")})
public class IPGroupPO extends COWAuditBaseEntity implements DeviceObject, Serializable {

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
    private Boolean isImplicit;
    private IPTypeEnum type;
    @ApiModelProperty("地址组包含项")
    private List<IPItemPO> includeItems;

    @ApiModelProperty("地址名称,Juniper防火墙有该值")
    private List<String> includeItemNames;

    @ApiModelProperty("组名称")
    private List<String>  includeGroupNames;

    @ApiModelProperty("排除项")
    private List<IPItemPO> excludeItems;
    private List<String> excludeItemNames;
    private List<String> excludeGroupNames;

    @JsonView
    @ApiModelProperty("行号")
    private ConfigLinePO lineNumbers;

    private DeviceObjectTypeEnum deviceObjectType = DeviceObjectTypeEnum.NETWORK_GROUP_OBJECT;

    public IPGroupPO() {
        this.deviceObjectType = DeviceObjectTypeEnum.NETWORK_GROUP_OBJECT;
    }

    public IPGroupPO(String name) {
        this.deviceObjectType = DeviceObjectTypeEnum.NETWORK_GROUP_OBJECT;
        this.name = name;
    }

    public IPGroupPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, Boolean isImplicit, IPTypeEnum type, List<IPItemPO> includeItemsList, List<String> includeItemNamesList, List<String> includeGroupNamesList, List<IPItemPO> excludeItemsList, List<String> excludeItemNamesList, List<String> excludeGroupNamesList) {
        this.deviceObjectType = DeviceObjectTypeEnum.NETWORK_GROUP_OBJECT;
        this.name = name;
        this.description = description;
        this.tags = tagsList;
        this.lineNumbers = lineNumbers;
        this.isImplicit = isImplicit;
        this.type = type;
        this.includeItems = includeItemsList;
        this.includeItemNames = includeItemNamesList;
        this.includeGroupNames = includeGroupNamesList;
        this.excludeItems = excludeItemsList;
        this.excludeItemNames = excludeItemNamesList;
        this.excludeGroupNames = excludeGroupNamesList;
    }

    public void addIncludeItem(IPItemPO item) {
        if (item != null) {
            if (this.includeItems == null) {
                this.includeItems = new ArrayList<>();
            }
            this.includeItems.add(item);
        }
    }

    public void addIncludeItemName(String itemName) {
        if (itemName != null) {
            if (this.includeItemNames == null) {
                this.includeItemNames = new ArrayList<>();
            }
            this.includeItemNames.add(itemName);
        }
    }

    public void addIncludeGroupName(String includeGroupName) {
        if (includeGroupName != null) {
            if (this.includeGroupNames == null) {
                this.includeGroupNames = new ArrayList<>();
            }
            this.includeGroupNames.add(includeGroupName);
        }
    }

    public void addExcludeItem(IPItemPO item) {
        if (item != null) {
            if (this.excludeItems == null) {
                this.excludeItems = new ArrayList<>();
            }
            this.excludeItems.add(item);
        }
    }

    public void addExcludeItemName(String itemName) {
        if (itemName != null) {
            if (this.excludeItemNames == null) {
                this.excludeItemNames = new ArrayList<>();
            }
            this.excludeItemNames.add(itemName);
        }
    }

    public void addExcludeGroupName(String groupName) {
        if (groupName != null) {
            if (this.excludeGroupNames == null) {
                this.excludeGroupNames = new ArrayList<>();
            }
            this.excludeGroupNames.add(groupName);
        }
    }

    @Override
    public DeviceObjectTypeEnum getDeviceObjectType() {
        return this.deviceObjectType == null ? DeviceObjectTypeEnum.NETWORK_GROUP_OBJECT : this.deviceObjectType;
    }

    public String toArgsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.name != null) {
            sb.append("name:" + this.name + ",");
        }
        if (this.includeItems != null) {
            sb.append("includeItems:" + JSON.toJSONString(this.includeItems) + ",");
        }
        if (this.includeItemNames != null) {
            sb.append("includeItemNames:" + JSON.toJSONString(this.includeItemNames) + ",");
        }
        if (this.includeGroupNames != null) {
            sb.append("includeGroupNames:" + JSON.toJSONString(this.includeGroupNames) + ",");
        }
        if (this.excludeItems != null) {
            sb.append("excludeItems:" + JSON.toJSONString(this.excludeItems) + ",");
        }
        if (this.excludeItemNames != null) {
            sb.append("name:" + JSON.toJSONString(this.excludeItemNames) + ",");
        }
        if (this.excludeGroupNames != null) {
            sb.append("excludeGroupNames:" + JSON.toJSONString(this.excludeGroupNames) + ",");
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

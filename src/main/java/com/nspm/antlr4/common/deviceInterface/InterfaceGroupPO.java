package com.nspm.antlr4.common.deviceInterface;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import com.nspm.antlr4.common.interfaces.DeviceObject;
import com.nspm.antlr4.common.utils.DiffIgnoreFields;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.*;

/**
 * @author luwei
 * @date 2019/9/11
 */
@ApiModel(value = "zone接口组")
@Document(collection = "DEVICE_ZONE")
@Data
@CompoundIndexes({@CompoundIndex(name="uuid_idx", def="{'uuid': 1}", unique=true), @CompoundIndex(name="deviceUuid_name_idx", def="{'deviceUuid': 1, 'name': -1 }")})
public class InterfaceGroupPO extends COWAuditBaseEntity implements DeviceObject, Serializable {

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

    private String[] tags;
    @Transient
    @JsonView({DiffIgnoreFields.class})
    private ConfigLinePO lineNumbers;
    private Boolean isImplicit;
    @ApiModelProperty("优先级")
    private Integer priority;
    @ApiModelProperty("接口名集")
    private List<String> interfaceNames;

    public InterfaceGroupPO() {
    }

    public InterfaceGroupPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, List<String> interfaceNamesList) {
        this(name, description, tagsList, lineNumbers, null, interfaceNamesList);
    }

    public InterfaceGroupPO(String name, String description, List<String> tagsList, ConfigLinePO lineNumbers, Boolean isImplicit, List<String> interfaceNamesList) {
        if ((name == null) || (name.trim().isEmpty())) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
        this.description = description;
        this.tags = (tagsList == null ? null : (String[]) tagsList.toArray(new String[tagsList.size()]));
        if (((isImplicit == null) || (!isImplicit.booleanValue())) && (lineNumbers == null)) {
            throw new IllegalArgumentException("Line number is required for non-implicit interface group.");
        }
        this.lineNumbers = lineNumbers;
        this.isImplicit = isImplicit;

        if (interfaceNamesList == null) {
            throw new IllegalArgumentException("InterfaceNameList cannot be null");
        }
        this.interfaceNames = interfaceNamesList;
    }

    public List<String> getTags() {
        return this.tags == null ? null : Collections.unmodifiableList(Arrays.asList(this.tags));
    }

    @Override
    public ConfigLinePO getLineNumbers() {
        return this.lineNumbers;
    }

    public boolean isImplicit() {
        if (this.isImplicit == null) {
            return false;
        }
        return this.isImplicit.booleanValue();
    }

    public List<String> getInterfaceNames() {
        return this.interfaceNames;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public void setLineNumbers(ConfigLinePO lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    public void setIsImplicit(Boolean isImplicit) {
        this.isImplicit = isImplicit;
    }

    public void setInterfaceNames(List<String> interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public void addInterfaceName(String interfaceName) {
        if (interfaceName == null) {
            return;
        }
        if (this.interfaceNames == null) {
            this.interfaceNames = new ArrayList();
        }
        this.interfaceNames.add(interfaceName);
    }

    public void addInterfaceNames(Set<String> ifNames) {
        if ((ifNames == null) || (ifNames.isEmpty())) {
            return;
        }

        if (this.interfaceNames == null) {
            this.interfaceNames = new ArrayList();
        }
        this.interfaceNames.addAll(ifNames);
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String toArgsString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.name != null) {
            sb.append("name:" + this.name + ",");
        }
        if (this.priority != null) {
            sb.append("priority:" + priority + ",");
        }
        if (this.interfaceNames != null) {
            sb.append("interfaceNames:" + JSON.toJSONString(this.interfaceNames) + ",");
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


    @Override
    public DeviceObjectTypeEnum getDeviceObjectType() {
        return DeviceObjectTypeEnum.ZONE;
    }
}

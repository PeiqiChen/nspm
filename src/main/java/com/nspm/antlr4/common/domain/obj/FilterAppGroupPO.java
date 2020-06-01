package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import com.nspm.antlr4.common.utils.DiffIgnoreFields;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/11
 */
public class FilterAppGroupPO {

    @JsonView({DiffIgnoreFields.class})
    private String uuid;
    @JsonView({DiffIgnoreFields.class})
    private String deviceUuid;
    private String name;
    private String description;
    private List<String> tags;
    @Transient
    @JsonView({DiffIgnoreFields.class})
    private ConfigLinePO lineNumbers;
    private Boolean isImplicit;
    private List<String> includeAppNames;
    private List<String> includeAppGroupNames;
    private List<String> includeFilterAppGroupNames;

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ConfigLinePO getLineNumbers() {
        return this.lineNumbers;
    }

    public void setLineNumbers(ConfigLinePO lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    public boolean isImplicit() {
        if (this.isImplicit == null) {
            return false;
        }
        return this.isImplicit.booleanValue();
    }

    public Boolean getIsImplicit() {
        return this.isImplicit;
    }

    public void setIsImplicit(Boolean isImplicit) {
        this.isImplicit = isImplicit;
    }

    public List<String> getIncludeAppNames() {
        return this.includeAppNames;
    }

    public void setIncludeAppNames(List<String> includeAppNames) {
        this.includeAppNames = includeAppNames;
    }

    public void addIncludeAppName(String appName) {
        if (appName == null) { return;
        }
        if (this.includeAppNames == null) {
            this.includeAppNames = new ArrayList();
        }

        this.includeAppNames.add(appName);
    }

    public List<String> getIncludeAppGroupNames() {
        return this.includeAppGroupNames;
    }

    public void setIncludeAppGroupNames(List<String> includeAppGroupNames) {
        this.includeAppGroupNames = includeAppGroupNames;
    }

    public void addIncludeAppGroupName(String appGroupName) {
        if (appGroupName == null) { return;
        }
        if (this.includeAppGroupNames == null) {
            this.includeAppGroupNames = new ArrayList();
        }

        this.includeAppGroupNames.add(appGroupName);
    }

    public List<String> getIncludeFilterAppGroupNames() {
        return this.includeFilterAppGroupNames;
    }

    public void setIncludeFilterAppGroupNames(List<String> includeFilterAppGroupNames)
    {
        this.includeFilterAppGroupNames = includeFilterAppGroupNames;
    }

    public void addIncludeFilterAppGroupName(String filterAppGroupName) {
        if (filterAppGroupName == null) { return;
        }
        if (this.includeFilterAppGroupNames == null) {
            this.includeFilterAppGroupNames = new ArrayList();
        }

        this.includeFilterAppGroupNames.add(filterAppGroupName);
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DeviceObjectTypeEnum getDeviceObjectType()
    {
        return DeviceObjectTypeEnum.APP_OBJECT;
    }

    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }
}

package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import com.nspm.antlr4.common.utils.DiffIgnoreFields;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/11
 */
public class FilterUserGroupPO {

    @JsonView({DiffIgnoreFields.class})
    private String uuid;
    @JsonView({DiffIgnoreFields.class})
    private String deviceUuid;
    private String name;
    private String description;
    private List<String> tags;
//    @Transient
    @JsonView({DiffIgnoreFields.class})
    private ConfigLinePO lineNumbers;
    private Boolean isImplicit;
    private List<String> includeUserNames;
    private List<String> includeUserGroupNames;
    private List<String> includeFilterUserGroupNames;

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

    public List<String> getIncludeUserNames() {
        return this.includeUserNames;
    }

    public void setIncludeUserNames(List<String> includeUserNames) {
        this.includeUserNames = includeUserNames;
    }

    public void addIncludeUserName(String userName) {
        if (userName == null) { return;
        }
        if (this.includeUserNames == null) {
            this.includeUserNames = new ArrayList();
        }

        this.includeUserNames.add(userName);
    }

    public List<String> getIncludeUserGroupNames() {
        return this.includeUserGroupNames;
    }

    public void setIncludeUserGroupNames(List<String> includeUserGroupNames) {
        this.includeUserGroupNames = includeUserGroupNames;
    }

    public void addIncludeUserGroupName(String userGroupName) {
        if (userGroupName == null) { return;
        }
        if (this.includeUserGroupNames == null) {
            this.includeUserGroupNames = new ArrayList();
        }

        this.includeUserGroupNames.add(userGroupName);
    }

    public List<String> getIncludeFilterUserGroupNames() {
        return this.includeFilterUserGroupNames;
    }

    public void setIncludeFilterUserGroupNames(List<String> includeFilterUserGroupNames)
    {
        this.includeFilterUserGroupNames = includeFilterUserGroupNames;
    }

    public void addIncludeFilterUserGroupName(String filterUserGroupName) {
        if (filterUserGroupName == null) { return;
        }
        if (this.includeFilterUserGroupNames == null) {
            this.includeFilterUserGroupNames = new ArrayList();
        }

        this.includeFilterUserGroupNames.add(filterUserGroupName);
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DeviceObjectTypeEnum getDeviceObjectType()
    {
        return DeviceObjectTypeEnum.USER_OBJECT;
    }

    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }
}

package com.nspm.antlr4.common.domain.routing;

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
public class RoutingSubnetGroupPO {

    @JsonView({DiffIgnoreFields.class})
    private String uuid;
    @JsonView({DiffIgnoreFields.class})
    private String deviceUuid;
    private String name;
    private String description;
    @Transient
    @JsonView({DiffIgnoreFields.class})
    private ConfigLinePO lineNumbers;
    private boolean isSystemDefined;
    private List<RoutingSubnetItemPO> items;

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

    public ConfigLinePO getLineNumbers()
    {
        return this.lineNumbers;
    }

    public void setLineNumbers(ConfigLinePO lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    public boolean isSystemDefined() {
        return this.isSystemDefined;
    }

    public void setSystemDefined(boolean isSystemDefined) {
        this.isSystemDefined = isSystemDefined;
    }

    public List<RoutingSubnetItemPO> getItems() {
        return this.items;
    }

    public void setItems(List<RoutingSubnetItemPO> items) {
        this.items = items;
    }

    public void addSubnetItem(RoutingSubnetItemPO item) {
        if (item == null) { return;
        }
        if (this.items == null) {
            this.items = new ArrayList();
        }

        this.items.add(item);
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DeviceObjectTypeEnum getDeviceObjectType()
    {
        return DeviceObjectTypeEnum.ROUTING_SUBNET_OBJECT;
    }

    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

}

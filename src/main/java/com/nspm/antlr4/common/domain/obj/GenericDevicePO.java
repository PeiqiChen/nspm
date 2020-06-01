package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.domain.DeviceRawConfigPO;
import com.nspm.antlr4.common.deviceInterface.InterfaceGroupPO;
import com.nspm.antlr4.common.domain.filter.FilterRuleListPO;
import com.nspm.antlr4.common.domain.routing.RoutingSubnetGroupPO;
import com.nspm.antlr4.common.domain.routing.RoutingTablePO;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;
import com.nspm.antlr4.common.utils.GenericDeviceValidator;
import com.nspm.antlr4.validation.ZeeCustomValidation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luwei
 * @date 2019/9/11
 */
@ApiModel(value = "通用解析设备对象")
@Data
public class GenericDevicePO implements ZeeCustomValidation {

    @Valid
    @ApiModelProperty("设备简略信息对象")
    private DevicePO device;
    @Valid
    @ApiModelProperty("地址对象集")
    private LinkedHashMap<String, IPObjectPO> ipItems;
    @Valid
    @ApiModelProperty("地址组对象集")
    private LinkedHashMap<String, IPGroupPO> ipGroups;
    @Valid
    @ApiModelProperty("域对象集")
    private LinkedHashMap<String, InterfaceGroupPO> zones;
    @Valid
    @ApiModelProperty("服务对象集")
    private LinkedHashMap<String, FilterServiceObjectPO> services;
    @Valid
    @ApiModelProperty("服务组对象集")
    private LinkedHashMap<String, FilterServiceGroupPO> serviceGroups;
    @Valid
    private LinkedHashMap<String, FilterUserGroupPO> userGroups;
    @Valid
    private LinkedHashMap<String, FilterAppGroupPO> appGroups;
//    @Valid
//    @ApiModelProperty("定时器对象集")
//    private LinkedHashMap<String, FilterTimeGroupPO> timeGroups;
    @Valid
    @ApiModelProperty("策略集对象集")
    private LinkedHashMap<String, FilterRuleListPO> filterLists;
    @Valid
    @ApiModelProperty("路由对象集")
    private LinkedHashMap<String, RoutingTablePO> routingTables;
    @Valid
    private LinkedHashMap<String, RoutingSubnetGroupPO> routingSubnetGroups;
    private List<ZeeError> parserWarnings;
    private List<ZeeError> parserErrors;
    @NotNull
    @Size(max = 104857600)
    @ApiModelProperty("原始配置信息")
    private String rawConfig;
    @ApiModelProperty("其他配置信息")
    private LinkedHashMap<String, String> otherRawConfig;

    public DevicePO getDevice() {
        return this.device;
    }

    public void setDevice(DevicePO device) {
        this.device = device;
    }

    public LinkedHashMap<String, FilterRuleListPO> getFilterLists() {
        return this.filterLists;
    }

    public FilterRuleListPO getFilterList(String name) {
        if (this.filterLists != null) {
            return (FilterRuleListPO) this.filterLists.get(name);
        }
        return null;
    }

    public void setFilterLists(LinkedHashMap<String, FilterRuleListPO> filterLists) {
        this.filterLists = filterLists;
    }

    public LinkedHashMap<String, RoutingTablePO> getRoutingTables() {
        return this.routingTables;
    }

    public RoutingTablePO getRoutingTable(String name) {
        if (this.routingTables != null) {
            return (RoutingTablePO) this.routingTables.get(name);
        }
        return null;
    }

    public void setRoutingTables(LinkedHashMap<String, RoutingTablePO> routingTables) {
        this.routingTables = routingTables;
    }

    public String getRawConfig() {
        return this.rawConfig;
    }

    public void setRawConfig(String rawConfig) {
        this.rawConfig = rawConfig;
    }

    public void addOtherRawConfig(String name, String rawText) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name shouldn't be empty string.");
        }

        if (this.otherRawConfig == null) {
            this.otherRawConfig = new LinkedHashMap();
        }
        this.otherRawConfig.put(name, rawText);
    }

    public DeviceRawConfigPO getAllRawConfig() {
        DeviceRawConfigPO result = new DeviceRawConfigPO();

        result.addRawText("", this.rawConfig);

        if (this.otherRawConfig != null) {
            for (Map.Entry<String, String> mye : this.otherRawConfig.entrySet()) {
                String mykey = (String) mye.getKey();
                String myval = (String) mye.getValue();

                result.addRawText(mykey, myval);
            }
        }

        return result;
    }

    public List<ZeeError> getParserWarnings() {
        return this.parserWarnings;
    }

    public void setParserWarnings(List<ZeeError> parserWarnings) {
        if ((parserWarnings != null) && (!parserWarnings.isEmpty())) {
            this.parserWarnings = parserWarnings;
        }
    }

    public void addParserWarning(ZeeError warning) {
        if (warning == null) {
            return;
        }

        if (this.parserWarnings == null) {
            this.parserWarnings = new ArrayList();
        }
        this.parserWarnings.add(warning);
    }

    public List<ZeeError> getParserErrors() {
        return this.parserErrors;
    }

    public void setParserErrors(List<ZeeError> parserErrors) {
        if ((parserErrors != null) && (!parserErrors.isEmpty())) {
            this.parserErrors = parserErrors;
        }
    }

    public void addParserError(ZeeError error) {
        if (error == null) {
            return;
        }

        if (this.parserErrors == null) {
            this.parserErrors = new ArrayList();
        }
        this.parserErrors.add(error);
    }

    public void addIpItem(IPObjectPO item) {
        if (item == null) {
            return;
        }

        if (this.ipItems == null) {
            this.ipItems = new LinkedHashMap();
        } else {
            IPObjectPO my = (IPObjectPO) this.ipItems.get(item.getName());
            if (my != null) {
                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for IP Object '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
                addParserWarning(mywarning);
            }
        }
        this.ipItems.put(item.getName(), item);
    }

    public IPObjectPO removeIpItem(String name) {
        if (this.ipItems == null) {
            return null;
        }

        return (IPObjectPO) this.ipItems.remove(name);
    }

    public void addIpGroup(IPGroupPO item) {
        if (item == null) {
            return;
        }

        if (item.getDeviceObjectType() != DeviceObjectTypeEnum.NETWORK_GROUP_OBJECT) {
            throw new IllegalArgumentException("Only IPGroupPO is allowed in addIpGroup()");
        }

        if (this.ipGroups == null) {
            this.ipGroups = new LinkedHashMap();
        } else {
            IPGroupPO my = (IPGroupPO) this.ipGroups.get(item.getName());
            if (my != null) {
                /*     */
                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for IP group '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
                addParserWarning(mywarning);
            }
        }
        this.ipGroups.put(item.getName(), item);
    }

    public IPGroupPO removeIpGroup(String name) {
        if (this.ipGroups == null) {
            return null;
        }

        return (IPGroupPO) this.ipGroups.remove(name);
    }

    public void addZone(InterfaceGroupPO item) {
        if (item == null) {
            return;
        }

        if (this.zones == null) {
            this.zones = new LinkedHashMap();
        } else {
            InterfaceGroupPO my = (InterfaceGroupPO) this.zones.get(item.getName());
            if (my != null) {
                /*     */
                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for zone '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
                addParserWarning(mywarning);
            }
        }
        this.zones.put(item.getName(), item);
    }

    public InterfaceGroupPO removeZone(String name) {
        if (this.zones == null) {
            return null;
        }

        return (InterfaceGroupPO) this.zones.remove(name);
    }

    public void addService(FilterServiceObjectPO item) {
        if (item == null) {
            return;
        }

        if (this.services == null) {
            this.services = new LinkedHashMap();
        } else {
            FilterServiceObjectPO my = (FilterServiceObjectPO) this.services.get(item.getName());
            if (my != null) {
                /*     */
                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for service '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
                addParserWarning(mywarning);
            }
        }
        this.services.put(item.getName(), item);
    }

    public FilterServiceObjectPO removeService(String name) {
        if (this.services == null) {
            return null;
        }

        return (FilterServiceObjectPO) this.services.remove(name);
    }

    public void addServiceGroup(FilterServiceGroupPO item) {
        if (item == null) {
            return;
        }

        if (item.getDeviceObjectType() != DeviceObjectTypeEnum.SERVICE_GROUP_OBJECT) {
            throw new IllegalArgumentException("Only FilterServiceGroupPO is allowed in addServiceGroup()");
        }

        if (this.serviceGroups == null) {
            this.serviceGroups = new LinkedHashMap();
        } else {
            FilterServiceGroupPO my = (FilterServiceGroupPO) this.serviceGroups.get(item.getName());
            if (my != null) {
                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for service group '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
                addParserWarning(mywarning);
            }
        }

        this.serviceGroups.put(item.getName(), item);
    }

    public FilterServiceGroupPO removeServiceGroup(String name) {
        if (this.serviceGroups == null) {
            return null;
        }

        return (FilterServiceGroupPO) this.serviceGroups.remove(name);
    }

    public void addUserGroup(FilterUserGroupPO item) {
        if (item == null) {
            return;
        }

        if (this.userGroups == null) {
            this.userGroups = new LinkedHashMap();
        } else {
            FilterUserGroupPO my = (FilterUserGroupPO) this.userGroups.get(item.getName());
            if (my != null) {
                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for user group '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
                addParserWarning(mywarning);
            }
        }
        this.userGroups.put(item.getName(), item);
    }

    public FilterUserGroupPO removeUserGroup(String name) {
        if (this.userGroups == null) {
            return null;
        }
        return (FilterUserGroupPO) this.userGroups.remove(name);
    }

//    public void addTimeGroup(FilterTimeGroupPO item) {
//        if (item == null) {
//            return;
//        }
//
//        if (this.timeGroups == null) {
//            this.timeGroups = new LinkedHashMap();
//        } else {
//            FilterTimeGroupPO my = (FilterTimeGroupPO) this.timeGroups.get(item.getName());
//            if (my != null) {
//                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for time object '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
//                addParserWarning(mywarning);
//            }
//        }
//        this.timeGroups.put(item.getName(), item);
//    }

//    public FilterTimeGroupPO removeTimeGroup(String name) {
//        if (this.timeGroups == null) {
//            return null;
//        }
//        return (FilterTimeGroupPO) this.timeGroups.remove(name);
//    }

    public LinkedHashMap<String, IPObjectPO> getIpItems() {
        return this.ipItems;
    }

    public IPObjectPO getIpItem(String name) {
        if (this.ipItems != null) {
            return (IPObjectPO) this.ipItems.get(name);
        }
        return null;
    }

    public LinkedHashMap<String, IPGroupPO> getIpGroups() {
        return this.ipGroups;
    }

    public IPGroupPO getIpGroup(String name) {
        if (this.ipGroups != null) {
            return (IPGroupPO) this.ipGroups.get(name);
        }
        return null;
    }

    public LinkedHashMap<String, InterfaceGroupPO> getZones() {
        return this.zones;
    }

    public InterfaceGroupPO getZone(String name) {
        if (this.zones != null) {
            return (InterfaceGroupPO) this.zones.get(name);
        }
        return null;
    }

    public LinkedHashMap<String, FilterServiceObjectPO> getServices() {
        return this.services;
    }

    public FilterServiceObjectPO getService(String name) {
        if (this.services != null) {
            return (FilterServiceObjectPO) this.services.get(name);
        }
        return null;
    }

    public LinkedHashMap<String, FilterServiceGroupPO> getServiceGroups() {
        return this.serviceGroups;
    }

    public FilterServiceGroupPO getServiceGroup(String name) {
        if (this.serviceGroups != null) {
            return (FilterServiceGroupPO) this.serviceGroups.get(name);
        }
        return null;
    }

    public LinkedHashMap<String, FilterUserGroupPO> getUserGroups() {
        return this.userGroups;
    }

    public FilterUserGroupPO getUserGroup(String name) {
        if (this.userGroups != null) {
            return (FilterUserGroupPO) this.userGroups.get(name);
        }
        return null;
    }

//    public LinkedHashMap<String, FilterTimeGroupPO> getTimeGroups() {
//        return this.timeGroups;
//    }
//
//    public FilterTimeGroupPO getTimeGroup(String name) {
//        if (this.timeGroups != null) {
//            return (FilterTimeGroupPO) this.timeGroups.get(name);
//        }
//        return null;
//    }

    public void setZones(LinkedHashMap<String, InterfaceGroupPO> zones) {
        this.zones = zones;
    }

    public LinkedHashMap<String, FilterAppGroupPO> getAppGroups() {
        return this.appGroups;
    }

    public FilterAppGroupPO getAppGroup(String name) {
        if (this.appGroups != null) {
            return (FilterAppGroupPO) this.appGroups.get(name);
        }
        return null;
    }

    public void addAppGroup(FilterAppGroupPO item) {
        if (item == null) {
            return;
        }

        if (this.appGroups == null) {
            this.appGroups = new LinkedHashMap();
        } else {
            FilterAppGroupPO my = (FilterAppGroupPO) this.appGroups.get(item.getName());
            if (my != null) {
                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for App group '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
                addParserWarning(mywarning);
            }
        }
        this.appGroups.put(item.getName(), item);
    }

    public FilterAppGroupPO removeAppGroup(String name) {
        if (this.appGroups == null) {
            return null;
        }
        return (FilterAppGroupPO) this.appGroups.remove(name);
    }

    public LinkedHashMap<String, RoutingSubnetGroupPO> getRoutingSubnetGroups() {
        return this.routingSubnetGroups;
    }

    public void setRoutingSubnetGroups(LinkedHashMap<String, RoutingSubnetGroupPO> routingSubnetGroups) {
        this.routingSubnetGroups = routingSubnetGroups;
    }

    public void addRoutingSubnetGroup(RoutingSubnetGroupPO item) {
        if (item == null) {
            return;
        }

        if (this.routingSubnetGroups == null) {
            this.routingSubnetGroups = new LinkedHashMap();
        } else {
            RoutingSubnetGroupPO my = (RoutingSubnetGroupPO) this.routingSubnetGroups.get(item.getName());
            if (my != null) {
                ZeeError mywarning = new ZeeError(ZeeErrorName.PLUGIN_DEVICE_PARSER_WARNING, null, "Duplicate name for RoutingSubnetGroupPO '" + item.getName() + "'. Line one: " + my.getLineNumbers() + ". Line two: " + item.getLineNumbers());
                addParserWarning(mywarning);
            }
        }
        this.routingSubnetGroups.put(item.getName(), item);
    }

    @Override
    public List<ZeeError> validate() {
        List<ZeeError> result = new ArrayList();

        GenericDeviceValidator.validateGenericDevice(this, result);

        return result;
    }

}

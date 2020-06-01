package com.nspm.antlr4.common.domain.routing;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.common.COWAuditBaseEntity;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;
import com.nspm.antlr4.validation.ZeeCustomValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 路由策略集信息，与RoutingTableDataPO 大致相同
 * @author luwei
 * @date 2019/9/9
 */
@Data
@Document(collection="ROUTING_ENTRY")
@ApiModel("路由集对象")
public class RoutingTablePO extends COWAuditBaseEntity implements ZeeCustomValidation {

    public static final String GLOBAL = "__GLOBAL";
    public static final String IMPLICIT_DEFAULT = "__IMPLICIT_DEFAULT";
    @JsonIgnore
    private String uuid;
    @ApiModelProperty("路由集名字")
    private String name;
    @ApiModelProperty("描述")
    private String description;
    private ConfigLinePO lineNumbers;
    @ApiModelProperty("接口组名，拿华为的举例：当路由集表示vrf时，该字段为 name + _vrf_interface_group")
    private String interfaceGroupName;

    private RoutingTableType ip4RoutingType;

    private RoutingTableType ip6RoutingType;
    @ApiModelProperty("是否为默认路由集")
    private boolean isDefault;
    @Valid
    @ApiModelProperty("路由信息对象集")
    private List<RoutingEntryPO> routingEntries;

    private List<RouteTarget> routeTargets;

    public RoutingTablePO() {
    }

    public RoutingTablePO(String uuid, String name, String description, ConfigLinePO lineNumbers, String interfaceGroupName, RoutingTableType ip4RoutingTableType, RoutingTableType ip6RoutingTableType, boolean isDefault, List<RoutingEntryPO> routingEntries) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.lineNumbers = lineNumbers;
        this.interfaceGroupName = interfaceGroupName;
        this.ip4RoutingType = ip4RoutingTableType;
        this.ip6RoutingType = ip6RoutingTableType;
        this.isDefault = isDefault;
        this.routingEntries = routingEntries;
    }

    public RoutingTablePO(String name, String description, ConfigLinePO lineNumbers, String interfaceGroupName, RoutingTableType ip4RoutingType, RoutingTableType ip6RoutingType, boolean isDefault, List<RoutingEntryPO> routingEntries) {
        this.name = name;
        this.description = description;
        this.lineNumbers = lineNumbers;
        this.interfaceGroupName = interfaceGroupName;
        this.ip4RoutingType = ip4RoutingType;
        this.ip6RoutingType = ip6RoutingType;
        this.isDefault = isDefault;
        this.routingEntries = routingEntries;
    }


    @JsonProperty
    public String getUuid() {
        return this.uuid;
    }

    @JsonIgnore
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public boolean isDefault() {
        return this.isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void addRoutingEntry(RoutingEntryPO route) {
        if (this.routingEntries == null) {
            this.routingEntries = new ArrayList<>();
        }
        this.routingEntries.add(route);
    }

    public void addRoutingEntries(List<RoutingEntryPO> routes) {
        if (this.routingEntries == null) {
            this.routingEntries = new ArrayList<>();
        }
        this.routingEntries.addAll(routes);
    }

    public RoutingTableType getIp6RoutingType() {
        return this.ip6RoutingType;
    }

    public void setIp6RoutingType(RoutingTableType ip6RoutingType) {
        this.ip6RoutingType = ip6RoutingType;
    }

    public void setIp4RoutingType(RoutingTableType ip4RoutingType) {
        this.ip4RoutingType = ip4RoutingType;
    }

    public List<RouteTarget> getRouteTargets() {
        return this.routeTargets;
    }

    public void setRouteTargets(List<RouteTarget> routeTargets) {
        this.routeTargets = routeTargets;
    }

    public void addRouteTarget(RouteTarget rt) {
        if (rt == null) {
            return;
        }
        if (this.routeTargets == null) {
            this.routeTargets = new ArrayList<>();
        }
        this.routeTargets.add(rt);
    }

    @Override
    public List<ZeeError> validate() {
        if ((this.ip4RoutingType == null) && (this.ip6RoutingType == null)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_ROUTING_TABLE_TYPE_MISSING, null, "Routing table '" + this.name + "' is missing routing type.");

            return Collections.singletonList(mye);
        }

        return null;
    }

    public static enum RoutingTableType {
        CONFIG_DYNAMIC,
        CONFIG_STATIC,
        RUNNING
    }

    public static class RouteTarget {
        private RoutingTablePO.RouteTargetAction action;
        private List<String> communities;

        private RouteTarget() {
        }

        public RouteTarget(RoutingTablePO.RouteTargetAction action, List<String> communities) {
            this.action = action;
            this.communities = communities;
        }

        public RoutingTablePO.RouteTargetAction getAction() {
            return this.action;
        }

        public void setAction(RoutingTablePO.RouteTargetAction action) {
            this.action = action;
        }

        public List<String> getCommunities() {
            return this.communities;
        }

        public void setCommunities(List<String> communities) {
            this.communities = communities;
        }

        public void addCommunity(String community) {
            if (community == null) {
                return;
            }

            if (this.communities == null) {
                this.communities = new ArrayList<>();
            }
            this.communities.add(community);
        }
    }

    public static enum RouteTargetAction {
        IMPORT,
        EXPORT,
        BOTH
    }


}

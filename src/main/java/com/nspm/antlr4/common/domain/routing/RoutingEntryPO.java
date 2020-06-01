package com.nspm.antlr4.common.domain.routing;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.ip.IP4AddressPO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;
import com.nspm.antlr4.common.domain.ip.IPAddressPO;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;
import com.nspm.antlr4.common.interfaces.DeviceObject;
import com.nspm.antlr4.common.interfaces.UuidHolder;
import com.nspm.antlr4.common.utils.DiffIgnoreFields;
import com.nspm.antlr4.validation.ZeeCustomValidation;
import com.fasterxml.jackson.annotation.JsonView;
import com.nspm.antlr4.validation.ZeeCustomValidation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/9
 */
@Data
@Document(collection = "ROUTING_ENTRY")
@CompoundIndexes({@CompoundIndex(name = "uuid_idx", def = "{'uuid': 1}", unique = true), @CompoundIndex(name = "deviceUuid_name_idx", def = "{'deviceUuid': 1, 'name': -1 }")})
public class RoutingEntryPO implements ZeeCustomValidation, DeviceObject, UuidHolder, Serializable {

    @ApiModelProperty("创建时间")
    @CreatedDate
    protected Date createdTime;
    @ApiModelProperty("最后修改时间")
    @LastModifiedDate
    protected Date lastModifiedTime;
    private String id;
    private Long version;
    @JsonView({DiffIgnoreFields.class})
    private String uuid;
    @JsonView({DiffIgnoreFields.class})
    private String deviceUuid;
    @JsonView({DiffIgnoreFields.class})
    private String routingTableUuid;
    @ApiModelProperty("路由类型")
    private RoutingEntryType type;
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("ip4目的网络地址")
    private IP4AddressPO ip4Prefix;
    @ApiModelProperty("ip6目的网络地址")
    private IP6AddressPO ip6Prefix;
    @ApiModelProperty("掩码长度")
    private Integer maskLength = null;
    @ApiModelProperty(" ")
    private String subnetGroupName;
    @ApiModelProperty("优先级")
    private Integer distance;
    @ApiModelProperty("路由度量值,开销")
    private Integer metric;
    @NotNull
    @Valid
    @ApiModelProperty("下一跳")
    private NextHopPO nextHop;
    @Transient
    @JsonView({DiffIgnoreFields.class})
    @ApiModelProperty("行号")
    private ConfigLinePO lineNumbers;
    @ApiModelProperty("路由协议")
    private String routingProtocol;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("是否禁用")
    private Boolean inactive;

    public RoutingEntryPO() {
    }

    public RoutingEntryPO(RoutingEntryType type, IP4AddressPO ip4Prefix, int maskLength, Integer distance, NextHopPO nextHop, ConfigLinePO lineNumbers, String name) {
        this.type = type;
        this.ip4Prefix = ip4Prefix;
        this.maskLength = maskLength;
        this.distance = distance;
        this.nextHop = nextHop;
        this.lineNumbers = lineNumbers;
        this.name = name;
    }

    public RoutingEntryPO(RoutingEntryType type, IP6AddressPO ip6Prefix, int maskLength, Integer distance, NextHopPO nextHop, ConfigLinePO lineNumbers, String name) {
        this.type = type;
        this.ip6Prefix = ip6Prefix;
        this.maskLength = maskLength;
        this.distance = distance;
        this.nextHop = nextHop;
        this.lineNumbers = lineNumbers;
        this.name = name;
    }

    public RoutingEntryPO(RoutingEntryType type, String subnetGroupName, Integer distance, NextHopPO nextHop, ConfigLinePO lineNumbers, String name) {
        this.type = type;
        this.subnetGroupName = subnetGroupName;
        this.distance = distance;
        this.nextHop = nextHop;
        this.lineNumbers = lineNumbers;
        this.name = name;
    }

    public static Comparator<RoutingEntryPO> SORT_ROUTE = (left, right) -> {
        IPTypeEnum leftType = left.getIPType();
        IPTypeEnum rightType = right.getIPType();

        if ((leftType == IPTypeEnum.IP4) && (rightType == IPTypeEnum.IP6)) {
            return -1;
        }

        if ((leftType == IPTypeEnum.IP6) && (rightType == IPTypeEnum.IP4)) {
            return 1;
        }

        if ((right.maskLength == null) || (left.maskLength == null)) {
            throw new IllegalArgumentException("Cannot compare routing entry without mask length");
        }

        int val = Integer.compare(right.maskLength, left.maskLength);
        if (val != 0) {
            return val;
        }

        if (leftType == IPTypeEnum.IP4) {
            val = left.ip4Prefix.compareTo(right.ip4Prefix);
            return val;
        } else {
            val = left.ip6Prefix.compareTo(right.ip6Prefix);
            return val;
        }

    };

    public RoutingEntryPO shallowCopy() {
        RoutingEntryPO result = new RoutingEntryPO();

        result.type = this.type;
        result.name = this.name;
        result.ip4Prefix = this.ip4Prefix;
        result.ip6Prefix = this.ip6Prefix;
        result.maskLength = this.maskLength;
        result.subnetGroupName = this.subnetGroupName;
        result.distance = this.distance;
        result.metric = this.metric;
        result.nextHop = this.nextHop;
        result.lineNumbers = this.lineNumbers;
        result.routingProtocol = this.routingProtocol;
        result.description = this.description;

        result.uuid = this.uuid;
        result.deviceUuid = this.deviceUuid;
        result.routingTableUuid = this.routingTableUuid;
        result.inactive = this.inactive;

        return result;
    }

    public IP4AddressPO getIp4Prefix() {
        return this.ip4Prefix;
    }

    public void setIp4Prefix(IP4AddressPO ip4Prefix) {
        this.ip4Prefix = ip4Prefix;
    }

    public IP6AddressPO getIp6Prefix() {
        return this.ip6Prefix;
    }

    public void setIp6Prefix(IP6AddressPO ip6Prefix) {
        this.ip6Prefix = ip6Prefix;
    }

    public IPAddressPO getPrefix() {
        return this.ip4Prefix != null ? this.ip4Prefix : this.ip6Prefix;
    }

    public IPTypeEnum getIPType() {
        if (this.ip4Prefix != null) {
            return IPTypeEnum.IP4;
        }
        if (this.ip6Prefix != null) {
            return IPTypeEnum.IP6;
        }
        throw new IllegalArgumentException("Both ip4Prefix and ip6Prefix are null.");
    }

    public int getMaskLength() {
        if (this.maskLength != null) {
            return this.maskLength;
        }
        return -1;
    }

    public void setMaskLength(Integer maskLength) {
        this.maskLength = maskLength;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public NextHopPO getNextHop() {
        return this.nextHop;
    }

    public void setNextHop(NextHopPO nextHop) {
        this.nextHop = nextHop;
    }

    @Override
    public ConfigLinePO getLineNumbers() {
        return this.lineNumbers;
    }

    @Override
    public void setLineNumbers(ConfigLinePO lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoutingEntryType getType() {
        return this.type;
    }

    public void setType(RoutingEntryType type) {
        this.type = type;
    }

    public String getSubnetGroupName() {
        return this.subnetGroupName;
    }

    public void setSubnetGroupName(String subnetGroupName) {
        this.subnetGroupName = subnetGroupName;
    }

    @Override
    public String getUuid() {
        return this.uuid;
    }

    @Override
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public DeviceObjectTypeEnum getDeviceObjectType() {
        return DeviceObjectTypeEnum.ROUTING_ENTRY;
    }

    @Override
    public String getDeviceUuid() {
        return this.deviceUuid;
    }

    @Override
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public String getRoutingTableUuid() {
        return this.routingTableUuid;
    }

    public void setRoutingTableUuid(String routingTableUuid) {
        this.routingTableUuid = routingTableUuid;
    }

    public Integer getMetric() {
        return this.metric;
    }

    public void setMetric(Integer metric) {
        this.metric = metric;
    }

    public String getRoutingProtocol() {
        return this.routingProtocol;
    }

    public void setRoutingProtocol(String routingProtocol) {
        this.routingProtocol = routingProtocol;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInactive() {
        return this.inactive;
    }

    public boolean isInactive() {
        if (this.inactive != null) {
            return this.inactive;
        }
        return false;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public String toString() {
        return "RoutingEntry [" + (this.type != null ? "type=" + this.type + ", " : "") + (this.name != null ? "name=" + this.name + ", " : "") + (this.ip4Prefix != null ? "ip4Prefix=" + this.ip4Prefix + ", " : "") + (this.ip6Prefix != null ? "ip6Prefix=" + this.ip6Prefix + ", " : "") + (this.maskLength != null ? "maskLength=" + this.maskLength + ", " : "") + (this.subnetGroupName != null ? "subnetGroupName=" + this.subnetGroupName + ", " : "") + (this.distance != null ? "distance=" + this.distance + ", " : "") + (this.nextHop != null ? "nextHop=" + this.nextHop + ", " : "") + (this.lineNumbers != null ? "lineNumbers=" + this.lineNumbers : "") + "]";
    }

    @Override
    public List<ZeeError> validate() {
        List<ZeeError> result = new ArrayList();
        if (this.subnetGroupName != null) {
            return result;
        }

        if ((this.ip4Prefix == null) && (this.ip6Prefix == null)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_ROUTE_MISSING_PREFIX, null, "Route entry missing IP prefix:" + toString());
            result.add(mye);
        }

        if ((this.maskLength == null) || (this.maskLength.intValue() == -1)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_ROUTE_MISSING_MASK_LENGTH, null, "Route entry missing mask length:" + toString());
            result.add(mye);
            return result;
        }

        if ((this.ip4Prefix != null) && (
                (this.maskLength.intValue() < 0) || (this.maskLength.intValue() > 32))) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_IP_MASK_LENGTH_OUT_OF_RANGE, null, "Route entry mask length out of range [0-32]:" + toString());
            result.add(mye);
        }

        /*     */
        if ((this.ip6Prefix != null) && (
                (this.maskLength.intValue() < 0) || (this.maskLength.intValue() > 128))) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_IP_MASK_LENGTH_OUT_OF_RANGE, null, "Route entry mask length out of range [0-128]:" + toString());
            result.add(mye);
        }

        /*     */
        if ((this.ip4Prefix != null) && (this.ip6Prefix != null)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_ROUTE_MORE_THAN_ONE_PREFIX_IP, null, "Route entry has both IP4 and IP6 prefix. Only one should be set:" + toString());
            result.add(mye);
        }

        if ((this.ip4Prefix != null) && (this.nextHop != null) && (this.nextHop.getIp6Gateway() != null)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_ROUTE_IP_TYPE_MISMATCH, null, "Route entry has IP4 prefix but IP6 gateway:" + toString());
            result.add(mye);
        }

        if ((this.ip6Prefix != null) && (this.nextHop != null) && (this.nextHop.getIp4Gateway() != null)) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_ROUTE_IP_TYPE_MISMATCH, null, "Route entry has IP6 prefix but IP4 gateway:" + toString());
            result.add(mye);
        }

        return result;
    }

    public enum RoutingEntryType {
        /**
         * 静态路由
         */
        STATIC,
        /**
         * 动态路由
         */
        DYNAMIC,
        /**
         * 直链路由
         */
        CONNECTED
    }


}

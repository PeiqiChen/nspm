package com.nspm.antlr4.common.deviceInterface;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.ip.IP4AddressPO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;
import com.nspm.antlr4.common.domain.ip.IPAddressPO;
import com.nspm.antlr4.common.domain.routing.RoutingTablePO;
import com.nspm.antlr4.common.domain.vpn.VPNRefPO;
import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.enums.InterfaceLayerEnum;
import com.nspm.antlr4.common.enums.VPNTypeEnum;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import com.nspm.antlr4.common.utils.ObjectUtil;
import com.nspm.antlr4.common.utils.UUIDUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel(value = "接口组，与设备接口DeviceInterfacePO对应，包含子网uuid、接口的组合")
@Data
public class NetInterfacePO implements Serializable {

    @JsonIgnore
    private String uuid;
    private IPTypeEnum ipType = null;
    private InterfaceType type = null;

    private String name;

    private String displayName;

    private String description;

    private List<String> tags;

    private ConfigLinePO lineNumbers;

    private List<VPNRefPO> vpnRefs;
    private EnumSet<NetInterfaceOption> options;
    private EnumSet<IPAddressInfoPO.IPAddressOption> ipOptions;
    private String deviceInterfaceName;
    private IP4AddressPO ip4Address;
    private Integer ip4MaskLength;
    private IP6AddressPO ip6Address;
    private Integer ip6MaskLength;
    @JsonIgnore
    private String ip4SubnetUuid;
    @JsonIgnore
    private String ip6SubnetUuid;
    private Integer securityLevel;
    private Boolean allowDuplicateIP = null;
    private Boolean isLoopback = null;
    private Boolean isManagement = null;

    private List<String> inboundRuleListRefs = null;

    private List<String> outboundRuleListRefs = null;

    /*     */
    private String vpnName = null;
    private VPNTypeEnum vpnType = null;

    /*     */
    /*     */
    /*     */
    /*     */
    private String markTag = null;

    private InterfaceLayerEnum layer = null;

    private RoutingTablePO.RouteTargetAction routeTargetAction;

    /*     */
    private NetInterfacePO() {
    }

    /*     */
    public NetInterfacePO(String name, String displayName, String description, ConfigLinePO lineNumbers, String deviceInterfaceName) {
        this.uuid = UUIDUtil.getUuid();
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.lineNumbers = lineNumbers;
        this.deviceInterfaceName = deviceInterfaceName;
    }

    /*     */
    /*     */
    /*     */
    /*     */
    public NetInterfacePO(IPTypeEnum ipType, InterfaceType type, String name, String displayName, String description, List<String> tagsList, ConfigLinePO lineNumbers, String deviceInterfaceName, IP4AddressPO ip4Address, Integer ip4MaskLength, IP6AddressPO ip6Address, Integer ip6MaskLength, Integer securityLevel) {
        if (name == null) {
            throw new IllegalArgumentException("Interface name can not be null");
        }

        this.uuid = UUIDUtil.getUuid();
        this.ipType = ipType;
        this.type = type;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
        this.tags = tagsList;
        this.lineNumbers = lineNumbers;
        this.deviceInterfaceName = deviceInterfaceName;
        this.ip4Address = ip4Address;
        this.ip4MaskLength = ip4MaskLength;
        this.ip6Address = ip6Address;
        this.ip6MaskLength = ip6MaskLength;
        this.securityLevel = securityLevel;
    }

    public NetInterfacePO cloneIt() {
        NetInterfacePO newif = new NetInterfacePO(this.ipType, this.type, this.name, this.displayName, this.description, this.tags, this.lineNumbers, this.deviceInterfaceName, this.ip4Address, this.ip4MaskLength, this.ip6Address, this.ip6MaskLength, this.securityLevel);

        newif.uuid = this.uuid;
        newif.isLoopback = this.isLoopback;
        newif.isManagement = this.isManagement;
        newif.allowDuplicateIP = this.allowDuplicateIP;
        newif.ip4SubnetUuid = this.ip4SubnetUuid;
        newif.ip6SubnetUuid = this.ip6SubnetUuid;
        newif.vpnRefs = this.vpnRefs;
        newif.options = this.options;
        newif.ipOptions = this.ipOptions;
        newif.inboundRuleListRefs = this.inboundRuleListRefs;
        newif.outboundRuleListRefs = this.outboundRuleListRefs;
        newif.vpnName = this.vpnName;
        newif.vpnType = this.vpnType;
        newif.markTag = this.markTag;
        newif.layer = this.layer;
        newif.routeTargetAction = this.routeTargetAction;

        return newif;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getDescription() {
        return this.description;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public ConfigLinePO getLineNumbers() {
        return this.lineNumbers;
    }

    public String getDeviceInterfaceName() {
        return this.deviceInterfaceName;
    }

    public boolean isAllowDuplicateIP() {
        return this.allowDuplicateIP == null ? false : this.allowDuplicateIP.booleanValue();
    }

    public IP4AddressPO getIp4Address() {
        return this.ip4Address;
    }

    public int getIp4MaskLength() {
        return this.ip4MaskLength == null ? -1 : this.ip4MaskLength.intValue();
    }

    public IP6AddressPO getIp6Address() {
        return this.ip6Address;
    }

    public int getIp6MaskLength() {
        return this.ip6MaskLength == null ? -1 : this.ip6MaskLength.intValue();
    }

    public IPAddressPO getIpAddress() {
        if (this.ip4Address != null) {
            return this.ip4Address;
        }
        return this.ip6Address;
    }

    @JsonProperty
    public String getIp4SubnetUuid() {
        return this.ip4SubnetUuid;
    }

    @JsonProperty
    public String getIp6SubnetUuid() {
        return this.ip6SubnetUuid;
    }

    public int getSecurityLevel() {
        return this.securityLevel == null ? -1 : this.securityLevel.intValue();
    }

    public boolean isLoopback() {
        return this.isLoopback == null ? false : this.isLoopback.booleanValue();
    }

    public boolean isManagement() {
        return this.isManagement == null ? false : this.isManagement.booleanValue();
    }

    public IPTypeEnum getIpType() {
        return this.ipType;
    }

    public InterfaceType getType() {
        return this.type;
    }

    public String getMarkTag() {
        return this.markTag;
    }

    public void setMarkTag(String markTag) {
        this.markTag = markTag;
    }

    @JsonIgnore
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonIgnore
    public void setIp4SubnetUuid(String ip4SubnetId) {
        this.ip4SubnetUuid = ip4SubnetId;
    }

    @JsonIgnore
    public void setIp6SubnetUuid(String ip6SubnetId) {
        this.ip6SubnetUuid = ip6SubnetId;
    }

    public void setType(InterfaceType type) {
        this.type = type;
    }

    public List<VPNRefPO> getVpnRefs() {
        return this.vpnRefs;
    }

    public void setVpnRefs(List<VPNRefPO> vpnRefs) {
        this.vpnRefs = vpnRefs;
    }

    public void addVpnRefs(VPNRefPO... vpnRefArr) {
        if ((vpnRefArr == null) || (vpnRefArr.length == 0)) {
            return;
        }

        if (this.vpnRefs == null) {
            this.vpnRefs = new ArrayList();
        }
        this.vpnRefs.addAll(Arrays.asList(vpnRefArr));
    }

    public void setIpType(IPTypeEnum ipType) {
        this.ipType = ipType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setLineNumbers(ConfigLinePO lineNumbers) {
        this.lineNumbers = lineNumbers;
    }

    public void setDeviceInterfaceName(String deviceInterfaceName) {
        this.deviceInterfaceName = deviceInterfaceName;
    }

    public void setIp4Address(IP4AddressPO ip4Address) {
        this.ip4Address = ip4Address;
    }

    public void setIp4MaskLength(Integer ip4MaskLength) {
        this.ip4MaskLength = ip4MaskLength;
    }

    public void setIp6Address(IP6AddressPO ip6Address) {
        this.ip6Address = ip6Address;
    }

    public void setIp6MaskLength(Integer ip6MaskLength) {
        this.ip6MaskLength = ip6MaskLength;
    }

    public void setSecurityLevel(Integer securityLevel) {
        this.securityLevel = securityLevel;
    }

    public void setAllowDuplicateIP(Boolean allowDuplicateIP) {
        this.allowDuplicateIP = allowDuplicateIP;
    }

    public void setIsLoopback(Boolean isLoopback) {
        this.isLoopback = isLoopback;
    }

    public void setIsManagement(Boolean isManagement) {
        this.isManagement = isManagement;
    }

    public EnumSet<NetInterfaceOption> getOptions() {
        return this.options;
    }

    public void addOptions(NetInterfaceOption... opts) {
        if (opts == null) {
            return;
        }

        if (this.options == null) {
            this.options = EnumSet.noneOf(NetInterfaceOption.class);
        }

        this.options.addAll(Arrays.asList(opts));
    }

    /*     */
    public EnumSet<IPAddressInfoPO.IPAddressOption> getIpOptions() {
        return this.ipOptions;
    }

    public void setIpOptions(EnumSet<IPAddressInfoPO.IPAddressOption> ipOptions) {
        this.ipOptions = ipOptions;
    }

    public List<String> getInboundRuleListRefs() {
        return this.inboundRuleListRefs;
    }

    public void setInboundRuleListRefs(List<String> inboundRuleListRefs) {
        this.inboundRuleListRefs = inboundRuleListRefs;
    }

    public List<String> getOutboundRuleListRefs() {
        return this.outboundRuleListRefs;
    }

    public void setOutboundRuleListRefs(List<String> outboundRuleListRefs) {
        this.outboundRuleListRefs = outboundRuleListRefs;
    }

    public String getVpnName() {
        return this.vpnName;
    }

    public void setVpnName(String ipsecUuid) {
        this.vpnName = ipsecUuid;
    }

    public VPNTypeEnum getVpnType() {
        return this.vpnType;
    }

    public void setVpnType(VPNTypeEnum vpnType) {
        this.vpnType = vpnType;
    }

    public InterfaceLayerEnum getLayer() {
        return this.layer;
    }

    public void setLayer(InterfaceLayerEnum layer) {
        this.layer = layer;
    }

    public RoutingTablePO.RouteTargetAction getRouteTargetAction() {
        return this.routeTargetAction;
    }

    public void setRouteTargetAction(RoutingTablePO.RouteTargetAction routeTargetAction) {
        this.routeTargetAction = routeTargetAction;
    }

    /*     */
    /*     */
    /*     */
    public boolean isRealIP() {
        if ((this.ip4Address == null) && (this.ip6Address == null)) {
            return false;
        }

        if ((this.type == InterfaceType.L2_TUNNEL) || (this.type == InterfaceType.TUNNEL)) {
            return false;
        }

        if ((this.ip6Address != null) && (this.ip6Address.isLinkLocalAddr())) {
            return false;
        }

        return true;
    }

    public InterfaceLayerEnum determineLayer() {
        if (this.layer != null) {
            return this.layer;
        }

        /*     */
        /*     */
        if ((this.type == InterfaceType.MANUAL_LINK) && (!isRealIP())) {
            return InterfaceLayerEnum.LAYER2;
        }

        if (ObjectUtil.containsAny(this.options, new NetInterfaceOption[]{NetInterfaceOption.LAYER2})) {
            return InterfaceLayerEnum.LAYER2;
        }

        /*     */
        return InterfaceLayerEnum.LAYER3;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (this.name != null) {
            builder.append(this.name).append(", ");
        }
        if (this.ip4Address != null) {
            builder.append(this.ip4Address).append("/");
            builder.append(this.ip4MaskLength);
        }

        if (this.ip6Address != null) {
            builder.append(" ");
            builder.append(this.ip6Address).append("/");
            builder.append(this.ip6MaskLength);
        }

        /*     */
        builder.append("]");
        return builder.toString();
    }

    public boolean containsIPAddress(IPAddressPO ip) {
        if (ip == null) {
            return false;
        }

        IPTypeEnum ipType = ip.getType();

        IPAddressRange myRange = getIPRange(ipType);
        if (myRange != null) {
            return myRange.contains(ip);
        }
        return false;
    }

    /*     */
    /*     */
    public IPAddressRange getIPRange(IPTypeEnum ipType) {
        IPAddressRange result = null;

        if ((ipType == IPTypeEnum.IP4) &&
                (this.ip4Address != null) && (this.ip4MaskLength.intValue() >= 0)) {
            result = this.ip4Address.toRange(this.ip4MaskLength.intValue());
        }

        /*     */
        if ((ipType == IPTypeEnum.IP6) &&
                (this.ip6Address != null) && (!this.ip6Address.isLinkLocalAddr()) && (this.ip6MaskLength.intValue() >= 0)) {
            result = this.ip6Address.toRange(this.ip6MaskLength.intValue());
        }

        /*     */
        return result;
    }

    public static Comparator<NetInterfacePO> SORT_BY_UUID = new Comparator<NetInterfacePO>() {
        @Override
        public int compare(NetInterfacePO left, NetInterfacePO right) {
            return left.getUuid().compareTo(right.getUuid());
        }
    };

    public static enum InterfaceType {
        REG,
        TUNNEL,
        L2_TUNNEL,
        AD_HOC,
        MANUAL_LINK;

        private InterfaceType() {
        }
    }

    public static enum NetInterfaceOption {
        LAYER2,
        L2TP,
        IPSEC,
        IP6_LINK_LOCAL,
        VIP;

        private NetInterfaceOption() {
        }
    }

}

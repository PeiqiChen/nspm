package com.nspm.antlr4.common.deviceInterface;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.domain.routing.RoutingTablePO;
import com.nspm.antlr4.common.domain.vpn.VPNRefPO;
import com.nspm.antlr4.common.enums.InterfaceLayerEnum;
import com.nspm.antlr4.common.utils.Assertion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author luwei
 * @date 2019/9/9
 */
@ApiModel(value = "设备接口")
@Data
public class DeviceInterfacePO implements Serializable {
    @NotBlank
    @ApiModelProperty("接口名称")
    private String name;
    private String displayName;
    @ApiModelProperty("接口描述")
    private String description;
    private List<String> tags;
    @ApiModelProperty("行号")
    private ConfigLinePO lineNumbers;
    @Valid
    @ApiModelProperty("ip地址信息集")
    private List<IPAddressInfoPO> ipAddresses;
    private List<VPNRefPO> vpnRefs;
    @ApiModelProperty("特别的连接")
    private List<AdhocLinkPO> adhocLinks;
    private Integer securityLevel = null;
    @ApiModelProperty("指示是否是一个环回接口")
    private Boolean isLoopback = null;
    @ApiModelProperty("指示接口是否被禁用")
    private Boolean isShutdown = null;
    @ApiModelProperty("指示是否是一个管理接口")
    private Boolean isManagement = null;
    @ApiModelProperty("别名")
    private String alias = null;
    @ApiModelProperty("指定了入接口的ACL名称集合。当报文进入接口前，会先进行ACL报文过滤。满足指定条件的报文才能进入接口")
    private List<String> inboundRuleListRefs = null;

    private List<String> outboundRuleListRefs = null;

    private InterfaceLayerEnum layer = null;

    private Boolean isImplicit = null;

    private RoutingTablePO.RouteTargetAction routeTargetAction = null;

    public void addIPAddress(IPAddressInfoPO ipAddr) {
        Assertion.notNull(ipAddr);
        if (this.ipAddresses == null) {
            this.ipAddresses = new ArrayList<>(4);
        }
        this.ipAddresses.add(ipAddr);
    }

    public void addVpnRefs(VPNRefPO vpnRef) {
        if (vpnRef != null) {
            if (this.vpnRefs == null) {
                this.vpnRefs = new ArrayList<>();
            }
            this.vpnRefs.add(vpnRef);
        }
    }

    public void addAdhocLink(AdhocLinkPO link) {
        if (link != null) {
            if (this.adhocLinks == null) {
                this.adhocLinks = new ArrayList();
            }
            this.adhocLinks.add(link);
        }
    }

    public void addInboundRuleListRef(String inboundAcl) {
        if (inboundAcl != null && !inboundAcl.trim().isEmpty()) {
            if (this.inboundRuleListRefs == null) {
                this.inboundRuleListRefs = new ArrayList<>(3);
            }
            this.inboundRuleListRefs.add(inboundAcl);
        }
    }

    public void addOutboundRuleListRef(String outboundAcl) {
        if (outboundAcl != null && !outboundAcl.trim().isEmpty()) {
            if (this.outboundRuleListRefs == null) {
                this.outboundRuleListRefs = new ArrayList<>(3);
            }
            this.outboundRuleListRefs.add(outboundAcl);
        }
    }

    public boolean hasModelImpactingChange(DeviceInterfacePO oldObj) {
        if (this == oldObj) {
            return false;
        } else if (oldObj == null) {
            return true;
        } else {
            if (this.ipAddresses == null) {
                if (oldObj.ipAddresses != null) {
                    return true;
                }
            } else {
                if (oldObj.ipAddresses == null) {
                    return true;
                }

                Iterator<IPAddressInfoPO> e1 = this.ipAddresses.iterator();
                Iterator e2 = oldObj.ipAddresses.iterator();

                while(e1.hasNext() && e2.hasNext()) {
                    IPAddressInfoPO o1 = e1.next();
                    IPAddressInfoPO o2 = (IPAddressInfoPO)e2.next();
                    if (o1 != null || o2 != null) {
                        if (o1 == null || o2 == null) {
                            return true;
                        }

                        if (o1.hasModelImpactingChange(o2)) {
                            return true;
                        }
                    }
                }

                if (e1.hasNext() || e2.hasNext()) {
                    return true;
                }
            }

            if (this.isLoopback == null) {
                if (oldObj.isLoopback != null) {
                    return true;
                }
            } else if (!this.isLoopback.equals(oldObj.isLoopback)) {
                return true;
            }

            if (this.isShutdown == null) {
                if (oldObj.isShutdown != null) {
                    return true;
                }
            } else if (!this.isShutdown.equals(oldObj.isShutdown)) {
                return true;
            }

            if (this.name == null) {
                if (oldObj.name != null) {
                    return true;
                }
            } else if (!this.name.equals(oldObj.name)) {
                return true;
            }

            if (this.securityLevel == null) {
                if (oldObj.securityLevel != null) {
                    return true;
                }
            } else if (!this.securityLevel.equals(oldObj.securityLevel)) {
                return true;
            }

            if (this.vpnRefs == null) {
                if (oldObj.vpnRefs != null) {
                    return true;
                }
            } else if (!this.vpnRefs.equals(oldObj.vpnRefs)) {
                return true;
            }

            if (this.adhocLinks == null) {
                if (oldObj.adhocLinks != null) {
                    return true;
                }
            } else if (!this.adhocLinks.equals(oldObj.adhocLinks)) {
                return true;
            }

            if (this.inboundRuleListRefs == null) {
                if (oldObj.inboundRuleListRefs != null) {
                    return true;
                }
            } else if (!this.inboundRuleListRefs.equals(oldObj.inboundRuleListRefs)) {
                return true;
            }

            if (this.outboundRuleListRefs == null) {
                if (oldObj.outboundRuleListRefs != null) {
                    return true;
                }
            } else if (!this.outboundRuleListRefs.equals(oldObj.outboundRuleListRefs)) {
                return true;
            }

            if (this.layer == null) {
                if (oldObj.layer != null) {
                    return true;
                }
            } else if (this.layer != oldObj.layer) {
                return true;
            }

            if (this.routeTargetAction == null) {
                if (oldObj.routeTargetAction != null) {
                    return true;
                }
            } else if (this.routeTargetAction != oldObj.routeTargetAction) {
                return true;
            }
            return false;
        }
    }
}

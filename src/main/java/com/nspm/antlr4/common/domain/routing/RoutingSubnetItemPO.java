package com.nspm.antlr4.common.domain.routing;

import com.nspm.antlr4.common.domain.ip.IP4AddressPO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;

/**
 * @author luwei
 * @date 2019/9/11
 */
public class RoutingSubnetItemPO {

    private IP4AddressPO ip4Prefix;
    private Integer ip4Length;
    private IP6AddressPO ip6Prefix;
    private Integer ip6Length;

    private RoutingSubnetItemPO() {
    }

    public RoutingSubnetItemPO(IP4AddressPO ip4Prefix, Integer ip4Length) {
        this.ip4Prefix = ip4Prefix;
        this.ip4Length = ip4Length;
    }

    public RoutingSubnetItemPO(IP6AddressPO ip6Prefix, Integer ip6Length) {
        this.ip6Prefix = ip6Prefix;
        this.ip6Length = ip6Length;
    }

    public IP4AddressPO getIp4Prefix() {
        return this.ip4Prefix;
    }

    public Integer getIp4Length() {
        return this.ip4Length;
    }

    public IP6AddressPO getIp6Prefix() {
        return this.ip6Prefix;
    }

    public Integer getIp6Length() {
        return this.ip6Length;
    }
}

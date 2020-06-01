package com.nspm.antlr4.common.enums;

import com.nspm.antlr4.common.domain.obj.ProtocolRangePO;
import com.nspm.antlr4.exceptions.ZeeRuntimeException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author luwei
 * @date 2019/9/5
 */
public enum ProtocolNameEnum {

    /**
     * 常见的IP协议
     */
    AH(51, "ahp"),
    EGP(8),
    EIGRP(88),
    ESP(50),
    GRE(47),
    ICMP(1),
    ICMP6(58, "icmpv6"),
    IGMP(2),
    IGRP(9),
    IP(ProtocolRangePO.ANY, "ipv6"),
    IP_IN_IP(4, "ipinip", "ipip"),
    IPSEC(50),
    NOS(94),
    OSPF(89),
    PCP(108),
    PIM(103),
    PPTP(47, "pptp-gre"),
    RSVP(46),
    SCTP(132),
    SNP(109),
    TCP(6),
    TCP_UDP(6, 17),
    UDP(17),
    VRRP(112);

    /**
     * 协议的端口
     */
    private ProtocolRangePO[] protoRanges;

    /**
     * 协议的别名
     */
    private String[] alias;

    private static Map<String, ProtocolNameEnum> nameMap;

    static {
        nameMap = new HashMap();
        Set<String> mykeys = new HashSet();
        for (ProtocolNameEnum myname : values()) {
            addMapEntry(myname.name(), myname, mykeys);

            String[] myalias = myname.getAlias();
            if (myalias != null) {
                for (String myval : myalias) {
                    addMapEntry(myval, myname, mykeys);
                }
            }
        }
    }

    ProtocolNameEnum(ProtocolRangePO range, String... alias) {
        this.protoRanges = new ProtocolRangePO[]{range};
        this.alias = alias;
    }

    ProtocolNameEnum(int val, String... alias) {
        this.protoRanges = new ProtocolRangePO[]{ProtocolRangePO.create(val)};
        this.alias = alias;
    }
    ProtocolNameEnum(int val1, int val2, String... alias) {
        this.protoRanges = new ProtocolRangePO[]{ProtocolRangePO.create(val1), ProtocolRangePO.create(val2)};
        this.alias = alias;
    }

    private static void addMapEntry(String key, ProtocolNameEnum proto, Set<String> existingKeys) {
        key = key.toLowerCase();
        key = key.replace('_', '-');
        if (existingKeys.contains(key)) {
            throw new ZeeRuntimeException("Duplicate name/alias with ProtocolName: " + key);
        }
        existingKeys.add(key);
        nameMap.put(key, proto);
    }

    public static ProtocolNameEnum lookup(String txt) {
        txt = txt.toLowerCase();
        txt = txt.replace('_', '-');
        return nameMap.get(txt);
    }

    public static Map<String, ProtocolNameEnum> getNameMap() {
        return nameMap;
    }

    public ProtocolRangePO[] getProtoRanges() {
        return this.protoRanges;
    }

    public String[] getAlias() {
        return this.alias;
    }

}

package com.nspm.antlr4.common.enums;

import com.nspm.antlr4.exceptions.ZeeRuntimeException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author luwei
 * @date 2019/9/5
 */
public enum ICMP6TypeEnum {

    ECHO(128, new String[0]),
    ECHO_REPLY(129, new String[0]),
    MEMBERSHIP_QUERY(130, new String[0]),
    MEMBERSHIP_REDUCTION(132, new String[0]),
    MEMBERSHIP_REPORT(131, new String[0]),
    NEIGHBOR_ADVERTISEMENT(136, new String[0]),
    NEIGHBOR_REDIRECT(137, new String[0]),
    NEIGHBOR_SOLICITATION(135, new String[0]),
    PACKET_TOO_BIG(2, new String[0]),
    PARAMETER_PROBLEM(4, new String[0]),
    ROUTER_ADVERTISEMENT(134, new String[0]),
    ROUTER_RENUMBERING(138, new String[0]),
    ROUTER_SOLICITATION(133, new String[0]),
    TIME_EXCEEDED(3, new String[0]),
    UNREACHABLE(1, new String[0]);

    private static Map<String, ICMP6TypeEnum> nameMap;

    static { nameMap = new HashMap();

        /*     */
        Set<String> mykeys = new HashSet();
        for (ICMP6TypeEnum myname : values()) {
            addMapEntry(myname.name(), myname, mykeys);

            String[] myalias = myname.getAlias();
            if (myalias != null) {
                for (String myval : myalias) {
                    addMapEntry(myval, myname, mykeys);
                }
            }
        }
    }

    private static void addMapEntry(String key, ICMP6TypeEnum icmp6Type, Set<String> existingKeys) {
        key = key.toLowerCase();
        key = key.replace('_', '-');

        if (existingKeys.contains(key)) {
            throw new ZeeRuntimeException("Duplicate name/alias with ICMP6TypeEnum: " + key);
        }
        existingKeys.add(key);

        /*     */
        nameMap.put(key, icmp6Type);
    }

    public static ICMP6TypeEnum lookup(String txt) {
        txt = txt.toLowerCase();
        txt = txt.replace('_', '-');

        return (ICMP6TypeEnum)nameMap.get(txt);
    }

    public static Map<String, ICMP6TypeEnum> getNameMap() {
        return nameMap;
    }

    /*     */
    /*     */
    private int typeNumber;

    private String[] alias;

    private ICMP6TypeEnum(int typeNumber, String... alias)
    {
        this.typeNumber = typeNumber;
        this.alias = alias;
    }

    public int getTypeNumber() {
        return this.typeNumber;
    }

    public String[] getAlias() {
        return this.alias;
    }

}

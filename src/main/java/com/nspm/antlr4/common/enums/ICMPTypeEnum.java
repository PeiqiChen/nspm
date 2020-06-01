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
public enum ICMPTypeEnum {
    ALTERNATE_ADDRESS(6),
    CONVERSION_ERROR(31),
    ECHO(8),
    ECHO_REPLY(0),
    INFORMATION_REPLY(16),
    INFORMATION_REQUEST(15),
    MASK_REPLY(18),
    MASK_REQUEST(17),
    MOBILE_REDIRECT(32),
    PARAMETER_PROBLEM(12),
    REDIRECT(5),
    ROUTER_ADVERTISEMENT(9),
    ROUTER_SOLICITATION(10),
    SOURCE_QUENCH(4),
    TIME_EXCEEDED(11),
    TIMESTAMP_REPLY(14),
    TIMESTAMP_REQUEST(13),
    TRACEROUTE(30),
    UNREACHABLE(3);

    private int typeNumber;
    private String[] alias;

    private static Map<String, ICMPTypeEnum> nameMap;

    static {
        nameMap = new HashMap();

        /*     */
        Set<String> mykeys = new HashSet();
        for (ICMPTypeEnum myname : values()) {
            addMapEntry(myname.name(), myname, mykeys);

            String[] myalias = myname.getAlias();
            if (myalias != null) {
                for (String myval : myalias) {
                    addMapEntry(myval, myname, mykeys);
                }
            }
        }
    }

    ICMPTypeEnum(int typeNumber, String... alias) {
        this.typeNumber = typeNumber;
        this.alias = alias;
    }

    private static void addMapEntry(String key, ICMPTypeEnum icmpType, Set<String> existingKeys) {
        key = key.toLowerCase();
        key = key.replace('_', '-');

        if (existingKeys.contains(key)) {
            throw new ZeeRuntimeException("Duplicate name/alias with ICMPTypeEnum: " + key);
        }
        existingKeys.add(key);

        nameMap.put(key, icmpType);
    }

    public static ICMPTypeEnum lookup(String txt) {
        txt = txt.toLowerCase();
        txt = txt.replace('_', '-');

        return nameMap.get(txt);
    }

    public static Map<String, ICMPTypeEnum> getNameMap() {
        return nameMap;
    }

    public int getTypeNumber() {
        return this.typeNumber;
    }

    public String[] getAlias() {
        return this.alias;
    }


}

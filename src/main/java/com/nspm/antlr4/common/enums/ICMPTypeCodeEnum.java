package com.nspm.antlr4.common.enums;

import com.nspm.antlr4.exceptions.ZeeRuntimeException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zc
 * @date 2019/10/15
 */
public enum ICMPTypeCodeEnum {
    ECHO(8, 0, new String[0]),
    ECHO_REPLY(0, 0, new String[0]),
    FRAGMENTNEED_DFSET(3, 4, new String[0]),
    HOST_REDIRECT(5, 1, new String[0]),
    HOST_TOS_REDIRECT(5, 3, new String[0]),
    HOST_UNREACHABLE(3, 1, new String[0]),
    INFORMATION_REPLY(16, 0, new String[0]),
    INFORMATION_REQUEST(15, 0, new String[0]),
    NET_REDIRECT(5, 0, new String[0]),
    NET_TOS_REDIRECT(5, 2, new String[0]),
    NET_UNREACHABLE(3, 0, new String[0]),
    PARAMETER_PROBLEM(12, 0, new String[0]),
    PORT_UNREACHABLE(3, 3, new String[0]),
    PROTOCOL_UNREACHABLE(3, 2, new String[0]),
    REASSEMBLY_TIMEOUT(11, 1, new String[0]),
    SOURCE_QUENCH(4, 0, new String[0]),
    SOURCE_ROUTE_FAILED(3, 5, new String[0]),
    TIMESTAMP_REPLY(14, 0, new String[0]),
    TIMESTAMP_REQUEST(13, 0, new String[0]),
    TTL_EXCEEDED(11, 0, new String[0]);

    private static Map<String, ICMPTypeCodeEnum> nameMap = new HashMap();
    private int typeNumber;
    private int codeNumber;
    private String[] alias;

    private static void addMapEntry(String key, ICMPTypeCodeEnum icmpType, Set<String> existingKeys) {
        key = key.toLowerCase();
        key = key.replace('_', '-');
        if (existingKeys.contains(key)) {
            throw new ZeeRuntimeException("Duplicate name/alias with ICMPType: " + key);
        } else {
            existingKeys.add(key);
            nameMap.put(key, icmpType);
        }
    }

    public static ICMPTypeCodeEnum lookup(String txt) {
        txt = txt.toLowerCase();
        txt = txt.replace('_', '-');
        return nameMap.get(txt);
    }

    public static Map<String, ICMPTypeCodeEnum> getNameMap() {
        return nameMap;
    }

    private ICMPTypeCodeEnum(int typeNumber, int codeNumber, String... alias) {
        this.typeNumber = typeNumber;
        this.codeNumber = codeNumber;
        this.alias = alias;
    }

    public int getTypeNumber() {
        return this.typeNumber;
    }

    public int getCodeNumber() {
        return this.codeNumber;
    }

    public String[] getAlias() {
        return this.alias;
    }

    static {
        Set<String> mykeys = new HashSet();
        ICMPTypeCodeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ICMPTypeCodeEnum myname = var1[var3];
            addMapEntry(myname.name(), myname, mykeys);
            String[] myalias = myname.getAlias();
            if (myalias != null) {
                String[] var6 = myalias;
                int var7 = myalias.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String myval = var6[var8];
                    addMapEntry(myval, myname, mykeys);
                }
            }
        }

    }
}

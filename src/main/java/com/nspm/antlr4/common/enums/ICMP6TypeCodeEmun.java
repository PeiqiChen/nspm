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
public enum ICMP6TypeCodeEmun {
    REDIRECT(137, 0, new String[0]),
    ECHO(128, 0, new String[0]),
    ECHOREPLY(129, 0, new String[0]),
    ERRHEADERFIELD(4, 0, new String[0]),
    FRAGTIMEEXCEEDED(3, 1, new String[0]),
    HOPLIMITEXCEEDED(3, 0, new String[0]),
    HOSTADMINPROHIB(1, 1, new String[0]),
    HOSTUNREACHABLE(1, 3, new String[0]),
    NEIGHBORADVERTISEMENT(136, 0, new String[0]),
    NEIGHBORSOLICITATION(135, 0, new String[0]),
    NETWORKUNREACHABLE(1, 0, new String[0]),
    PACKETTOOBIG(2, 0, new String[0]),
    PORTUNREACHABLE(1, 4, new String[0]),
    ROUTERADVERTISEMENT(134, 0, new String[0]),
    ROUTERSOLICITATION(133, 0, new String[0]),
    UNKNOWNIPV6OPT(4, 2, new String[0]),
    UNKNOWNNEXTHDR(4, 1, new String[0]);

    private static Map<String, ICMP6TypeCodeEmun> nameMap = new HashMap();
    private int typeNumber;
    private int codeNumber;
    private String[] alias;

    private static void addMapEntry(String key, ICMP6TypeCodeEmun icmpType, Set<String> existingKeys) {
        key = key.toLowerCase();
        key = key.replace('_', '-');
        if (existingKeys.contains(key)) {
            throw new ZeeRuntimeException("Duplicate name/alias with ICMPType: " + key);
        } else {
            existingKeys.add(key);
            nameMap.put(key, icmpType);
        }
    }

    public static ICMP6TypeCodeEmun lookup(String txt) {
        txt = txt.toLowerCase();
        txt = txt.replace('_', '-');
        return nameMap.get(txt);
    }

    public static Map<String, ICMP6TypeCodeEmun> getNameMap() {
        return nameMap;
    }

    private ICMP6TypeCodeEmun(int typeNumber, int codeNumber, String... alias) {
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
        ICMP6TypeCodeEmun[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ICMP6TypeCodeEmun myname = var1[var3];
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

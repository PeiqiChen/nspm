package com.nspm.antlr4.common.enums;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author luwei
 * @date 2019/9/5
 */
public enum PortOperatorEnum {
    // >
    GT(),
    // >=
    GE(),
    // <
    LT(),
    // <=
    LE(),
    // ==
    EQ("EQUAL"),
    // !=
    NEQ(),
    // 范围
    RANGE(),
    // 多个范围
    MULTI_RANGE();

    private String[] alias;

    private static Map<String, PortOperatorEnum> nameMap;

    static {
        nameMap = new HashMap<>();
        Set<String> mykeys = new HashSet<>();
        for (PortOperatorEnum myname : values()) {
            addMapEntry(myname.name(), myname, mykeys);

            String[] myalias = myname.getAlias();
            if (myalias != null) {
                for (String myval : myalias) {
                    addMapEntry(myval, myname, mykeys);
                }
            }
        }
    }

    PortOperatorEnum(String... alias) {
        this.alias = alias;
    }

    private static void addMapEntry(String key, PortOperatorEnum portOp, Set<String> existingKeys) {
        key = key.toLowerCase();
        key = key.replace('_', '-');
        if (existingKeys.contains(key)) {
            throw new RuntimeException("Duplicate name/alias with PortOperator: " + key);
        }
        nameMap.put(key, portOp);
    }

    public static PortOperatorEnum lookup(String txt) {
        txt = txt.toLowerCase();
        txt = txt.replace('_', '-');
        return nameMap.get(txt);
    }

    public static Map<String, PortOperatorEnum> getNameMap() {
        return nameMap;
    }

    public String[] getAlias() {
        return this.alias;
    }

}

package com.nspm.antlr4.common.interfaces;

import java.io.Serializable;

/**
 * @author luwei
 * @date 2019/9/5
 */
public interface Range extends Comparable<Range>, Serializable {

    Range[] createRangeArray(int paramInt);

    RangeValueType getRangeValueType();

    boolean isAny();

    Range getAny();

    public static enum RangeValueType {
        LONG, IP6;

        private RangeValueType() {
        }
    }
}

package com.nspm.antlr4.common.interfaces;

/**
 * @author luwei
 * @date 2019/9/5
 */
public interface LongRange extends Range {

    long getLongStart();

    long getLongEnd();

    LongRange createLongRange(long paramLong1, long paramLong2);
}

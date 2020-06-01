package com.nspm.antlr4.common.utils;

/**
 * @author zc
 * @date 2019/09/18
 */
public class TwoMemberObject<A, B> {
    private A valueA;
    private B valueB;

    public TwoMemberObject(A valueA, B valueB) {
        this.valueA = valueA;
        this.valueB = valueB;
    }

    public A getFirstValue() {
        return this.valueA;
    }

    public B getSecondValue() {
        return this.valueB;
    }
}

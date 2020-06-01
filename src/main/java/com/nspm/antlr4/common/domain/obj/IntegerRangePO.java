package com.nspm.antlr4.common.domain.obj;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author luwei
 * @date 2019/9/9
 */
@Setter
@Getter
@NoArgsConstructor
public class IntegerRangePO implements Comparable<IntegerRangePO>{

    int start;

    int end;

    public IntegerRangePO(int start)
    {
        this.start = start;
        this.end = start;
    }

    public IntegerRangePO(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start value " + start + " is bigger than end value " + end + ".");
        }
        this.start = start;
        this.end = end;
    }

    public boolean isSame()
    {
        return this.start == this.end;
    }

    @Override
    public int compareTo(IntegerRangePO that) {
        if (getStart() < that.getStart()) {
            return -1;
        }
        if (getStart() > that.getStart()) {
            return 1;
        }

        if (getEnd() < that.getEnd()) {
            return -1;
        }
        if (getEnd() > that.getEnd()) {
            return 1;
        }
        return 0;
    }
}

package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.interfaces.LongRange;
import com.nspm.antlr4.common.interfaces.Range;
import com.nspm.antlr4.common.utils.RangeUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zc
 * @date 2019/09/20
 */
@Data
public class PortRangePO implements LongRange, Serializable {
    private static final long serialVersionUID = 1L;
    private static final ConcurrentHashMap<PortRangePO, PortRangePO> PORT_RANGE_INTERN_CACHE = new ConcurrentHashMap(10000, 0.75F, 32);
    public static final PortRangePO ANY = create(0, 65535);
    public static final PortRangePO NEAR_ANY = create(1, 65535);
    public static final int LOW_LIMIT = 0;
    public static final int HIGH_LIMIT = 65535;
    private int start;
    private int end;

    @JsonCreator
    public static PortRangePO create(@JsonProperty("start") int start, @JsonProperty("end") int end) {
        return intern(new PortRangePO(start, end));
    }

    public static PortRangePO create(int val) {
        return intern(new PortRangePO(val));
    }

    private static PortRangePO intern(PortRangePO rangeObj) {
        return RangeUtil.intern(PORT_RANGE_INTERN_CACHE, rangeObj);
    }

    private Object readResolve() throws ObjectStreamException {
        return intern(this);
    }

    private PortRangePO() {
    }

    private PortRangePO(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start value " + start + " is bigger than end value " + end + ".");
        } else if (start >= 0 && start <= 65535) {
            if (end >= 0 && end <= 65535) {
                this.start = start;
                this.end = end;
            } else {
                throw new IllegalArgumentException("End value '" + end + "' is out of range [0-65535]");
            }
        } else {
            throw new IllegalArgumentException("Start value '" + start + "' is out of range [0-65535]");
        }
    }

    private PortRangePO(int val) {
        this(val, val);
    }

    @Override
    public Range[] createRangeArray(int size) {
        return new PortRangePO[size];
    }

    @JsonIgnore
    @Override
    public RangeValueType getRangeValueType() {
        return RangeValueType.LONG;
    }

    @Override
    public boolean isAny() {
        return this.equals(ANY);
    }

    @JsonIgnore
    @Override
    public Range getAny() {
        return ANY;
    }

    @Override
    public String toString() {
        return this.start == this.end ? this.start + "" : this.start + "-" + this.end;
    }

    @Override
    public int compareTo(Range o) {
        PortRangePO myOther = (PortRangePO) o;
        return RangeUtil.compareLongRange(this, myOther);
    }
    @JsonIgnore
    @Override
    public long getLongStart() {
        return (long)this.start;
    }
    @JsonIgnore
    @Override
    public long getLongEnd() {
        return (long)this.end;
    }

    @Override
    public LongRange createLongRange(long start, long end) {
        return create((int)start, (int)end);
    }
}

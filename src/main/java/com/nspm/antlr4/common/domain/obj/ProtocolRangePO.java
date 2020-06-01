package com.nspm.antlr4.common.domain.obj;

import com.nspm.antlr4.common.interfaces.LongRange;
import com.nspm.antlr4.common.interfaces.Range;
import com.nspm.antlr4.common.utils.RangeUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luwei
 * @date 2019/9/5
 */
@Data
@ApiModel("协议范围对象")
public class ProtocolRangePO implements LongRange, Serializable {

    private static final long serialVersionUID = 1L;
    private static final ConcurrentHashMap<ProtocolRangePO, ProtocolRangePO> PROTOCOL_RANGE_INTERN_CACHE = new ConcurrentHashMap(10000, 0.75F, 32);

    public static final ProtocolRangePO ANY = create(0, 255);
    public static final ProtocolRangePO TCP = create(6);
    public static final ProtocolRangePO UDP = create(17);
    public static final ProtocolRangePO ICMP = create(1);
    public static final ProtocolRangePO IGMP = create(2);
    private int start;
    private int end;

    @JsonCreator
    public static ProtocolRangePO create(@JsonProperty("start") int start, @JsonProperty("end") int end)
    {
        return intern(new ProtocolRangePO(start, end));
    }

    public static ProtocolRangePO create(int val) {
        return intern(new ProtocolRangePO(val));
    }

    private static ProtocolRangePO intern(ProtocolRangePO rangeObj)
    {
        return (ProtocolRangePO) RangeUtil.intern(PROTOCOL_RANGE_INTERN_CACHE, rangeObj);
    }

    private Object readResolve() throws ObjectStreamException
    {
        return intern(this);
    }

    private ProtocolRangePO() {}

    private ProtocolRangePO(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start value " + start + " is bigger than end value " + end + ".");
        }

        if ((start < 0) || (start > 255)) {
            throw new IllegalArgumentException("Start value '" + start + "' is out of range [0-255]");
        }

        if ((end < 0) || (end > 255)) {
            throw new IllegalArgumentException("End value '" + end + "' is out of range [0-255]");
        }

        this.start = start;
        this.end = end;
    }

    private ProtocolRangePO(int val) {
        this(val, val);
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    @Override
    public Range[] createRangeArray(int size)
    {
        return new ProtocolRangePO[size];
    }

    @JsonIgnore
    @Override
    public Range.RangeValueType getRangeValueType()
    {
        return Range.RangeValueType.LONG;
    }

    @Override
    public boolean isAny()
    {
        return equals(ANY);
    }

    @JsonIgnore
    @Override
    public Range getAny()
    {
        return ANY;
    }

    @Override
    public int compareTo(Range o)
    {
        ProtocolRangePO myOther = (ProtocolRangePO)o;
        return RangeUtil.compareLongRange(this, myOther);
    }

    @Override
    public String toString()
    {
        if (this.start == this.end) {
            return this.start + "";
        }
        return this.start + "-" + this.end;
    }

    /*     */
    /*public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.end;
        result = 31 * result + this.start;
        return result;
    }*/

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ProtocolRangePO other = (ProtocolRangePO)obj;
        if (this.end != other.end) {
            return false;
        }
        if (this.start != other.start) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public long getLongStart()
    {
        return this.start;
    }

    @JsonIgnore
    @Override
    public long getLongEnd()
    {
        return this.end;
    }

    @Override
    public LongRange createLongRange(long start, long end)
    {
        return create((int)start, (int)end);
    }

}

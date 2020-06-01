package com.nspm.antlr4.common.domain.ip;

import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import com.nspm.antlr4.common.interfaces.LongRange;
import com.nspm.antlr4.common.interfaces.Range;
import com.nspm.antlr4.common.utils.IPUtil;
import com.nspm.antlr4.common.utils.RangeUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectStreamException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel(value = "ip4地址")
//@Data
@Slf4j
public class IP4AddressRangePO implements LongRange, IPAddressRange {

    private static final long serialVersionUID = 1L;
    private static final ConcurrentHashMap<IP4AddressRangePO, IP4AddressRangePO> IP4_RANGE_INTERN_CACHE = new ConcurrentHashMap(10000, 0.75F, 32);
    public static final IP4AddressRangePO ANY = create("0.0.0.0", "255.255.255.255");

    @JsonIgnore
    private long start;

    @JsonIgnore
    private long end;


    public static IP4AddressRangePO create(long start) {
        return intern(new IP4AddressRangePO(start, start));
    }

    public static IP4AddressRangePO create(long start, long end) {
        return intern(new IP4AddressRangePO(start, end));
    }

    public static IP4AddressRangePO create(IP4AddressPO start) {
        return intern(new IP4AddressRangePO(start, start));
    }

    public static IP4AddressRangePO create(IP4AddressPO start, IP4AddressPO end) {
        return intern(new IP4AddressRangePO(start, end));
    }

    @JsonCreator
    public static IP4AddressRangePO create(@JsonProperty("start") String start, @JsonProperty("end") String end) {
        return intern(new IP4AddressRangePO(start, end));
    }

    public static IP4AddressRangePO createNew(String start, String end) {
        return intern(new IP4AddressRangePO(start, end));
    }

    public static IP4AddressRangePO create(String val) {
        return intern(new IP4AddressRangePO(val, val));
    }

    private static IP4AddressRangePO intern(IP4AddressRangePO rangeObj) {
        return (IP4AddressRangePO) RangeUtil.intern(IP4_RANGE_INTERN_CACHE, rangeObj);
    }

    private Object readResolve()
            throws ObjectStreamException {
        return intern(this);
    }

    private IP4AddressRangePO() {
    }

    private IP4AddressRangePO(long start, long end) {
        long myStart = IPUtil.unsignedIntToLong(start);
        long myEnd = IPUtil.unsignedIntToLong(end);
        if (myStart > myEnd) {
            throw new IllegalArgumentException("Start value " + start + " is bigger than end value " + end + ".");
        }
        this.start = start;
        this.end = end;
    }

    private IP4AddressRangePO(IP4AddressPO start, IP4AddressPO end) {
        long myStart = start.getAddressAsLong();
        long myEnd = end.getAddressAsLong();
        if (myStart > myEnd) {
            throw new IllegalArgumentException("Start value " + start + " is bigger than end value " + end + ".");
        }
        this.start = start.getAddressAsUnsignedInt();
        this.end = end.getAddressAsUnsignedInt();
    }

    public IP4AddressRangePO(String start, String end) {
        IP4AddressPO ipStart = IP4AddressPO.create(start);
        IP4AddressPO ipEnd = IP4AddressPO.create(end);
        long myStart = ipStart.getAddressAsLong();
        long myEnd = ipEnd.getAddressAsLong();
        if (myStart > myEnd) {
            log.error("起始IP：" + start + " 大于终止IP:" + end);
            throw new IllegalArgumentException("Start value " + start + " is bigger than end value " + end + ".");
        }
        this.start = ipStart.getAddressAsUnsignedInt();
        this.end = ipEnd.getAddressAsUnsignedInt();
    }
    @JsonIgnore
    public Long getStartAsUnsignedInt() {
        return this.start;
    }
    @JsonIgnore
    public Long getEndAsUnsignedInt() {
        return this.end;
    }
    @JsonIgnore
    public long getStartAsLong() {
        return IPUtil.unsignedIntToLong(this.start);
    }
    @JsonIgnore
    public long getEndAsLong() {
        return IPUtil.unsignedIntToLong(this.end);
    }
    @JsonIgnore
    public IP4AddressPO getStart() {
        return IP4AddressPO.create(this.start);
    }
    @JsonIgnore
    public IP4AddressPO getEnd() {
        return IP4AddressPO.create(this.end);
    }

    @JsonProperty("start")
    public String getStartAsString() {
        return IP4AddressPO.create(this.start).toString();
    }

    @JsonProperty("end")
    public String getEndAsString() {
        return IP4AddressPO.create(this.end).toString();
    }
    @JsonIgnore
    @Override
    public Range.RangeValueType getRangeValueType() {
        return Range.RangeValueType.LONG;
    }

    @Override
    public Range[] createRangeArray(int size) {
        return new IP4AddressRangePO[size];
    }
    @JsonIgnore
    @Override
    public long getLongStart() {
        return getStartAsLong();
    }
    @JsonIgnore
    @Override
    public long getLongEnd() {
        return getEndAsLong();
    }

    @Override
    public LongRange createLongRange(long start, long end) {
        return create((int) start, (int) end);
    }

    @Override
    public boolean isAny() {
        log.info("是否any,isAny");
        return equals(ANY);
    }

    @Override
    public int compareTo(Range o) {
        IP4AddressRangePO myOther = (IP4AddressRangePO) o;
        return RangeUtil.compareLongRange(this, myOther);
    }

    @Override
    public String toString() {
        if (this.start == this.end) {
            return getStartAsString();
        }
        return getStartAsString() + "-" + getEndAsString();
    }

   /* @Override
    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.end;
        result = 31 * result + this.start;
        return result;
    }*/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        IP4AddressRangePO other = (IP4AddressRangePO) obj;
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
    public IPAddressPO getIPStart() {
        return getStart();
    }
    @JsonIgnore
    @Override
    public IPAddressPO getIPEnd() {
        return getEnd();
    }

    /** 注：此方法如果不加 JsonIgnore，在controller中返回jackson时会循环自引用**/
    @Override
    @JsonIgnore
    public IPAddressRange getAny() {
//        log.info("获取any,getAny");
        return ANY;
    }
    @JsonIgnore
    @Override
    public IPTypeEnum getIPType() {
        return IPTypeEnum.IP4;
    }

    @Override
    public boolean contains(IPAddressPO ip) {
        if (ip.getType() != IPTypeEnum.IP4) {
            return false;
        }
        IP4AddressPO myip = (IP4AddressPO) ip;
        long mylong = myip.getAddressAsLong();
        if ((mylong >= getStartAsLong()) && (mylong <= getEndAsLong())) {
            return true;
        }
        return false;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public static void main(String[] args) {
        /*IP4AddressRangePO po = IP4AddressRangePO.createNew("172.17.1.100", "172.17.1.100");
        System.out.println(po.toString());*/
    }

}

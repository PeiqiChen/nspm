package com.nspm.antlr4.common.domain.ip;

import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import com.nspm.antlr4.common.interfaces.Range;
import com.nspm.antlr4.common.utils.RangeUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.ObjectStreamException;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel(value = "ip6地址")
@Data
public class IP6AddressRangePO   implements IPAddressRange {

    private static final ConcurrentHashMap<IP6AddressRangePO, IP6AddressRangePO> IP6_RANGE_INTERN_CACHE = new ConcurrentHashMap(1000, 0.75F, 32);
    public static final IP6AddressRangePO ANY = create("::", "ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff");

    private IP6AddressPO start;

    private IP6AddressPO end;



    public static IP6AddressRangePO create(IP6AddressPO addr)
    {
        return intern(new IP6AddressRangePO(addr, addr));
    }

    public static IP6AddressRangePO create(IP6AddressPO start, IP6AddressPO end)
    {
        return intern(new IP6AddressRangePO(start, end));
    }

    @JsonCreator
    public static IP6AddressRangePO create(@JsonProperty("start") String start, @JsonProperty("end") String end)
    {
        return intern(new IP6AddressRangePO(start, end));
    }

    private static IP6AddressRangePO intern(IP6AddressRangePO obj)
    {
        return (IP6AddressRangePO) RangeUtil.intern(IP6_RANGE_INTERN_CACHE, obj);
    }

    private Object readResolve()
            throws ObjectStreamException
    {
        return intern(this);
    }

    private IP6AddressRangePO() {}

    private IP6AddressRangePO(IP6AddressPO start, IP6AddressPO end)
    {
        if (start.getAddressAsBigInt().compareTo(end.getAddressAsBigInt()) > 0) {
            throw new IllegalArgumentException("Start value " + start + " is bigger than end value " + end + ".");
        }
        this.start = start;
        this.end = end;
    }

    private IP6AddressRangePO(String start, String end)
    {
        IP6AddressPO myStart = IP6AddressPO.create(start);
        IP6AddressPO myEnd = IP6AddressPO.create(end);
        if (myStart.getAddressAsBigInt().compareTo(myEnd.getAddressAsBigInt()) > 0) {
            throw new IllegalArgumentException("Start value " + start + " is bigger than end value " + end + ".");
        }
        this.start = myStart;
        this.end = myEnd;
    }

    public IP6AddressPO getStart()
    {
        return this.start;
    }

    public IP6AddressPO getEnd()
    {
        return this.end;
    }

    @JsonProperty("start")
    public String getStartAsString()
    {
        return this.start.toString();
    }

    @JsonProperty("end")
    public String getEndAsString()
    {
        return this.end.toString();
    }

    @Override
    public Range[] createRangeArray(int size)
    {
        return new IP6AddressRangePO[size];
    }

    @Override
    public Range.RangeValueType getRangeValueType()
    {
        return Range.RangeValueType.IP6;
    }

    @Override
    public boolean isAny()
    {
        return equals(ANY);
    }

    @Override
    public int compareTo(Range o)
    {
        IP6AddressRangePO that = (IP6AddressRangePO)o;

        int val = this.start.compareTo(that.start);
        if (val != 0) {
            return val;
        }
        return this.end.compareTo(that.end);
    }

    @Override
    public IPAddressPO getIPStart()
    {
        return getStart();
    }

    @Override
    public IPAddressPO getIPEnd()
    {
        return getEnd();
    }

    @Override
    public IPAddressRange getAny()
    {
        return ANY;
    }

    @Override
    public IPTypeEnum getIPType()
    {
        return IPTypeEnum.IP6;
    }

    @Override
    public boolean contains(IPAddressPO ip)
    {
        if (ip.getType() != IPTypeEnum.IP6) {
            return false;
        }
        IP6AddressPO myip = (IP6AddressPO)ip;
        BigInteger mybi = myip.getAddressAsBigInt();
        if ((mybi.compareTo(getStart().getAddressAsBigInt()) >= 0) && (mybi.compareTo(getEnd().getAddressAsBigInt()) <= 0)) {
            return true;
        }
        return false;
    }

  /*  public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.end == null ? 0 : this.end.hashCode());
        result = 31 * result + (this.start == null ? 0 : this.start.hashCode());
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
        IP6AddressRangePO other = (IP6AddressRangePO)obj;
        if (this.end == null)
        {
            if (other.end != null) {
                return false;
            }
        }
        else if (!this.end.equals(other.end)) {
            return false;
        }
        if (this.start == null)
        {
            if (other.start != null) {
                return false;
            }
        }
        else if (!this.start.equals(other.start)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        if (this.start.equals(this.end)) {
            return getStartAsString();
        }
        return getStartAsString() + "-" + getEndAsString();
    }
}

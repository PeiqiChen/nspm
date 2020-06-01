package com.nspm.antlr4.common.domain.ip;

import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import com.nspm.antlr4.common.utils.IPUtil;
import com.nspm.antlr4.common.utils.RangeUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import com.nspm.antlr4.common.utils.IPUtil;
import com.nspm.antlr4.common.utils.RangeUtil;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel(value = "ip6地址long类型")
@Data
public class IP6AddressPO extends IPAddressPO implements Serializable, Comparable<IP6AddressPO>{

    public static final int BIT_SIZE = 128;
    public static final int BYTE_SIZE = 16;
    private static final ConcurrentHashMap<IP6AddressPO, IP6AddressPO> IP6_INTERN_CACHE = new ConcurrentHashMap(10000, 0.75F, 32);
    public static final IP6AddressPO MIN = create("0::0");
    public static final IP6AddressPO MAX = create("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff");

    @JsonIgnore
    private long highBits;

    @JsonIgnore
    private long lowBits;


    public static IP6AddressPO create(long highBits, long lowBits)
    {
        return intern(new IP6AddressPO(highBits, lowBits));
    }

    @JsonCreator
    public static IP6AddressPO create(String address)
    {
        return intern(new IP6AddressPO(address));
    }

    public static IP6AddressPO create(byte[] bytes)
    {
        return intern(new IP6AddressPO(bytes));
    }

    public static IP6AddressPO create(BigInteger bigInt)
    {
        return intern(new IP6AddressPO(bigInt));
    }

    private static IP6AddressPO intern(IP6AddressPO ip6Obj)
    {
        return (IP6AddressPO) RangeUtil.intern(IP6_INTERN_CACHE, ip6Obj);
    }

    private Object readResolve()
            throws ObjectStreamException
    {
        return intern(this);
    }

    private IP6AddressPO() {}

    private IP6AddressPO(long highBits, long lowBits)
    {
        this.highBits = highBits;
        this.lowBits = lowBits;
    }

    private IP6AddressPO(String address) {
        if ((address == null) || (address.trim().length() == 0)) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        InetAddress myip;
        try {
            myip = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(address + " is not a valid IPv6 address.");
        }
        if (myip == null) {
            throw new IllegalArgumentException(address + " is not a valid IPv6 address.");
        }
        if (!(myip instanceof Inet6Address)) {
            throw new IllegalArgumentException(address + " is not a valid IPv6 address.");
        }
        byte[] myadd = myip.getAddress();
        long[] mylongs = toTwoLong(myadd);
        this.highBits = mylongs[0];
        this.lowBits = mylongs[1];
    }

    private IP6AddressPO(byte[] bytes)
    {
        if ((bytes == null) || (bytes.length != 16)) {
            throw new IllegalArgumentException("byte array has to be of size 16.");
        }
        long[] mylongs = toTwoLong(bytes);
        this.highBits = mylongs[0];
        this.lowBits = mylongs[1];
    }

    private IP6AddressPO(BigInteger bigInt)
    {
        if (bigInt == null) {
            throw new IllegalArgumentException("BigInt cannot be null.");
        }
        long mylow = bigInt.longValue();
        BigInteger bigInt2 = bigInt.shiftRight(64);
        long myhigh = bigInt2.longValue();

        this.lowBits = mylow;
        this.highBits = myhigh;
    }

    private long[] toTwoLong(byte[] bytes)
    {
        if ((bytes == null) || (bytes.length != 16)) {
            throw new IllegalArgumentException("byte array has to be of size 16.");
        }
        long[] result = new long[2];

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        result[0] = byteBuffer.getLong();
        result[1] = byteBuffer.getLong();

        return result;
    }

    private byte[] toByteArray(long highBits, long lowBits)
    {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.putLong(highBits).putLong(lowBits);
        return byteBuffer.array();
    }

    public long getHighBits()
    {
        return this.highBits;
    }

    public long getLowBits()
    {
        return this.lowBits;
    }

    public byte[] getAddressAsByteArray()
    {
        return toByteArray(this.highBits, this.lowBits);
    }

    public BigInteger getAddressAsBigInt()
    {
        return new BigInteger(1, getAddressAsByteArray());
    }

    @JsonValue
    public String getAddressAsString()
    {
        return toShortDisplay();
    }

    public String toFullDisplay()
    {
        StringBuilder sb = new StringBuilder();

        String[] myStrs = toHexStringArr();
        for (int i = 0; i < myStrs.length; i++)
        {
            String myStr = myStrs[i];
            sb.append(myStr);
            if (i != myStrs.length - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    public String toShortDisplay()
    {
        String[] myHexs = toHexStringArr();

        int maxStartInd = 0;
        int maxCountZero = 0;
        boolean isNewZero = true;
        int startInd = 0;
        int countZero = 0;
        for (int i = 0; i < myHexs.length; i++)
        {
            String myHex = myHexs[i];
            if (myHex.equals("0"))
            {
                if (isNewZero)
                {
                    countZero = 1;
                    startInd = i;
                    isNewZero = false;
                }
                else
                {
                    countZero++;
                }
            }
            else
            {
                isNewZero = true;
                if (countZero > maxCountZero)
                {
                    maxStartInd = startInd;
                    maxCountZero = countZero;
                }
            }
        }
        if (countZero > maxCountZero)
        {
            maxStartInd = startInd;
            maxCountZero = countZero;
        }
        StringBuilder sb = new StringBuilder();
        if (maxCountZero <= 1)
        {
            for (int i = 0; i < myHexs.length; i++)
            {
                String myStr = myHexs[i];
                sb.append(myStr);
                if (i != myHexs.length - 1) {
                    sb.append(":");
                }
            }
            return sb.toString();
        }
        for (int i = 0; i < myHexs.length; i++)
        {
            if (i == maxStartInd)
            {
                sb.append("::");
                i += maxCountZero;
            }
            else if (i != 0)
            {
                sb.append(":");
            }
            if (i < myHexs.length)
            {
                String myStr = myHexs[i];
                sb.append(myStr);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString()
    {
        return toShortDisplay();
    }

    private String[] toHexStringArr()
    {
        String[] result = new String[8];
        for (int i = 0; i < 4; i++) {
            result[i] = Integer.toHexString((int)(this.highBits >>> (3 - i) * 16 & 0xFFFF));
        }
        for (int i = 0; i < 4; i++) {
            result[(i + 4)] = Integer.toHexString((int)(this.lowBits >>> (3 - i) * 16 & 0xFFFF));
        }
        return result;
    }

    @Override
    public String toDisplayString()
    {
        return toShortDisplay();
    }

    @Override
    public IPTypeEnum getType()
    {
        return IPTypeEnum.IP6;
    }

    @Override
    public IP6AddressRangePO toRange(int maskLength)
    {
        if ((maskLength < 0) || (maskLength > 128)) {
            throw new IllegalArgumentException("Mask length should be between 0 to 128");
        }
        if (maskLength == 0) {
            return IP6AddressRangePO.ANY;
        }
        if (maskLength == 128) {
            return IP6AddressRangePO.create(this, this);
        }
        IP6AddressRangePO result = null;
        if (maskLength > 64)
        {
            long myMask = Long.MIN_VALUE >> maskLength - 65;
            long myBase = this.lowBits & myMask;
            long myEnd = myBase | myMask ^ 0xFFFFFFFFFFFFFFFFL;

            IP6AddressPO mystart = create(this.highBits, myBase);
            IP6AddressPO myend = create(this.highBits, myEnd);

            result = IP6AddressRangePO.create(mystart, myend);
        }
        else if (maskLength == 64)
        {
            IP6AddressPO mystart = create(this.highBits, 0L);
            IP6AddressPO myend = create(this.highBits, -1L);
            result = IP6AddressRangePO.create(mystart, myend);
        }
        else
        {
            long myMask = Long.MIN_VALUE >> maskLength - 1;
            long myBase = this.highBits & myMask;
            long myEnd = myBase | myMask ^ 0xFFFFFFFFFFFFFFFFL;

            IP6AddressPO mystart = create(myBase, 0L);
            IP6AddressPO myend = create(myEnd, -1L);

            result = IP6AddressRangePO.create(mystart, myend);
        }
        return result;
    }

    @Override
    public int compareTo(IP6AddressPO that)
    {
        long high2 = that.highBits;
        long low2 = that.lowBits;

        int val = IPUtil.unsignedLongCompare(this.highBits, high2);
        if (val != 0) {
            return val;
        }
        return IPUtil.unsignedLongCompare(this.lowBits, low2);
    }

    @Override
    public IPAddressRange createRange(IPAddressPO start, IPAddressPO end)
    {
        if ((start.getType() != IPTypeEnum.IP6) || (end.getType() != IPTypeEnum.IP6)) {
            throw new IllegalArgumentException("Start or end value is not IP6");
        }
        return IP6AddressRangePO.create((IP6AddressPO)start, (IP6AddressPO)end);
    }

 /*   public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (int)(this.highBits ^ this.highBits >>> 32);
        result = 31 * result + (int)(this.lowBits ^ this.lowBits >>> 32);
        return result;
    }*/

    /*public boolean equals(Object obj)
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
        IP6AddressPO other = (IP6AddressPO)obj;
        if (this.highBits != other.highBits) {
            return false;
        }
        if (this.lowBits != other.lowBits) {
            return false;
        }
        return true;
    }
*/
    public IP6AddressPO minusOne()
    {
        if (equals(MIN)) {
            throw new IllegalArgumentException("IP6 Address is all zero. Cannot minus one.");
        }
        long newHigh = this.highBits;
        long newLow = this.lowBits;
        if (this.lowBits != 0L)
        {
            newLow = this.lowBits - 1L;
        }
        else
        {
            newLow = -1L;
            newHigh = this.highBits - 1L;
        }
        return create(newHigh, newLow);
    }

    public IP6AddressPO addOne()
    {
        if (equals(MAX)) {
            throw new IllegalArgumentException("IP6 Address is already MAX. Cannot add one.");
        }
        long newHigh = this.highBits;
        long newLow = this.lowBits;
        if (this.lowBits != -1L)
        {
            newLow = this.lowBits + 1L;
        }
        else
        {
            newLow = 0L;
            newHigh = this.highBits + 1L;
        }
        return create(newHigh, newLow);
    }

    public boolean isLinkLocalAddr()
    {
        return (this.highBits & 0xFFC0000000000000L) == -108086391056891904L;
    }

    public boolean isUniqueLocalAddr()
    {
        return (this.highBits & 0xFF00000000000000L) == -216172782113783808L;
    }
}

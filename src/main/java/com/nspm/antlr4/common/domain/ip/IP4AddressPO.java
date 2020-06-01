package com.nspm.antlr4.common.domain.ip;

import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import com.nspm.antlr4.common.utils.IPUtil;
import com.nspm.antlr4.common.utils.RangeUtil;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luwei
 * @date 2019/9/5
 */
@ApiModel(value = "ip4地址long类型")
@Data
@Slf4j
public class IP4AddressPO extends IPAddressPO implements Serializable, Comparable<IP4AddressPO> {

    private static final long serialVersionUID = 1L;
    private static final ConcurrentHashMap<IP4AddressPO, IP4AddressPO> IP4_INTERN_CACHE = new ConcurrentHashMap(10000, 0.75F, 32);
    public static final IP4AddressPO MIN = create(0L);
    public static final IP4AddressPO MAX = create("255.255.255.255");

    @ApiModelProperty(value = "ip4地址long类型")
    private Long address;

    @JsonCreator
    public static IP4AddressPO create(String address) {
        return intern(new IP4AddressPO(address));
    }

    public static IP4AddressPO createWithouIntern(String address) {
        return new IP4AddressPO(address);
    }

    public static IP4AddressPO create(Long address) {
        return intern(new IP4AddressPO(address));
    }

    public static IP4AddressPO createWithoutIntern(long address) {
        return new IP4AddressPO(address);
    }

    public static IP4AddressPO create(byte[] bytes) {
        return intern(new IP4AddressPO(bytes));
    }

    private static IP4AddressPO intern(IP4AddressPO ip4Obj) {
        return (IP4AddressPO) RangeUtil.intern(IP4_INTERN_CACHE, ip4Obj);
    }

    private Object readResolve()
            throws ObjectStreamException {
        return intern(this);
    }

    private IP4AddressPO() {
    }

    public IP4AddressPO(String address) {
        IP4AddressPO myip = createByString(address);
        if (myip == null) {
            throw new IllegalArgumentException("Invalid ip4 address:" + address);
        }
        this.address = myip.address;
    }

    private IP4AddressPO(Long address) {
        this.address = address;
    }

    private IP4AddressPO(byte[] bytes) {
        if ((bytes == null) || (bytes.length != 4)) {
            throw new IllegalArgumentException("byte array has to be of size 4.");
        }
        long myadd = 0;
        myadd = bytes[3] & 0xFFL;
        myadd |= bytes[2] << 8 & 0xFF00L;
        myadd |= bytes[1] << 16 & 0xFF0000L;
        myadd |= bytes[0] << 24 & 0xFF000000L;
        this.address = myadd;
    }

    public byte[] getAddressAsByteArray() {
        byte[] addr = new byte[4];

        addr[0] = ((byte) (this.address >>> 24 & 0xFF));
        addr[1] = ((byte) (this.address >>> 16 & 0xFF));
        addr[2] = ((byte) (this.address >>> 8 & 0xFF));
        addr[3] = ((byte) (this.address & 0xFF));
        return addr;
    }

    public long getAddressAsUnsignedInt() {
        return this.address;
    }

    public long getAddressAsLong() {
        return IPUtil.unsignedIntToLong(this.address);
    }

    @JsonValue
    public String getAddressAsString() {
        return toString();
    }

    private static IP4AddressPO createByString(String ipStr) {
        if ((ipStr == null) || (ipStr.trim().length() == 0)) {
            return null;
        }
        InetAddress myip = null;
        try {
            myip = InetAddress.getByName(ipStr);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(ipStr + " is not a valid IPv4 address.");
        }
        if (myip == null) {
            return null;
        }
        IP4AddressPO result = null;
        if ((myip instanceof Inet4Address)) {
            result = new IP4AddressPO(myip.getAddress());
        } else {
            throw new IllegalArgumentException(ipStr + " is not a valid IPv4 address.");
        }
        return result;
    }

    @Override
    public String toString() {
        byte[] bytes = getAddressAsByteArray();
        return (bytes[0] & 0xFF) + "." + (bytes[1] & 0xFF) + "." + (bytes[2] & 0xFF) + "." + (bytes[3] & 0xFF);
    }

   /* @Override
    public int hashCode()
    {
        long prime = 31;
        int result = 1;
        result = 31 * result + this.address;
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
        IP4AddressPO other = (IP4AddressPO) obj;
        if (this.address.equals(other.address)) {
            return false;
        }
        return true;
    }

    @Override
    public String toDisplayString() {
        return toString();
    }

    @Override
    public IPTypeEnum getType() {
        return IPTypeEnum.IP4;
    }

    @Override
    public int compareTo(IP4AddressPO o) {
        if (o == null) {
            return 1;
        }
        return Long.compare(getAddressAsLong(), o.getAddressAsLong());
    }

    @Override
    public IP4AddressRangePO toRange(int maskLength) {
        if ((maskLength < 0) || (maskLength > 32)) {
            throw new IllegalArgumentException("Mask length should be between 0 to 32");
        }
        if (maskLength == 0) {
            log.info("maskLength = 0, 返回any");
            return IP4AddressRangePO.ANY;
        }
        if (maskLength == 32) {
            return IP4AddressRangePO.create(this);
        }
        long myAddInt = this.address;

        int myMask = Integer.MIN_VALUE >> maskLength - 1;

        long myBase = myAddInt & myMask;

        long myEnd = myBase | myMask ^ 0xFFFFFFFF;

        return IP4AddressRangePO.create(myBase, myEnd);
    }

    @Override
    public IPAddressRange createRange(IPAddressPO start, IPAddressPO end) {
        if ((start.getType() != IPTypeEnum.IP4) || (end.getType() != IPTypeEnum.IP4)) {
            throw new IllegalArgumentException("Start or end value is not IP4");
        }
        return IP4AddressRangePO.create((IP4AddressPO) start, (IP4AddressPO) end);
    }


}

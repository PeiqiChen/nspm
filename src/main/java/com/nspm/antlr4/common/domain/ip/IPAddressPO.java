package com.nspm.antlr4.common.domain.ip;

import com.nspm.antlr4.common.enums.IPTypeEnum;
import com.nspm.antlr4.common.interfaces.IPAddressRange;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Comparator;

/**
 * @author luwei
 * @date 2019/9/5
 */
public abstract class IPAddressPO {

    public static IPAddressPO createIPAddress(String ipStr)
    {
        if ((ipStr == null) || (ipStr.trim().length() == 0)) {
            return null;
        }

        InetAddress myip;
        try
        {
            myip = InetAddress.getByName(ipStr);
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException(ipStr + " is not a valid IP address.");
        }

        if (myip == null) {
            return null;
        }

        IPAddressPO result = null;

        if ((myip instanceof Inet4Address)) {
            result = IP4AddressPO.create(myip.getAddress());
        } else if ((myip instanceof Inet6Address)) {
            result = IP6AddressPO.create(myip.getAddress());
        } else {
            throw new IllegalArgumentException(ipStr + " is not a valid IP address.");
        }

        return result;
    }

    public abstract String toDisplayString();

    public abstract IPTypeEnum getType();

    public abstract IPAddressRange toRange(int paramInt);

    public abstract IPAddressRange createRange(IPAddressPO paramIPAddress1, IPAddressPO paramIPAddress2);

    public int compareIPAddress(IPAddressPO that)
    {
        if (that == null) {
            return 1;
        }

        if (getType() != that.getType()) {
            throw new IllegalArgumentException("Cannot compare IPAddresses that are not the same type.");
        }

        if (getType() == IPTypeEnum.IP4) {
            return ((IP4AddressPO)this).compareTo((IP4AddressPO)that);
        }
        return ((IP6AddressPO)this).compareTo((IP6AddressPO)that);
    }

    public static Comparator<IPAddressPO> SORT_IP_ADD = new Comparator<IPAddressPO>() {
        @Override
        public int compare(IPAddressPO left, IPAddressPO right) {

            if ((left == null) && (right == null)) {
                return 0;
            }

            if (left == null) {
                return -1;
            }
            return left.compareIPAddress(right);
        }
    };
}

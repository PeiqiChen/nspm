package com.nspm.antlr4.common.utils;

import com.nspm.antlr4.common.domain.ip.IP4AddressPO;
import com.nspm.antlr4.common.domain.ip.IP4AddressRangePO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;
import com.nspm.antlr4.common.domain.ip.IPAddressPO;
import com.nspm.antlr4.exceptions.ZeeRuntimeException;
import com.nspm.antlr4.common.interfaces.IPAddressRange;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author luwei
 * @date 2019/9/5
 */
@Slf4j
public class IPUtil {

    private static final Pattern IP4PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    public static int unsignedLongCompare(long a, long b)
    {
        if (a == b) {
            return 0;
        }
        if (a == 0L) {
            return -1;
        }
        if (b == 0L) {
            return 1;
        }
        if ((a < 0L) && (b > 0L)) {
            return 1;
        }
        if ((a < 0L) && (b < 0L)) {
            return Long.compare(a, b);
        }
        if ((a > 0L) && (b > 0L)) {
            return Long.compare(a, b);
        }
        if ((a > 0L) && (b < 0L)) {
            return -1;
        }
        throw new IllegalStateException("Coding bug. It shouldn't come here.");
    }

    public static long unsignedIntToLong(long a)
    {
        long myA = a & 0xFFFFFFFFL;

        return myA;
    }

    public static IP4AddressPO getBaseAddress(IP4AddressPO address, int maskLength)
    {
        if (address == null) {
            return null;
        }
        if ((maskLength < 0) || (maskLength > 32)) {
            throw new IllegalArgumentException("Mask length should be between 0 to 32");
        }
        if (maskLength == 0) {
            return IP4AddressPO.MIN;
        }
        if (maskLength == 32) {
            return address;
        }
        long myAddInt = address.getAddressAsUnsignedInt();

        long myMask = Integer.MIN_VALUE >> maskLength - 1;

        long myBase = myAddInt & myMask;

        return IP4AddressPO.create(myBase);
    }

    public static IP6AddressPO getBaseAddress(IP6AddressPO address, int maskLength)
    {
        if (address == null) {
            return null;
        }
        if ((maskLength < 0) || (maskLength > 128)) {
            throw new IllegalArgumentException("Mask length should be between 0 to 128");
        }
        if (maskLength == 0) {
            return IP6AddressPO.MIN;
        }
        if (maskLength == 128) {
            return address;
        }
        long highBits = address.getHighBits();
        long lowBits = address.getLowBits();
        if (maskLength == 64)
        {
            lowBits = 0L;
        }
        else if (maskLength > 64)
        {
            long myMask = Long.MIN_VALUE >> maskLength - 64 - 1;
            lowBits &= myMask;
        }
        else
        {
            lowBits = 0L;
            long myMask = Long.MIN_VALUE >> maskLength - 1;
            highBits &= myMask;
        }
        return IP6AddressPO.create(highBits, lowBits);
    }

    public static List<IP4AddressRangePO> getWildcardRanges(IP4AddressPO baseIP, IP4AddressPO wildcardMask)
    {
        long mask = wildcardMask.getAddressAsUnsignedInt();
        if (mask == 0) {
            log.info("mask=0，返回any");
            return Collections.singletonList(IP4AddressRangePO.ANY);
        }
        int maxRangeCount = 256;

        long myRangeCount = getWildcardRangeCount(wildcardMask);

        int myResultCount = maxRangeCount;
        if (myRangeCount < maxRangeCount) {
            myResultCount = (int)myRangeCount;
        }
        List<IP4AddressRangePO> myResult = new ArrayList(myResultCount);

        long base = baseIP.getAddressAsUnsignedInt();
        base &= mask;

        long trailingZeroCount = Long.numberOfTrailingZeros(mask);
        long highMask = 0;
        if (trailingZeroCount == 0) {
            highMask = 0;
        } else {
            highMask = Integer.MIN_VALUE >> 32 - trailingZeroCount - 1 ^ 0xFFFFFFFFL;
        }
        long myGap = 1L;
        if (myRangeCount > maxRangeCount) {
            myGap = myRangeCount / maxRangeCount;
        }
        long nextIndex = 0L;
        while (nextIndex < myRangeCount)
        {
            long newBase = getNewBase(base, mask, (int)nextIndex, trailingZeroCount);
            myResult.add(IP4AddressRangePO.create(newBase, newBase | highMask));
            nextIndex += myGap;
        }
        return myResult;
    }

    private static long getNewBase(long oldBase, long oldMask, long indexForMidZero, long numberOfTrailingZero)
    {
        long result = oldBase;
        long indexOneCount = Long.bitCount(indexForMidZero);

        long indexMask = 1;
        long baseMask = 1 << numberOfTrailingZero;
        long myOneCount = 0;
        while (myOneCount < indexOneCount)
        {
            if (baseMask == 0) {
                throw new ZeeRuntimeException("Coding error. oldBase should have enough zero bit.");
            }
            if ((baseMask & oldMask) == 0)
            {
                if ((indexMask & indexForMidZero) != 0)
                {
                    myOneCount++;
                    result |= baseMask;
                }
                indexMask <<= 1;
            }
            baseMask <<= 1;
        }
        return result;
    }

    public static long getWildcardRangeCount(IP4AddressPO wildcardMask)
    {
        long mask = wildcardMask.getAddressAsUnsignedInt();
        long zerobitCount = 32 - Long.bitCount(mask);
        long trailingZeroCount = Long.numberOfTrailingZeros(mask);

        long rangeBitCount = zerobitCount - trailingZeroCount;

        long result = 1L << rangeBitCount;

        return result;
    }

    public static boolean isValidNetmask(IP4AddressPO netmask)
    {
        if (netmask == null) {
            return false;
        }
        long myInt = netmask.getAddressAsUnsignedInt();

        long myResult = Long.bitCount(myInt);
        if (myResult == 0) {
            return true;
        }
        if (Long.numberOfLeadingZeros(myInt) != 0) {
            return false;
        }
        if (Long.numberOfTrailingZeros(myInt) + myResult != 32) {
            return false;
        }
        return true;
    }

    public static int getNetmaskLength(IP4AddressPO netmask)
    {
        long myInt = netmask.getAddressAsUnsignedInt();
        int myResult = Long.bitCount(myInt);
        if (myResult == 0) {
            return 0;
        }
        if (Long.numberOfLeadingZeros(myInt) != 32) {
            throw new IllegalArgumentException("This is not a valid netmask:" + netmask.toDisplayString());
        }
        if (Long.numberOfTrailingZeros(myInt) + myResult != 32) {
            throw new IllegalArgumentException("This is not a valid netmask:" + netmask.toDisplayString());
        }
        return myResult;
    }

    public static IP4AddressPO getNetmask(int maskLength)
    {
        if ((maskLength < 0) || (maskLength > 32)) {
            throw new IllegalArgumentException("Mask length should be between 0 to 32");
        }
        if (maskLength == 0) {
            return IP4AddressPO.MIN;
        }
        long myMask = Integer.MIN_VALUE >> maskLength - 1;

        return IP4AddressPO.create(myMask);
    }

    public static IPAddressRange getIPRange(IPAddressPO prefix, int maskLength)
    {
        return prefix.toRange(maskLength);
    }

    public static IP4AddressPO convertIosStyleWildcardToNetmaskStyle(IP4AddressPO iosWildcard)
    {
        long myint = iosWildcard.getAddressAsUnsignedInt();
        long regularWildcard = myint ^ 0xFFFFFFFFL;
        IP4AddressPO mask = IP4AddressPO.create(regularWildcard);

        return mask;
    }

    public static boolean isValidIp4Literal(String ipStr)
    {
        return IP4PATTERN.matcher(ipStr).matches();
    }

    public static boolean isPublicIp(IP4AddressPO ip)
    {
        if (ip == null) {
            return false;
        }
        IP4AddressRangePO[] myRanges = {IP4AddressRangePO.create("10.0.0.0", "10.255.255.255"), IP4AddressRangePO.create("172.16.0.0", "172.31.255.255"), IP4AddressRangePO.create("192.168.0.0", "192.168.255.255")};
        for (IP4AddressRangePO myr : myRanges) {
            if (myr.contains(ip)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        long a = IPUtil.unsignedIntToLong("255.");
    }


}

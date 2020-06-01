package com.nspm.antlr4.common.utils;

import com.nspm.antlr4.common.domain.ip.IP4AddressRangePO;
import com.nspm.antlr4.common.domain.ip.IP6AddressPO;
import com.nspm.antlr4.common.domain.ip.IP6AddressRangePO;
import com.nspm.antlr4.common.interfaces.LongRange;
import com.nspm.antlr4.common.interfaces.Range;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author luwei
 * @date 2019/9/5
 */
@Slf4j
public class RangeUtil {

    //排序、合并
    public static <T extends Range> List<T> sortAndCompressLongRanges(List<T> ranges) {
        if (ranges == null || ranges.isEmpty()) {
//            log.error("排序时，参数为空");
            return ranges;
        }

        //集合内排序
        Collections.sort(ranges);

        List<T> result = new ArrayList(ranges.size());

        //循环集合的下标
        int index = 0;

        //取集合中第1个，即最小的那个
        LongRange firstRange = (LongRange) ranges.get(0);
        long currentStart = firstRange.getLongStart();
        int currentStartIndex = 0;
        long currentEnd = firstRange.getLongEnd();
        int currentEndIndex = 0;
        index++;
        while (index < ranges.size()) {
            //依次取下一个对象
            LongRange myRange = (LongRange) ranges.get(index);
            //如果当前的开始 <=  上一个的结束 注：排序后小于可忽略，只看等于
            if (myRange.getLongStart() <= currentEnd) {
                //如果当前的结束 <= 上一个的结束。 表示：当前的范围 = 上一个的范围，是一样的
                if (myRange.getLongEnd() <= currentEnd) {
                    index++;
                } else {
                    //是连续的，要合并
                    currentEnd = myRange.getLongEnd();
                    currentEndIndex = index;
                    index++;
                }
            } else if (myRange.getLongStart() == currentEnd + 1L) {
                //如果当前的开始 = 上一个结束+1，即是连续的，要合并
                currentEnd = myRange.getLongEnd();
                currentEndIndex = index;
                index++;
            } else {
                //如果当前的开始 > 上一个结束+1， 即中间有差，要当成2个对象处理
                if (currentStartIndex == currentEndIndex) {
                    result.add(ranges.get(currentStartIndex));
                } else {
                    result.add((T) myRange.createLongRange(currentStart, currentEnd));
                }
                currentStart = myRange.getLongStart();
                currentStartIndex = index;
                currentEnd = myRange.getLongEnd();
                currentEndIndex = index;
                index++;
            }
        }
        if (currentStartIndex == currentEndIndex) {
            result.add(ranges.get(currentStartIndex));
        } else {
            result.add((T) firstRange.createLongRange(currentStart, currentEnd));
        }
        return result;
    }

    public static <T extends Range> List<IP6AddressRangePO> sortAndCompressIP6Ranges(List<T> ranges) {
        Collections.sort(ranges);

        List<IP6AddressRangePO> result = new ArrayList();

        int index = 0;
        IP6AddressRangePO firstRange = (IP6AddressRangePO) ranges.get(0);
        IP6AddressPO currentStart = firstRange.getStart();
        int currentStartIndex = 0;
        IP6AddressPO currentEnd = firstRange.getEnd();
        int currentEndIndex = 0;
        index++;
        while (index < ranges.size()) {
            IP6AddressRangePO myRange = (IP6AddressRangePO) ranges.get(index);
            if (myRange.getStart().compareTo(currentEnd) <= 0) {
                if (myRange.getEnd().compareTo(currentEnd) <= 0) {
                    index++;
                } else {
                    currentEnd = myRange.getEnd();
                    currentEndIndex = index;
                    index++;
                }
            } else if (myRange.getStart().equals(currentEnd.addOne())) {
                currentEnd = myRange.getEnd();
                currentEndIndex = index;
                index++;
            } else {
                if (currentStartIndex == currentEndIndex) {
                    result.add((IP6AddressRangePO) ranges.get(currentStartIndex));
                } else {
                    result.add(IP6AddressRangePO.create(currentStart, currentEnd));
                }
                currentStart = myRange.getStart();
                currentStartIndex = index;
                currentEnd = myRange.getEnd();
                currentEndIndex = index;
                index++;
            }
        }
        if (currentStartIndex == currentEndIndex) {
            result.add((IP6AddressRangePO) ranges.get(currentStartIndex));
        } else {
            result.add(IP6AddressRangePO.create(currentStart, currentEnd));
        }
        return result;
    }

    public static <T extends LongRange> List<T> negateLongRange(List<T> ranges, T anyRange) {
        List<T> mySortedRanges = sortAndCompressLongRanges(ranges);
        return negateSortedAndNoOverlapLongRange(mySortedRanges, anyRange);
    }

    public static List<IP6AddressRangePO> negateIP6Range(List<IP6AddressRangePO> ranges) {
        List<IP6AddressRangePO> mySortedRanges = sortAndCompressIP6Ranges(ranges);
        return negateSortedAndNoOverlapIP6Ranges(mySortedRanges);
    }

    public static <T extends LongRange> List<T> negateSortedAndNoOverlapLongRange(List<T> ranges, T anyRange) {
        long min = anyRange.getLongStart();
        long max = anyRange.getLongEnd();

        long currentStart = min;
        long currentPointer = -1L;

        List<T> result = new ArrayList();
        for (LongRange myRange : ranges) {
            currentPointer = myRange.getLongStart() - 1L;
            if (currentPointer >= currentStart) {
                result.add((T) myRange.createLongRange(currentStart, currentPointer));
            }
            currentStart = myRange.getLongEnd() + 1L;
        }
        if (currentStart <= max) {
            result.add((T) anyRange.createLongRange(currentStart, max));
        }
        return result;
    }

    public static int compareRanges(Range[] left, Range[] right) {
        if ((left == null) && (right == null)) {
            return 0;
        }
        if ((left == null) && (right != null)) {
            return -1;
        }
        if ((left != null) && (right == null)) {
            return 1;
        }
        int mysize = left.length < right.length ? left.length : right.length;
        for (int i = 0; i < mysize; i++) {
            int val = left[i].compareTo(right[i]);
            if (val != 0) {
                return val;
            }
        }
        if (left.length > right.length) {
            return 1;
        }
        if (left.length == right.length) {
            return 0;
        }
        return -1;
    }


    //2个IP long 类型排序
    public static int compareLongRange(LongRange left, LongRange right) {
        if (left.getLongStart() < right.getLongStart()) {
            return -1;
        }
        if (left.getLongStart() > right.getLongStart()) {
            return 1;
        }
        if (left.getLongEnd() < right.getLongEnd()) {
            return -1;
        }
        if (left.getLongEnd() > right.getLongEnd()) {
            return 1;
        }
        return 0;
    }

    public static Range[] negateSortedAndNoOverlapRanges(Range[] ranges) {
        if ((ranges == null) || (ranges.length == 0)) {
            throw new IllegalArgumentException("Ranges couldn't be null or empty.");
        }
        Range sampleRange = ranges[0];

        Range.RangeValueType mytype = sampleRange.getRangeValueType();

        Range[] result = null;
        switch (mytype) {
            case IP6:
                List<IP6AddressRangePO> ip6Ranges = negateSortedAndNoOverlapIP6Ranges(Arrays.asList((IP6AddressRangePO[]) ranges));
                result = (Range[]) ip6Ranges.toArray(new IP6AddressRangePO[ip6Ranges.size()]);
                break;
            case LONG:
                List<LongRange> myRanges = negateSortedAndNoOverlapLongRange(Arrays.asList((LongRange[]) ranges), (LongRange) sampleRange.getAny());
                result = (Range[]) myRanges.toArray(sampleRange.createRangeArray(myRanges.size()));
                break;
            default:
                throw new IllegalArgumentException("Unknown type:" + mytype);
        }
        return result;
    }

    public static List<IP6AddressRangePO> negateSortedAndNoOverlapIP6Ranges(List<IP6AddressRangePO> myranges) {
        IP6AddressPO currentStart = IP6AddressPO.MIN;
        IP6AddressPO currentPointer = null;

        List<IP6AddressRangePO> result = new ArrayList();
        for (IP6AddressRangePO myRange : myranges) {
            IP6AddressPO mystart = myRange.getStart();
            if (mystart.equals(IP6AddressPO.MIN)) {
                IP6AddressPO myend = myRange.getEnd();
                if (myend.equals(IP6AddressPO.MAX)) {
                    currentStart = null;
                    break;
                }
                currentStart = myend.addOne();
            } else {
                currentPointer = mystart.minusOne();
                result.add(IP6AddressRangePO.create(currentStart, currentPointer));

                IP6AddressPO myend = myRange.getEnd();
                if (!myend.equals(IP6AddressPO.MAX)) {
                    currentStart = myend.addOne();
                } else {
                    currentStart = null;
                }
            }
        }
        if (currentStart != null) {
            result.add(IP6AddressRangePO.create(currentStart, IP6AddressPO.MAX));
        }
        return result;
    }

    public static <T> T intern(ConcurrentHashMap<T, T> cache, T obj) {
        T result = cache.get(obj);
        if (result != null) {
            return result;
        }
        result = cache.putIfAbsent(obj, obj);
        if (result == null) {
            result = obj;
        }
        return result;
    }


    public static void main(String[] args) {
        List<IP4AddressRangePO> addressRanges = new ArrayList<>();
        IP4AddressRangePO address =  IP4AddressRangePO.create("192.168.2.36", "192.168.2.37");
        IP4AddressRangePO address2 =  IP4AddressRangePO.create("192.168.2.39", "192.168.2.40");
//        IP4AddressRangePO address3 =  IP4AddressRangePO.create("192.168.2.36", "192.168.2.37");
        addressRanges.add(address);
        addressRanges.add(address2);
//        addressRanges.add(address3);

        RangeUtil.sortAndCompressLongRanges(addressRanges);


    }

}

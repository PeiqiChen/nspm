package com.nspm.antlr4.common.utils;

import com.nspm.antlr4.exceptions.ZeeRuntimeException;
import com.nspm.antlr4.exceptions.ZeeValidationException;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * @author luwei
 * @date 2019/9/9
 */
public class ObjectUtil {

    public static String getUUID() {
        String result = UUID.randomUUID().toString();
        result = result.replace("-", "");
        return result;
    }

    public static <T> Set<T> intersection(Set<T> setOne, Set<T> setTwo) {
        if (setOne == null) {
            return Collections.emptySet();
        }
        if (setTwo == null) {
            return Collections.emptySet();
        }

        Set<T> mySmallSet = null;
        Set<T> myLargeSet = null;

        if (setOne.size() <= setTwo.size()) {
            mySmallSet = setOne;
            myLargeSet = setTwo;
        } else {
            mySmallSet = setTwo;
            myLargeSet = setOne;
        }

        Set<T> result = new HashSet();

        for (T myObj : mySmallSet) {
            if (myLargeSet.contains(myObj)) {
                result.add(myObj);
            }
        }

        return result;
    }

    public static int getCoreCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static <T> List<T[]> splitArray(T[] array, int max) {
        int leftover = array.length;

        List<T[]> result = new ArrayList();

        int from = 0;

        while (leftover > 0) {
            int size = leftover > max ? max : leftover;
            result.add(Arrays.copyOfRange(array, from, from + size));

            from += size;
            leftover -= size;
        }

        return result;
    }

    public static List<byte[]> splitArray(byte[] array, int max) {
        int leftover = array.length;

        List<byte[]> result = new ArrayList();

        int from = 0;

        while (leftover > 0) {
            int size = leftover > max ? max : leftover;
            result.add(Arrays.copyOfRange(array, from, from + size));

            from += size;
            leftover -= size;
        }

        return result;
    }

    public static byte[] combineArray(List<byte[]> arrays) {
        int count = 0;
        for (byte[] array : arrays) {
            if (array != null) {
                count += array.length;
            }
        }
        byte[] result = new byte[count];

        int arrIndex = 0;
        for (byte[] array : arrays) {
            if (array != null) {
                /*     */
                /*     */
                System.arraycopy(array, 0, result, arrIndex, array.length);
                arrIndex += array.length;
            }
        }
        return result;
    }

    public static <T> boolean isNullOrEmpty(T[] array) {
        return (array == null) || (array.length == 0);
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return (collection == null) || (collection.isEmpty());
    }

    public static <K, V> boolean isNullOrEmpty(Map<K, V> map) {
        return (map == null) || (map.isEmpty());
    }

    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static <T> boolean isSameSet(Set<T> left, Set<T> right) {
        if ((left == null) && (right == null)) {
            return true;
        }

        if ((left == null) && (right != null) && (right.isEmpty())) {
            return true;
        }
        if ((left != null) && (left.isEmpty()) && (right == null)) {
            return true;
        }

        if ((left != null) && (right == null)) {
            return false;
        }

        if ((left == null) && (right != null)) {
            return false;
        }

        return left.equals(right);
    }

    public static <T> boolean isSameList(List<T> left, List<T> right) {
        if ((left == null) && (right == null)) {
            return true;
        }

        if ((left == null) && (right != null) && (right.isEmpty())) {
            return true;
        }
        if ((left != null) && (left.isEmpty()) && (right == null)) {
            return true;
        }

        if ((left != null) && (right == null)) {
            return false;
        }

        if ((left == null) && (right != null)) {
            return false;
        }

        return left.equals(right);
    }

    public static <T> T newInstance(Class<T> clz) {
        try {
            Constructor<T> ctor = clz.getDeclaredConstructor(new Class[0]);

            ctor.setAccessible(true);
            return (T) ctor.newInstance(new Object[0]);
        } catch (Exception e) {
            throw new ZeeRuntimeException(e);
        }
    }

    @SafeVarargs
    public static <T extends Enum<T>> boolean containsAny(EnumSet<T> myset, T... vals) {
        if ((myset == null) || (myset.isEmpty())) {
            return false;
        }

        if ((vals == null) || (vals.length == 0)) {
            return false;
        }

        for (T myopt : vals) {
            if (myset.contains(myopt)) {
                return true;
            }
        }

        return false;
    }

    @SafeVarargs
    public static <T extends Enum<T>> boolean containsAll(EnumSet<T> myset, T... vals) {
        if ((myset == null) || (myset.isEmpty())) {
            return false;
        }

        if ((vals == null) || (vals.length == 0)) {
            return false;
        }

        return myset.containsAll(Arrays.asList(vals));
    }

    public static void validateBackendPageParam(Integer page, Integer pageSize) {
        if ((page == null) && (pageSize == null)) {
            return;
        }

        if ((page == null) || (pageSize == null)) {
            throw new ZeeValidationException("Both page and psize parameter are required");
        }

        if (page.intValue() < 0) {
            throw new ZeeValidationException("Page parameter should be >= 0");
        }
        if (pageSize.intValue() < 1) {
            throw new ZeeValidationException("Psize parameter should be >= 1");
        }
    }

}

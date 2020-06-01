package com.nspm.antlr4.common.utils;

import java.util.UUID;

/**
 * @author luwei
 * @date 2019/5/10
 */
public class UUIDUtil {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String toUuidNoDash(UUID uuid) {
        String result = uuid.toString();
        result = result.replace("-", "");
        return result;
    }

}

package com.nspm.antlr4.plugin;

import com.nspm.antlr4.common.domain.obj.GenericDevicePO;

import java.util.Map;
import java.util.Set;

/**
 * @author zc
 * @date 2019/09/16
 */
public interface DevicePlugin  {
//    List<DeviceMetadata> getDeviceMetadata();

    Set<String> getSupportedDeviceFamily();

    GenericDevicePO getDevice(String var1, Map<String, Object> var2) throws Exception;

    boolean canProcess(String var1) throws Exception;
}

package com.nspm.antlr4.common.utils;

import com.nspm.antlr4.common.deviceInterface.DeviceInterfacePO;
import com.nspm.antlr4.common.domain.obj.DevicePO;
import com.nspm.antlr4.exceptions.ZeeError;
import com.nspm.antlr4.exceptions.ZeeErrorName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author luwei
 * @date 2019/10/25
 */
public class DeviceValidator {

    public static void validateDevice(DevicePO myd, List<ZeeError> result) {
        validateDeviceInterfaces(myd.getDeviceInterfaces(), result);
    }

    private static void validateDeviceInterfaces(List<DeviceInterfacePO> deviceInterfaces, List<ZeeError> result) {
        if ((deviceInterfaces == null) || (deviceInterfaces.isEmpty())) {
            return;
        }

        Set<String> names = new HashSet();
        List<String> duplicates = new ArrayList();
        for (DeviceInterfacePO mydi : deviceInterfaces) {
            String myname = mydi.getName();
            if ((myname != null) && (!myname.isEmpty())) {
                if (myname.trim().isEmpty()) {
                    ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_NAME_EMPTY, null, "Device interface name shouldn't be empty string.");
                    result.add(mye);
                } else if (names.contains(myname)) {
                    duplicates.add(myname);
                } else {
                    names.add(myname);
                }
            }
        }
        if (!duplicates.isEmpty()) {
            ZeeError mye = new ZeeError(ZeeErrorName.VALIDATION_DEVICE_INTERFACE_NOT_UNIQUE_NAME, null, "Device interface name not unique:" + duplicates);
            result.add(mye);
        }
    }

}

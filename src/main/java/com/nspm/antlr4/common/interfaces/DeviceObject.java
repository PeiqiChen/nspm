package com.nspm.antlr4.common.interfaces;

import com.nspm.antlr4.common.domain.ConfigLinePO;
import com.nspm.antlr4.common.enums.DeviceObjectTypeEnum;

/**
 * @author luwei
 * @date 2019/9/9
 */
public abstract   interface DeviceObject {

    public abstract String getId();

    public abstract void setId(String paramString);

    public abstract Long getVersion();

    public abstract void setVersion(Long paramLong);

    public abstract String getUuid();

    public abstract void setUuid(String paramString);

    public abstract String getName();

    public abstract ConfigLinePO getLineNumbers();

    public abstract void setLineNumbers(ConfigLinePO paramConfigLine);

    public abstract DeviceObjectTypeEnum getDeviceObjectType();

    public abstract String getDeviceUuid();

    public abstract void setDeviceUuid(String paramString);


}

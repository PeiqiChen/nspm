package com.nspm.antlr4.common.enums;

/**
 * @author luwei
 * @date 2019/9/9
 */
public enum ConfigTypeEnum {

    MANUAL_UPLOAD(10),
    STARTUP_CONFIG(10),
    RUNNING_CONFIG(10);

    private int priority;

    private ConfigTypeEnum(int priority)
    {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }


}

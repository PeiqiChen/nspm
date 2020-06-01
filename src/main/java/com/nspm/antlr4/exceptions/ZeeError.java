package com.nspm.antlr4.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author luwei
 * @date 2019/9/9
 */
public class ZeeError {

    private static final String ERROR_CODE_RESOURCE_BASE = "errorCode/ZeeErrorCode";
    private ZeeErrorName errorName;
    @JsonIgnore
    private Object[] args;
    private String systemMessage;

    protected ZeeError() {}

    public ZeeError(ZeeErrorName errorName, Object[] args, String systemMessage)
    {
        this.errorName = errorName;
        this.args = args;
        this.systemMessage = systemMessage;
    }

    public ZeeErrorName getErrorName() {
        return this.errorName;
    }

    @JsonProperty
    public String getErrorCode() {
        return this.errorName.getErrorCode();
    }

    @JsonIgnore
    public void setErrorCode(String mock) {}

    public Object[] getArgs()
    {
        return this.args;
    }

    public String getSystemMessage() {
        return this.systemMessage;
    }

    public void setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
    }

    @JsonProperty
    public String getUserMessage() {
        return ZeeMessageSource.getMessage("errorCode/ZeeErrorCode", this.errorName.getPropertyName(), this.args);
    }

    @JsonIgnore
    private void setUserMessage(String toIgnore) {}

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(this.errorName).append(":").append(getSystemMessage());
        return builder.toString();
    }


}


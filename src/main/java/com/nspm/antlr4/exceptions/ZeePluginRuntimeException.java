package com.nspm.antlr4.exceptions;

/**
 * @author zc
 * @date 2019/09/17
 */
public class ZeePluginRuntimeException extends ZeeRuntimeException {
    private static final long serialVersionUID = 1L;

    public ZeePluginRuntimeException(ZeeError error, Throwable cause) {
        super(error, cause);
    }

    public ZeePluginRuntimeException(ZeeErrorName errorName, Object[] args, String message, Throwable cause) {
        super(errorName, args, message, cause);
    }

    public ZeePluginRuntimeException(String message, Throwable cause) {
        super(ZeeErrorName.PLUGIN_RUNTIME_DEFAULT, (Object[])null, message, cause);
    }

    public ZeePluginRuntimeException(String message) {
        super(ZeeErrorName.PLUGIN_RUNTIME_DEFAULT, (Object[])null, message);
    }

    public ZeePluginRuntimeException(Throwable cause) {
        super(ZeeErrorName.PLUGIN_RUNTIME_DEFAULT, (Object[])null, (String)null, cause);
    }
}

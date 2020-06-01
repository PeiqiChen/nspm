package com.nspm.antlr4.exceptions;


/**
 * @author luwei
 * @date 2019/9/5
 */
public class ZeeRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ZeeError zeeError;

    public ZeeRuntimeException(String message) {
        super(message);
    }

    public ZeeRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZeeRuntimeException(Throwable cause) {
        super(cause);
    }

    protected ZeeRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ZeeRuntimeException(ZeeErrorName errorName, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.zeeError = new ZeeError(errorName, args, message);
    }

    public ZeeRuntimeException(ZeeError error, Throwable cause) {
        super(error.getSystemMessage(), cause);
        this.zeeError = error;
    }


    public ZeeRuntimeException(ZeeErrorName errorName, Object[] args, String message) {
        super(message);
        this.zeeError = new ZeeError(errorName, args, message);
    }

    public ZeeError getZeeError() {
        return zeeError;
    }

    public void setZeeError(ZeeError zeeError) {
        this.zeeError = zeeError;
    }


    public ZeeErrorName getErrorName() {
        return this.zeeError.getErrorName();
    }
}

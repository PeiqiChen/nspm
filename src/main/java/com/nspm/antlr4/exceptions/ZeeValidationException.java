package com.nspm.antlr4.exceptions;

/**
 * @author luwei
 * @date 2019/9/9
 */
public class ZeeValidationException extends ZeeRuntimeException {

    private static final long serialVersionUID = 1L;

    public ZeeValidationException(ZeeErrorName errorName, Object[] args, String message, Throwable cause)
    {
        super(errorName, args, message, cause);
    }

    public ZeeValidationException(String message, Throwable cause) {
        super(ZeeErrorName.VALIDATION_EXCEPTION, null, message, cause);
    }

    public ZeeValidationException(String message) {
        super(ZeeErrorName.VALIDATION_EXCEPTION, null, message);
    }

    public ZeeValidationException(Throwable cause) {
        super(ZeeErrorName.VALIDATION_EXCEPTION, null, null, cause);
    }
}

package com.newprj.blog.common.exception;
import com.newprj.blog.common.constants.ErrorCode;

public class InternalServerException extends BaseRuntimeException {

    private static final long serialVersionUID = 6719974844964427804L;

    @Override
    public ErrorCode errorCode() {
        return ErrorCode.INTERNAL_ERROR;
    }

    @Override
    public String error() {
        return "Internal server";
    }

    public InternalServerException(Throwable t) {
        super(t);
    }
    public InternalServerException(String s) {
        super(s);
    }
    public InternalServerException() {
        super();
    }

    @Override
    public String userErrorMessage() {
        return null;
    }
}

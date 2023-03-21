package com.newprj.blog.common.exception;

import com.newprj.blog.common.constants.ErrorCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataNotFoundException extends BaseRuntimeException {

    private static final long serialVersionUID = -7469913817876019465L;

    @Override
    public ErrorCode errorCode() {
        return ErrorCode.DATA_NOT_FOUND;
    }

    @Override
    public String error() {
        return "Data not found";
    }

    public DataNotFoundException(Throwable t) {
        super(t);
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }
    @Override
    public String userErrorMessage() {
        return null;
    }
}


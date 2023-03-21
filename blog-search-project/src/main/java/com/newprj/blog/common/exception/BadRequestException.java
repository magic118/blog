package com.newprj.blog.common.exception;

import com.newprj.blog.common.constants.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BadRequestException extends BaseRuntimeException {

    @Override
    public ErrorCode errorCode() {
        return ErrorCode.BAD_REQUEST_ERROR;
    }

    @Override
    public String error() {
        return "Expectation Failed";
    }

    public BadRequestException(Throwable t) {
        super(t);
    }

    @Override
    public String userErrorMessage() {
        return null;
    }

    public BadRequestException(String msg) {
        super(msg);
    }

}

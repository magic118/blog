package com.newprj.blog.common.exception;


import com.newprj.blog.common.constants.ErrorCode;

public abstract class BaseRuntimeException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 6335109083862548798L;

    public abstract ErrorCode errorCode();

    public abstract String error();

    public BaseRuntimeException() {
        super();

    }

    public BaseRuntimeException(String msg) {
        super(msg);

    }

    public BaseRuntimeException(Throwable t) {
        super(t);

    }

    public BaseRuntimeException(String s, Throwable t) {
        super(s, t);

    }

    public abstract String userErrorMessage();

}

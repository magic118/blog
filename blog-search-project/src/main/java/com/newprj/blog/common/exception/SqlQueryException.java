package com.newprj.blog.common.exception;

import com.newprj.blog.common.constants.ErrorCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SqlQueryException extends BaseRuntimeException {

    private static final long serialVersionUID = -6302514118640040972L;

    @Override
    public ErrorCode errorCode() {
        return ErrorCode.SQL_QUERY_ERROR;
    }

    @Override
    public String error() {
        return "Sql query";
    }

    public SqlQueryException(Throwable t) {
        super(t);
    }

    public SqlQueryException(String msg) {
        super(msg);
    }
    @Override
    public String userErrorMessage() {
        return null;
    }
}

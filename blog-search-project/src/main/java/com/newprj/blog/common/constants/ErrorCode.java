package com.newprj.blog.common.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ErrorCode {
    OK("OK", HttpStatus.OK),
    CREATED("CREATED", HttpStatus.CREATED),
    DATA_NOT_FOUND("DATA_NOT_FOUND", HttpStatus.NOT_FOUND),
    UNSUPPORTED_MEDIA_TYPE("UNSUPPORTED_MEDIA_TYPE", HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    SQL_QUERY_ERROR("SQL_QUERY_ERROR", HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR("BAD_REQUEST_ERROR", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN_ERROR("INVALID_TOKEN_ERROR", HttpStatus.UNAUTHORIZED),
    DATA_NOT_ALLOW("DATA_NOT_ALLOW", HttpStatus.NOT_ACCEPTABLE),
    INTERNAL_ERROR("INTERNAL_ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_CONFLICT("DATA_CONFLICT", HttpStatus.CONFLICT),
    CUSTOM_ERROR("CUSTOM_ERROR", HttpStatus.EXPECTATION_FAILED),
    FORBIDDEN("FORBIDDEN", HttpStatus.FORBIDDEN),
    NO_CONTENT("NO_CONTENT", HttpStatus.NO_CONTENT);
    @Getter
    private final String value;

    @Getter
    private final HttpStatus status;

    ErrorCode(String v, HttpStatus s) {
        this.value = v.toUpperCase();
        this.status = s;
    }

    public static ErrorCode fromString(String s) {
        return Arrays.stream(ErrorCode.values()).filter(v -> v.value.equalsIgnoreCase(s)).findFirst().orElseThrow(() -> new IllegalArgumentException(
                "Unknown value :"
                        + s));
    }
}

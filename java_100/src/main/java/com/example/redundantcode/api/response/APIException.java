package com.example.redundantcode.api.response;

import lombok.Getter;

public class APIException extends RuntimeException {
    @Getter
    private final int errorCode;
    @Getter
    private final String errorMessage;

    public APIException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public APIException(Throwable cause, int errorCode, String errorMessage) {
        super(errorMessage, cause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

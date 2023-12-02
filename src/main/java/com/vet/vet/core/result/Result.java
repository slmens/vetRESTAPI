package com.vet.vet.core.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private boolean status;
    private String message;
    private String httpCode;

    public Result(boolean status, String message, String httpCode) {
        this.status = status;
        this.message = message;
        this.httpCode = httpCode;
    }
}

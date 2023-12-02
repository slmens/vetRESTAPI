package com.vet.vet.core.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultData<T> extends Result {
    private T data;

    public ResultData(boolean status, String message, String httpCode, T data) {
        super(status, message, httpCode);
        this.data = data;
    }
}

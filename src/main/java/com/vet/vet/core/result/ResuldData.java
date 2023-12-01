package com.vet.vet.core.result;

import lombok.Getter;

@Getter
public class ResuldData<T> extends Result {
    private T data;

    public ResuldData(boolean status, String message, String httpCode, T data) {
        super(status, message, httpCode);
        this.data = data;
    }
}

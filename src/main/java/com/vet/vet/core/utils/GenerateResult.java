package com.vet.vet.core.utils;

import com.vet.vet.core.result.ResuldData;

public class GenerateResult {

    public static <T> ResuldData<T> created(T data){
        return new ResuldData<>(true,Msg.CREATED,"201",data);
    }

    public static <T> ResuldData<T> validateError(T data){
        return new ResuldData<>(false, Msg.VALIDATE_ERROR,"400",data);
    }
}

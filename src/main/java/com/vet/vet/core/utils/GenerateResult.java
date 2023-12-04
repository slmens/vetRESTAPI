package com.vet.vet.core.utils;

import com.vet.vet.core.result.ResultData;

public class GenerateResult {

    public static <T> ResultData<T> created(T data){
        return new ResultData<>(true,Msg.CREATED,"201",data);
    }

    public static <T> ResultData<T> validateError(T data){
        return new ResultData<>(false, Msg.VALIDATE_ERROR,"400",data);
    }

    public static <T> ResultData<T> dataAccessError(String msg){
        return new ResultData<>(false, msg,"500",null);
    }
}

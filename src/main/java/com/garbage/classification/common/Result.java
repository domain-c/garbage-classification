package com.garbage.classification.common;

import lombok.Data;

/**
 * @author: domain
 * @date: 2018/9/14
 */
@Data
public class Result<T> {

    public static final int SUCCEED_CODE = 1000000;

    private int res = 1000001;
    private String resMsg;
    private Object obj;

    private Result(){}

    private Result(int res, Object obj,String resMsg){
        this.res = res;
        this.obj = obj;
        this.resMsg = resMsg;
    }

    private Result(int res, Object obj){
        this.res = res;
        this.obj = obj;
    }

    private Result(int res, String resMsg){
        this.res = res;
        this.resMsg = resMsg;
    }

    public static <T> Result<T> setSucceed(Object obj, String resMsg) {
        return new Result<>(1000000, obj, resMsg);
    }

    public static <T> Result<T> setSucceed(Object obj) {
        return new Result<>(1000000, obj);
    }

    public static <T> Result<T> setSucceedMsg(String resMsg) {
        return new Result<>(1000000, resMsg);
    }

    public static <T> Result<T> setFailMsg(String resMsg) {
        return new Result<>(1000001, resMsg);
    }
}

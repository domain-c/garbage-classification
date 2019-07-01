package com.garbage.classification.common;


import lombok.Data;

import java.util.Date;


/**
 * @author chenguotu
 * @date 2018/9/5
 * 响应结果
 */
@Data
public class ResponseResult<T> {

    private int code;
    private String message;
    private String lang;
    private String accessToken;

    private String sign;
    private int source;
    private Date time;
    private T value;

    private ResponseResult() {}

//    private ResponseResult(Builder builder) {
//        this.code = builder.code;
//        this.message = builder.message;
//        this.lang = builder.lang;
//        this.accessToken = builder.accessToken;
//        this.sign = builder.sign;
//        this.source = builder.source;
//        this.time = builder.time;
//        this.value = (T) builder.value;
//    }

    private ResponseResult(int code, T value) {
        this(code, value, "");
    }

    private ResponseResult(int code, T value, String message) {
        this.time = new Date();
        this.lang = "zh_Cn";
        this.source = 201;
        this.code = code;
        this.value = value;
        this.message = message;

    }

    private ResponseResult(int code, String message) {
        this.time = new Date();
        this.lang = "zh_Cn";
        this.source = 201;
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseResult<T> setError(String msg, T data) {
        return new ResponseResult<>(200, data, msg);
    }

    public static <T> ResponseResult<T> setSuccess(T value, String message) {
        return new ResponseResult<>(CommonCode.HTTP_SUCCESS, value, message);
    }

    public static <T> ResponseResult<T> setSuccess(String message) {
        return new ResponseResult<>(CommonCode.HTTP_SUCCESS, message);
    }

    public static <T> ResponseResult<T> setError(String message) {
        return new ResponseResult<>(CommonCode.HTTP_ERROR, message);
    }

//    public static <T> ResponseResult<T> setTokenError(T value, String message) {
//        return new ResponseResult<>(CommonCode.HTTP_TOKEN_ERROR, value, message);
//    }

    /**
     * 创建构建器
     */
    public static class Builder<T> {
        private int code;
        private String message;
        private String lang;
        private String accessToken;

        private String sign;
        private int source;
        private Date time;
        private T value;

        public Builder code(int code){
            this.code = code;
            return this;
        }

        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder lang(String lang){
            this.lang = lang;
            return this;
        }

        public Builder accessToken(String accessToken){
            this.accessToken = accessToken;
            return this;
        }

        public Builder sign(String sign){
            this.sign = sign;
            return this;
        }

        public Builder source(int source){
            this.source = source;
            return this;
        }

        public Builder time(){
            this.time = new Date();
            return this;
        }

        public Builder value(T value){
            this.value = value;
            return this;
        }

//       public ResponseResult builder(){
//            return new ResponseResult(this);
//        }
    }
}


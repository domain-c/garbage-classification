package com.garbage.classification.exception;

import com.garbage.classification.common.CommonCode;
import com.garbage.classification.common.ResponseResult;
import com.garbage.classification.utils.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author: domain
 * @date: 2018/9/4
 */
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * 404 - Not Found
     * 失效状态，通过errorController实现 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Object handleHttpRequestMethodNoHandlerFoundException(NoHandlerFoundException exception) throws Exception {
        log.info("不支持当前请求方法 " + exception);
        return JacksonUtil.obj2json(ResponseResult.setError(CommonCode.HTTP_NOT_FOUND));
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Object handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception) throws Exception {
        log.info("不支持当前请求方式 " + exception);
//        throw new NullPointerException();
        return JacksonUtil.obj2json(ResponseResult.setError(CommonCode.HTTP_METHOD_NOT_ALLOWED));
    }

//    /**
//     * 500 - Internal Server Error
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    public Object sysExpHandler(Exception exception) throws Exception {
//        log.error("系统异常 ",exception);
//        return JacksonUtil.obj2json(ResponseResult.setError(CommonCode.IN_SYSTEM_ERROR));
//    }
}

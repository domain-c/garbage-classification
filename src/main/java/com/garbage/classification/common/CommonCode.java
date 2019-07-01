package com.garbage.classification.common;

import java.io.Serializable;


/**
 * @author: domain
 * @date: 2018/9/5
 * 通用code
 */
public class CommonCode implements Serializable {

    /**
     * http 访问成功code
     */
    static final int HTTP_SUCCESS = 200;

    /**
     * http 访问成功code
     */
    static final int HTTP_ERROR = 400;

    /**
     * 语言类型
     */
    public static final String HTTP_LANG = "zh_Cn";

    /**
     * 内部调用成功
     */
    public static final int SUCCESS = 1000000;

    /**
     * 内部调用失败
     */
    public static final int FAIL = 1000001;



    /***  Exception  ****/
    public static final String HTTP_NOT_FOUND = "不支持当前请求方法";

    public static final String HTTP_METHOD_NOT_ALLOWED= "不支持当前请求方式";

    public static final String IN_SYSTEM_ERROR = "内部错误";

    public static final String REQUIRED_PARAMETER_MISSING = "缺少必要参数";

    public static final String ERROR_INCORRECT_USERNAME_OR_PASSWORD = "用户名或密码错误";

    public static final String ERROR_SIGN = "签名错误";

    /**
     * String baseUlr = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
     */
    public static final String PAGE_TRANSITION_URL = "";

}

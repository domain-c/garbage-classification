package com.garbage.classification.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: domain
 * @date: 2018/9/13
 */
@Slf4j
public class StringUtils {

    public static void printAccessDetails(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String timestamp = System.currentTimeMillis() + "";
        String url = httpServletRequest.getRequestURL().toString();
        String method = httpServletRequest.getMethod();
        String uri = httpServletRequest.getRequestURI();
        String queryString = httpServletRequest.getQueryString();
        String ip = httpServletRequest.getRemoteAddr();
        log.info(timestamp + ", url: {}, method: {}, uri: {}, params: {}, ip:{}", url, method, uri, queryString, ip);
    }
}

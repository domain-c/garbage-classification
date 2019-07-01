package com.garbage.classification.controller.base;

import com.garbage.classification.common.ResponseResult;
import com.garbage.classification.utils.JacksonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: domain
 * @date: 2018/9/5
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {


    @RequestMapping("/error")
    public ModelAndView errorNotFound(ModelAndView modelAndView){
        modelAndView.setViewName("error/404");
        return modelAndView;
    }

    @RequestMapping("/error/j")
    @ResponseBody
    public Object errorNotFound() throws Exception {
        return JacksonUtil.obj2json(ResponseResult.setError("未知请求"));
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

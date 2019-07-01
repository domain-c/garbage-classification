package com.garbage.classification.controller;

import com.garbage.classification.common.CommonCode;
import com.garbage.classification.common.ResObj;
import com.garbage.classification.common.ResponseResult;
import com.garbage.classification.common.Result;
import com.garbage.classification.entity.Employee;
import com.garbage.classification.entity.Garbage;
import com.garbage.classification.service.EmployeeService;
import com.garbage.classification.service.GarbageService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author domain
 * @date 2019-07-01
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    GarbageService garbageService;

    /**
     * 登录页面
     */
    @RequestMapping(value = "/toLogin", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestParam("userName") String userName,
                                @RequestParam("password") String password, HttpSession session) {
        ResponseResult responseResult = null;
        try {
            if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
                Result<Employee> result = employeeService.selectByUsername(userName);
                Employee employee = (Employee) result.getObj();
                if (employee != null) {
                    password = DigestUtils.md5DigestAsHex(password.getBytes());
                    if (employee.getPassword().equals(password)) {
                        employee.setPassword("");
                        session.setAttribute("adminUser", employee);
                        responseResult = ResponseResult.setSuccess("登录成功");
                    } else {
                        responseResult = ResponseResult.setError(CommonCode.ERROR_INCORRECT_USERNAME_OR_PASSWORD);
                    }
                } else {
                    responseResult = ResponseResult.setError(result.getRes() == 1000001 ? result.getResMsg() : "未找到该用户");
                }
            } else {
                responseResult = ResponseResult.setError(CommonCode.REQUIRED_PARAMETER_MISSING);
            }
        } catch (Exception e) {
            log.error(CommonCode.IN_SYSTEM_ERROR, e);
            responseResult = ResponseResult.setError(CommonCode.IN_SYSTEM_ERROR);
        }
        return responseResult;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public ResponseResult cancel(HttpSession session) {
        ResponseResult responseResult = ResponseResult.setSuccess("注销成功");
        session.removeAttribute("adminUser");
        return responseResult;
    }

    @RequestMapping(value = "/garbage", method = RequestMethod.GET)
    public ModelAndView garbage(ModelAndView modelAndView, Model model, HttpSession session) {
        modelAndView.setViewName("garbage/garbage_l");
        Employee employee = (Employee) session.getAttribute("adminUser");
        model.addAttribute("username", employee.getUsername());
        return modelAndView;
    }


    @RequestMapping(value = "/garbageL", method = RequestMethod.POST)
    public Map<String, Object> garbageL(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "5") int length,
                                        @RequestParam(required = false, defaultValue = "0") String type,
                                        @RequestParam(required = false, defaultValue = "") String key) {
        Map<String, Object> map = new HashMap<>(10);
        PageInfo<Garbage> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(start);
        pageInfo.setPageSize(length);
        Map<String, Object> param = new HashMap<>(1);
        if (!StringUtils.isEmpty(key) && Integer.valueOf(type) > 0) {
            param.put("garbageName", key.trim());
            param.put("belongClassification", Integer.valueOf(type));
        }
        Result<ResObj<Garbage>> result = garbageService.findAllGarbage(pageInfo, param);
        ResObj resObj = (ResObj) result.getObj();
        map.put("data", resObj.getList());
        // 总记录数
        map.put("recordsTotal", resObj.getTotal());
        // 过滤后的总记录数
        map.put("recordsFiltered", resObj.getTotal());
        return map;
    }

}

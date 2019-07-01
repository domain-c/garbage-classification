package com.garbage.classification.service;

import com.garbage.classification.common.Result;
import com.garbage.classification.entity.Employee;

/**
 * @author: domain
 * @date: 2018/9/14
 */
public interface EmployeeService extends BaseService<Employee> {

    /**
     * 用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    Result<Employee> selectByUsername(String username);

}

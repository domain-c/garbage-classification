package com.garbage.classification.service.impl;

import com.garbage.classification.common.CommonCode;
import com.garbage.classification.common.Result;
import com.garbage.classification.dao.EmployeeDao;
import com.garbage.classification.entity.Employee;
import com.garbage.classification.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author domain
 * @date 2019-07-01
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public Result<Employee> selectByUsername(String username) {
        Result result = null;
        try {
            Employee employee = employeeDao.selectByUsername(username);
            result = Result.setSucceed(employee);
        } catch (Exception e) {
            log.error(CommonCode.IN_SYSTEM_ERROR, e);
            result = Result.setFailMsg(CommonCode.IN_SYSTEM_ERROR);
        }
        return result;
    }
}

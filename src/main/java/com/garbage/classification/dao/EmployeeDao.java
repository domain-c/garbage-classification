package com.garbage.classification.dao;

import com.garbage.classification.dao.base.BaseDao;
import com.garbage.classification.dao.base.GenericBatis;
import com.garbage.classification.entity.Employee;

/**
 * @author chenguotu
 */
@GenericBatis
public interface EmployeeDao extends BaseDao<Employee> {

    /**
     * 用户名查找
     *
     * @param username
     * @return
     */
    Employee selectByUsername(String username);
}
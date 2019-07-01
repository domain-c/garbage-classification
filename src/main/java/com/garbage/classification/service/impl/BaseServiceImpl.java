package com.garbage.classification.service.impl;

import com.garbage.classification.dao.base.BaseDao;
import com.garbage.classification.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: domain
 * @date: 2018/9/5
 */
@Slf4j
public class BaseServiceImpl<T> implements BaseService<T> {

    public BaseServiceImpl(){super();}

    @Autowired
    protected BaseDao<T> baseDao;

    @Override
    public int insert(T obj) {
        return baseDao.insert(obj);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return baseDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(T obj) {
        return baseDao.insertSelective(obj);
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        return baseDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T obj) {
        return baseDao.updateByPrimaryKeySelective(obj);
    }

    @Override
    public int updateByPrimaryKey(T obj) {
        return baseDao.updateByPrimaryKey(obj);
    }
}

package com.garbage.classification.dao;

import com.garbage.classification.dao.base.BaseDao;
import com.garbage.classification.dao.base.GenericBatis;
import com.garbage.classification.entity.Garbage;

/**
 * @author chenguotu
 */
@GenericBatis
public interface GarbageDao extends BaseDao<Garbage> {

}
package com.garbage.classification.dao;

import com.garbage.classification.dao.base.BaseDao;
import com.garbage.classification.dao.base.GenericBatis;
import com.garbage.classification.entity.GarbageUnknown;

import java.util.List;
import java.util.Map;

/**
 * @author chenguotu
 */
@GenericBatis
public interface GarbageUnknownDao extends BaseDao<GarbageUnknown> {

    /**
     * 获取全部未知分类列表数据
     *
     * @param map 条件
     * @return List<GarbageUnknown>
     */
    List<GarbageUnknown> selectAllUnGarbage(Map<String, Object> map);
}
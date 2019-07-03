package com.garbage.classification.dao;

import com.garbage.classification.dao.base.BaseDao;
import com.garbage.classification.dao.base.GenericBatis;
import com.garbage.classification.entity.Garbage;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chenguotu
 */
@GenericBatis
public interface GarbageDao extends BaseDao<Garbage> {

    /**
     * 获取全部垃圾分类清单
     *
     * @param map 条件
     * @return List<Garbage>
     */
    List<Garbage> selectAllGarbage(Map<String, Object> map);

    /**
     * 获取全部垃圾分类信息
     *
     * @return List<Garbage>
     */
    List<Garbage> selectAllListGarbage();

    /**
     * 模糊查询
     *
     * @param garbageName 垃圾名称
     * @return List<Garbage>
     */
    List<Garbage> selectLikeGarbageName(@Param("garbageName") String garbageName);

}
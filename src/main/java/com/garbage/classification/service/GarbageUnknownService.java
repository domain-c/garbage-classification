package com.garbage.classification.service;

import com.garbage.classification.common.ResObj;
import com.garbage.classification.common.Result;
import com.garbage.classification.entity.GarbageUnknown;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author domain
 * @date 2019-07-12
 */
public interface GarbageUnknownService extends BaseService<GarbageUnknown> {

    /**
     * 分页获取未知分类数据
     *
     * @param pageInfo 分页
     * @param map      条件
     * @return List<Garbage>
     */
    Result<ResObj<GarbageUnknown>> findAllUnGarbage(PageInfo<GarbageUnknown> pageInfo, Map<String, Object> map);
}

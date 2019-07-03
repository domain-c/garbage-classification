package com.garbage.classification.service;

import com.garbage.classification.common.ResObj;
import com.garbage.classification.common.Result;
import com.garbage.classification.entity.Garbage;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author domain
 * @date 2019-07-01
 */
public interface GarbageService extends BaseService<Garbage> {

    /**
     * 获取全部垃圾信息
     *
     * @param pageInfo 分页
     * @param map      条件
     * @return List<Garbage>
     */
    Result<ResObj<Garbage>> findAllGarbage(PageInfo<Garbage> pageInfo, Map<String, Object> map);

    /**
     * 获取全部垃圾分类信息
     *
     * @return List<Garbage>
     */
    Result<List<Garbage>> findAllListGarbage();

}

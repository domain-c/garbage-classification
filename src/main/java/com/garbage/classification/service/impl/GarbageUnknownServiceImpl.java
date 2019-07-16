package com.garbage.classification.service.impl;

import com.garbage.classification.common.CommonCode;
import com.garbage.classification.common.ResObj;
import com.garbage.classification.common.Result;
import com.garbage.classification.dao.GarbageUnknownDao;
import com.garbage.classification.entity.GarbageUnknown;
import com.garbage.classification.service.GarbageUnknownService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author domain
 * @date 2019-07-12
 */
@Slf4j
@Service
public class GarbageUnknownServiceImpl extends BaseServiceImpl<GarbageUnknown> implements GarbageUnknownService {

    @Autowired
    GarbageUnknownDao unknownDao;

    @Override
    public Result<ResObj<GarbageUnknown>> findAllUnGarbage(PageInfo<GarbageUnknown> pageInfo, Map<String, Object> map) {
        Result result = null;
        try {
            Page<GarbageUnknown> page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<GarbageUnknown> list = unknownDao.selectAllUnGarbage(map);
            ResObj<GarbageUnknown> resObj = new ResObj<>(list, page.getTotal(), pageInfo.getPageNum());
            result = Result.setSucceed(resObj);
        } catch (Exception e) {
            log.error(CommonCode.IN_SYSTEM_ERROR, e);
            result = Result.setFailMsg(CommonCode.IN_SYSTEM_ERROR);
        }
        return result;
    }
}

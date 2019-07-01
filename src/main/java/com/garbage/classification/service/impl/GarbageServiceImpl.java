package com.garbage.classification.service.impl;

import com.garbage.classification.common.CommonCode;
import com.garbage.classification.common.ResObj;
import com.garbage.classification.common.Result;
import com.garbage.classification.dao.GarbageDao;
import com.garbage.classification.entity.Garbage;
import com.garbage.classification.service.GarbageService;
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
 * @date 2019-07-01
 */
@Slf4j
@Service
public class GarbageServiceImpl extends BaseServiceImpl<Garbage> implements GarbageService {

    @Autowired
    GarbageDao garbageDao;

    @Override
    public Result<ResObj<Garbage>> findAllGarbage(PageInfo<Garbage> pageInfo, Map<String, Object> map) {
        Result result = null;
        try {
            Page<Garbage> page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
            List<Garbage> list = garbageDao.selectAllGarbage(map);
            ResObj<Garbage> resObj = new ResObj<>(list, page.getTotal(), pageInfo.getPageNum());
            result = Result.setSucceed(resObj);
        } catch (Exception e) {
            log.error(CommonCode.IN_SYSTEM_ERROR, e);
            result = Result.setFailMsg(CommonCode.IN_SYSTEM_ERROR);
        }
        return result;
    }
}

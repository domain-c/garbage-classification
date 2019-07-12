package com.garbage.classification.controller;

import com.garbage.classification.common.CommonCode;
import com.garbage.classification.common.ResponseResult;
import com.garbage.classification.common.Result;
import com.garbage.classification.entity.Garbage;
import com.garbage.classification.entity.GarbageUnknown;
import com.garbage.classification.service.GarbageService;
import com.garbage.classification.service.GarbageUnknownService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author domain
 * @date 2019-07-03
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    GarbageService garbageService;

    @Autowired
    GarbageUnknownService garbageUnknownService;

    @PostMapping("/getAllGarbage")
    public ResponseResult getAllGarbage() {
        ResponseResult response = null;
        Result<List<Garbage>> result = garbageService.findAllListGarbage();
        if (result.getRes() == Result.SUCCEED_CODE) {
            List<Garbage> list = (List<Garbage>) result.getObj();
//            1 可回收 2 有害 3 湿垃圾 4 干垃圾
            int[] type = {1, 2, 3, 4};
            String[] name = {"可回收垃圾", "有害垃圾", "湿垃圾", "干垃圾"};
            String[] desc = {"是指适宜回收和可循环再利用的废弃物。主要包括废玻璃、废金属、废塑有害垃圾料、废纸张、废织物等",
                    "是指对人体健康或者自然环境造成直接或者潜在危害的零星废弃物，单位有害垃圾集中产生的除外。主要包括废电池、废灯管、废药品、废油漆桶等",
                    "是指易腐的生物质废弃物。主要包括剩菜剩饭、瓜皮果核、花卉绿植、肉类有害垃圾碎骨、过期食品、餐厨垃圾等", ""};
            String[] howThrow = {"●轻投轻放\\n●清洁干燥，避免污染\\n●废纸尽量平整\\n●立体包装请清空内容物，清洁后压扁投放\\n●有尖锐边角的，应包裹后投放",
                    "●充电电池、纽扣电池、蓄电池投放时请注意轻放\\n●油漆桶、杀虫剂如有残留请密闭后投放\\n●荧光灯、节能灯易破损请连带包装或包裹后轻放\\n●废药品及其包装连带包装一 并投放", "", ""};
            List<Map<String, Object>> resultList = new ArrayList<>();
            for (int i = 0; i < type.length; i++) {
                Map<String, Object> objectMap = new HashMap<>(5);
                int index = type[i];
                List<Garbage> val = list.stream().filter(garbage -> garbage.getBelongClassification() == index).collect(Collectors.toList());
                objectMap.put("type", index);
                objectMap.put("name", name[i]);
                objectMap.put("desc", desc[i]);
                objectMap.put("howThrow", howThrow[i]);
                objectMap.put("list", val);
                resultList.add(objectMap);
            }
            response = ResponseResult.setSuccess(resultList, "成功");
        } else {
            response = ResponseResult.setError(result.getResMsg());
        }
        return response;
    }

    @PostMapping("/queryGarbageName")
    public ResponseResult queryGarbageName(@RequestParam("garbageName") String garbageName) {
        ResponseResult response = null;
        try {
            if (!StringUtils.isEmpty(garbageName)) {
                Result<List<Garbage>> result = garbageService.findLikeGarbageName(garbageName);
                List<Garbage> list = (List<Garbage>) result.getObj();
                if (list.size() == 0) {
                    // 触发没有记录保存
                    GarbageUnknown gn = new GarbageUnknown(garbageName);
                    garbageUnknownService.insert(gn);
                }
                response = ResponseResult.setSuccess(list, "成功");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            response = ResponseResult.setError(CommonCode.IN_SYSTEM_ERROR);
        }
        return response;
    }

}

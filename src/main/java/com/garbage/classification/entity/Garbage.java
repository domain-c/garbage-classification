package com.garbage.classification.entity;

import com.garbage.classification.utils.DateUtils;
import lombok.Data;

/**
 * @author chenguotu
 */
@Data
public class Garbage {
    private Long id;

    private Long belongClassification;

    private String garbageName;

    private String detail;

    private String createTime;

    public Garbage() {
    }

    public Garbage(Long belongClassification, String garbageName) {
        this.belongClassification = belongClassification;
        this.garbageName = garbageName;
        this.detail = "";
        this.createTime = DateUtils.currentTime(DateUtils.TIME_FORMAT_1);
    }
}
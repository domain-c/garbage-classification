package com.garbage.classification.entity;

import com.garbage.classification.utils.DateUtils;
import lombok.Data;

/**
 * @author chenguotu
 */
@Data
public class GarbageUnknown {
    private Long id;

    private String title;

    private Integer state;

    private String createTime;

    public GarbageUnknown() {
    }

    public GarbageUnknown(String title) {
        this.title = title;
        this.state = 0;
        this.createTime = DateUtils.currentTime(DateUtils.TIME_FORMAT_1);
    }
}
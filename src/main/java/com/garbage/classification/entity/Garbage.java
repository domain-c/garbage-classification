package com.garbage.classification.entity;

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
}
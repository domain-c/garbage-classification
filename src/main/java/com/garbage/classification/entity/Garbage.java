package com.garbage.classification.entity;


import lombok.Data;

/**
 * @author domain
 */
@Data
public class Garbage {
    private Long id;

    private String garbageName;

    private String detail;

    private String createTime;

}
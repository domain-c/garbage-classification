package com.garbage.classification.entity;

import lombok.Data;

/**
 * @author domain
 */
@Data
public class Employee {
    private Integer id;

    private Integer type;

    private String password;

    private String username;

    private String createTime;

    private String updateTime;

    private String lastLoginTime;

    private Integer dr;
}
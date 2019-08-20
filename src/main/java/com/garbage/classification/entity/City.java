package com.garbage.classification.entity;


import lombok.Data;

/**
 * @author domain
 */
@Data
public class City {
    private Long id;

    private String adcode;

    private String country;

    private String province;

    private String city;

    private Integer state;


}
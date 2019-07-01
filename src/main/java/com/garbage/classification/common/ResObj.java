package com.garbage.classification.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author doamin
 * @param <T>
 */
@Data
public class ResObj<T> implements Serializable {

    /**
     * 结果集
     */
    private List<T> list;
    /**
     * 总数量
     */
    private long total;
    /**
     * 第几页
     */
    private int pages;

    public ResObj(){}

    public ResObj(List<T> list, long total, int pages) {
        this.list = list;
        this.total = total;
        this.pages = pages;
    }
}
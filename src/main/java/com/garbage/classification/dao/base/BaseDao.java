package com.garbage.classification.dao.base;

/**
 * @author: domain
 * @date: 2018/9/5
 */
public interface BaseDao<T> {

    /**
     * 保存一个对象
     * @param obj 对象
     * @return 对象ID
     */
    public int insert(T obj);

    /**
     * 主键删除一个对象
     * @param id 对象ID
     * @return
     */
    public int deleteByPrimaryKey(Long id);

    /**
     * 保存对象
     * @param obj 对象
     * @return 对象ID
     */
    int insertSelective(T obj);

    /**
     * 主键查询得到对象
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 更新操作
     * @param obj
     * @return
     */
    int updateByPrimaryKeySelective(T obj);

    /**
     * 更新操作
     * @param obj
     * @return
     */
    int updateByPrimaryKey(T obj);

}

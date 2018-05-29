package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.CoefficientPO;

/**
 * Created by ${WX} on 2017/6/7.
 */
public interface CoefficientDao {
    /**
     * 新增一份系数实例
     * @param po 要新增的实例
     */
    public void add(CoefficientPO po);

    /**
     * 修改一个系数实例
     *
     * @param po 要修改的系数实例
     */
    public void update(CoefficientPO po);

    /**
     * 获得一个系数实例
     *
     * @param key 实例的key
     * @return 实例的value
     */
    public double getByName(String key);
}

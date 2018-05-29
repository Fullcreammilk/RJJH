package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.LittleAwardsPO;

import java.util.List;

/**
 * Created by ${WX} on 2017/6/6.
 */
public interface LittleAwardsDao {
    /**
     * 新增一个获奖实例
     * @param po 要新增的实例
     */
    public void add(LittleAwardsPO po);

    /**
     * 获得一个maker 的获奖实例
     *
     * @param name makername
     * @param type makertype
     * @return 获奖的实例列表
     */

    public List<LittleAwardsPO> getByName(String name,String type);

    /**
     * 删除一个获奖实例
     * @param po 要删除的实例
     */

    public void delete(LittleAwardsPO po);
}

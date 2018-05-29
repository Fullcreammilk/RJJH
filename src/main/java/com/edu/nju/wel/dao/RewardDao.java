package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.RewardPO;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/23.
 */
public interface RewardDao {
    /**
     * 新增一个获奖实例
     * @param po 要新增的实例po
     */
    public void add(RewardPO po);

    /**
     * 获得一个人的获奖信息
     * @param name 人名
     * @return 获奖信息polist
     */
    public List<RewardPO> getByPeopleName(String name);


    /**
     * 获得一部电影的获奖信息
     * @param name 电影名
     * @return 获奖信息polist
     */
    public List<RewardPO> getByMovieName(String name);

    /**
     * 获得所有大奖实例
     * @return 返回大奖实例列表
     */
    public List<RewardPO> getAll();

    /**
     * 修改一个大奖实例
     * @param po 要修改的大奖实例
     */
    public void update(RewardPO po);
}

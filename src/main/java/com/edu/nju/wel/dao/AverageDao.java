package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.AveragePO;

import java.util.List;

/**
 * Created by ${WX} on 2017/6/4.
 */
public interface AverageDao {
    /**
     * 新增一个平均分实例
     * @param po 要新增的实例
     */
    public void add(AveragePO po);

    /**
     * 获得一个maker的平均分信息
     *
     * @param name makername
     * @param type makertype
     * @return 返货该maker的平均分实例
     */
    public AveragePO getByName(String name,String type);

    /**
     * 修改一个maker实例
     *
     * @param po 要更新的实例
     */
    public void update(AveragePO po);


    /**
     * 获得某类maker的均分信息
     *
     * @param type 需要的type
     * @return maker的实例列表
     */
    public List<AveragePO> getAll(String type);
}

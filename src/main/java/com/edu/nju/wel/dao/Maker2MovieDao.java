package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.Creator2Movie;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/10.
 */
public interface Maker2MovieDao {
    /**
     *
     * @param relation 新的电影和maker映射关系
     */
    public void add(Creator2Movie relation);

    /**
     * 删除一个映射关系
     * @param relation 要删除的映射
     */
    public void delete(Creator2Movie relation);

    /**
     * 得到所有映射关系
     * @return 映射实例列表
     */
    public List<Creator2Movie> getAll();
}

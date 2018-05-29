package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.MovieData;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/21.
 */
public interface MovieDataDao {
    /**
     * 新增一个moviedata实例
     * @param data 要新增的实例po
     */
    public void add(MovieData data);

    /**
     * 用于获得所有的moviedata实例
     * @return返回所有moviedata实例的list
     */
    public List<MovieData> getAll();

    /**
     * 更新某个实例
     * @param data 要更新的实例
     */
    public void update(MovieData data);

    /**
     * 删除一个moviedata实例
     * @param data
     */
    public void delete(MovieData data);
}

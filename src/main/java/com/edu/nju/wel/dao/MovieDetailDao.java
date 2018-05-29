package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.MovieDetailPO;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ${WX} on 2017/5/9.
 */
public interface MovieDetailDao {
    /**
     * 用于获得所有电影详细信息的实例
     * @return 所有实例的polist
     */
    public List<MovieDetailPO> getAll();


    /**
     * 根据名字获得电影信息实例
     * @param movieName 电影的名字
     * @return 返回符合条件的实例po，若无则返回null
     */
    public MovieDetailPO getByName(String movieName);

    /**
     * 根据名字获得该电影的所有creator名字
     * @param movieName 电影的名字
     * @return 包含所有creator的迭代器
     */
    public Iterator<String> getCreators(String movieName);


    /**
     * 根据名字获得该电影的所有star名字
     * @param movieName 电影的名字
     * @return 包含所有star的迭代器
     */
    public Iterator<String> getStars(String movieName);


    /**
     * 新增一个电影详细信息实例
     * @param po 要新增的实例po
     */
    public void add(MovieDetailPO po);
}

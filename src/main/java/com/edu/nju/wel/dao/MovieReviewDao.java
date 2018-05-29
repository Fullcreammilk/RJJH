package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.MovieReviewPO;

import java.util.List;

/**
 * Created by ${WX} on 2017/4/15.
 */
public interface MovieReviewDao {
    /**
     * 获得一部电影的评论实例
     * @param movieName 电影名
     * @return 该电影名的评论实例list
     */
    public List<MovieReviewPO> find(String movieName);

    /**
     * 新增一个评论实例
     * @param movie 要新增的电影评论实例poo
     */
    public void add(MovieReviewPO movie);

    /**
     * 获得某部电影的评论数
     * @param movieName 电影名
     * @return 评论数
     */
    public int getPersonalReviewNum(String movieName);
}

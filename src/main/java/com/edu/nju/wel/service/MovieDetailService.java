package com.edu.nju.wel.service;


import com.edu.nju.wel.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 电影信息相关模块，负责获取所有电影信息并进行搜索
 * Created by qianzhihao on 2017/5/10.
 */
public interface MovieDetailService {
    /**
     * 获得电影信息列表
     * @param keywords 关键字
     * @param tag 标签
     * @param sortWay 排序方式
     * @return
     */
    public ArrayList<ListVO> getMovies(String keywords, String tag, String sortWay);

    /**
     * 获得对应电影的信息
     * @param movieName 电影名
     * @return 电影详细信息
     */
    public MovieDetailVO getMovie(String movieName);

    /**
     * 获取对应电影的所有明星名字
     * @param movieName 电影名
     * @return 参演明星名字的迭代器
     */
    public Iterator<String> getStarsName(String movieName);

    /**
     * 获得所有导演
     * @param movieName
     * @return
     */
    public Iterator<String> getDirectorsName(String movieName);

    /**
     * 获得相关推荐的电影
     * @param movieVO 电影的vo对象
     * @return
     */
    public ArrayList<RecMovieVO> recMovie(MovieDetailVO movieVO);

    /**
     * 获得个人的评论
     * @param movieName 电影名
     * @return 普通观众的评论
     */
    public List<MovieReviewPO> getPersonReview(String movieName);

    /**
     * 将同地区的票房信息的数轴图数据计算出来
     * @param movieName 电影名
     * @return 同地区票房数轴的数据
     */
    public ArrayList<AxisParamVO> getAxisParam(String movieName);

    /**
     * 对应电影的所有奖项
     * @param movieName 电影名
     * @return
     */
    public List<RewardPO> getAward(String movieName);
}

package com.edu.nju.wel.service;

import com.edu.nju.wel.model.BoxPlotParaVO;
import com.edu.nju.wel.model.ChartParaVO;
import com.edu.nju.wel.model.GrossListVO;
import com.edu.nju.wel.model.ListVO;

import java.util.ArrayList;

/**
 * 统计信息
 * Created by qianzhihao on 2017/5/16.
 */
public interface StatisticService {
    /**
     * 返回每种标签的出现次数
     * @return
     */
    public ArrayList<ChartParaVO> getTagNum();

    /**
     * 获得每种标签的所有得分情况
     * @return
     */
    public ArrayList<BoxPlotParaVO> getTagRatingValues();

    /**
     * 获得所有的标签
     * @return
     */
    public ArrayList<String> getAllGenres();

    /**
     * 评分与票房的关系
     * @return
     */
    public ArrayList<ChartParaVO> ratingAndGross();

    /**
     * 评论数与票房的关系
     * @return
     */
    public ArrayList<ChartParaVO> reviewAndGross();

    /**
     * 评分最高的10个演员
     * @return
     */
    public ArrayList<ListVO> topTenRatingStar();

    /**
     * 评分最高的10个导演
     * @return
     */
    public ArrayList<ListVO> topTenRatingCreator();

    /**
     * 得分最高的10部
     * @return
     */
    public ArrayList<ListVO> topTenRatingMovie();
}

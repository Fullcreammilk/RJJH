package com.edu.nju.wel.service;

import com.edu.nju.wel.model.ForecastVO;

import java.util.ArrayList;

/**
 * 预测相关逻辑
 * Created by qianzhihao on 2017/6/1.
 */
public interface ForecastService {
    /**
     * 获得预测的结果
     * @param genres 流派数组
     * @param stars 明星数组
     * @param creators 导演数组
     * @return 根据三个数组和一些加成项获得的预测结果
     */
    public double forecastRatingValue(String[] genres,String[] stars,String[] creators);

    /**
     * 此次预测的加成项
     * @return
     */
    public ArrayList<ForecastVO> getExtraEffect();
}

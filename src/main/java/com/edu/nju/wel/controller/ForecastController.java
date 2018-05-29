package com.edu.nju.wel.controller;

import com.edu.nju.wel.service.ForecastService;
import com.edu.nju.wel.service.MakerDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by qianzhihao on 2017/5/21.
 */
@Controller
@RequestMapping("/forecast")
public class ForecastController {
    @Resource(name = "ForecastService")
    ForecastService forecastService;

    @Resource(name = "MakerDetailService")
    MakerDetailService makerDetailService;

    /**
     * 人员列表
     * @param keyword 关键词
     * @param nationality 国籍
     * @param type 类型
     * @return
     */
    @RequestMapping(value="/maker",method = RequestMethod.GET)
    public ModelAndView makerList(String keyword, String nationality, String type,String page){
        return makerDetailService.makerSearch("movieForecast",keyword,nationality,type,page);
    }

    /**
     * 预测结果
     * @param genres 流派
     * @param stars 演员
     * @param creators 导演
     * @return
     */
    @RequestMapping(value="/result",method = RequestMethod.GET)
    public ModelAndView result(String[] genres,String[] stars,String[] creators){
        //TODO
        ModelAndView result = new ModelAndView("forecastResult");

        result.addObject("result",forecastService.forecastRatingValue(genres,stars,creators));
        //一些额外的加分项
        result.addObject("extraEffect",forecastService.getExtraEffect());

        return result;
    }
}

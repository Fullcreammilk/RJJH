package com.edu.nju.wel.controller;

import com.edu.nju.wel.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by qianzhihao on 2017/5/17.
 */
@Controller
@RequestMapping("/chart")
public class ChartController {
    @Resource(name = "StatisticService")
    StatisticService statisticService;

    /**
     * 统计图表
     * @return
     */
    @RequestMapping("/all")
    public ModelAndView chart(){
        ModelAndView chart = new ModelAndView("chart");
        chart.addObject("boxPlot",statisticService.getTagRatingValues());
        chart.addObject("tag_num",statisticService.getTagNum());
        //评分和票房的关系
        chart.addObject("ratingAndGross",statisticService.ratingAndGross());
        return chart;
    }
}

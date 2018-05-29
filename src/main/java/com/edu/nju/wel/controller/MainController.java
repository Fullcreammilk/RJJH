package com.edu.nju.wel.controller;

import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.MovieDetailService;
import com.edu.nju.wel.service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * 主控制器，返回可以直接访问的页面
 * Created by qianzhihao on 2017/5/6.
 */
@Controller
public class MainController {
    @Resource(name = "MovieDetailService")
    MovieDetailService movieDetailService;

    @Resource(name = "MakerDetailService")
    MakerDetailService makerDetailService;

    @Resource(name = "StatisticService")
    StatisticService statisticService;

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping("/reg")
    public String reg(){
        return "register";
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping("/home")
    public ModelAndView homePage(){
        ModelAndView home = new ModelAndView("movieHomepage");
        ArrayList<ListVO> movieList = statisticService.topTenRatingMovie();
        home.addObject("recMov",movieList);
        ArrayList<ListVO> starList = statisticService.topTenRatingStar();
        home.addObject("recStar",starList);
        ArrayList<ListVO> creList = statisticService.topTenRatingCreator();
        home.addObject("recCreator",creList);
        return home;
    }
}

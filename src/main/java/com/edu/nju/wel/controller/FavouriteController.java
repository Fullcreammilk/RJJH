package com.edu.nju.wel.controller;

import com.alibaba.fastjson.JSONObject;
import com.edu.nju.wel.service.FavouriteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by qianzhihao on 2017/6/1.
 */
@Controller
@RequestMapping("/favourite")
public class FavouriteController {
    @Resource(name = "FavouriteService")
    FavouriteService favouriteService;

    @RequestMapping("/all")
    public ModelAndView favourite(HttpSession session){
        ModelAndView favourite = new ModelAndView("favourite");
        favourite.addObject("movie",favouriteService.getFavourites(session.getAttribute("username").toString(),"movie"));
        favourite.addObject("star",favouriteService.getFavourites(session.getAttribute("username").toString(),"star"));
        favourite.addObject("creator",favouriteService.getFavourites(session.getAttribute("username").toString(),"creator"));
        return favourite;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(String userName, String name, String type){
        favouriteService.addFavourite(userName, name,type);

        JSONObject json = new JSONObject();
        json.put("msg","Add to your favorites!");
        return json.toJSONString();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String userName, String name, String type){

        System.out.println(userName+name+type);
        favouriteService.deleteFavourite(userName, name,type);

        JSONObject json = new JSONObject();
        json.put("msg","Delete from your favorites!");
        return json.toJSONString();
    }
}

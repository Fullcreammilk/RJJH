package com.edu.nju.wel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edu.nju.wel.model.ChartParaVO;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.model.MakerDetailVO;
import com.edu.nju.wel.model.PartnerVO;
import com.edu.nju.wel.service.FavouriteService;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.MovieDetailService;
import com.edu.nju.wel.util.ToBigImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qianzhihao on 2017/5/11.
 */
@Controller
@RequestMapping("/maker")
public class MakerDetailController {
    @Resource(name="MakerDetailService")
    MakerDetailService makerDetailService;

    @Resource(name="MovieDetailService")
    MovieDetailService movieDetailService;

    @Resource(name="FavouriteService")
    FavouriteService favouriteService;

    /**
     * 人员列表
     * @param keyword 关键词
     * @param nationality 国籍
     * @param type 类型
     * @return
     */
    @RequestMapping(value="/maker",method = RequestMethod.GET)
    public ModelAndView makerList(String keyword, String nationality, String type,String page){
        return makerDetailService.makerSearch("makerSearch",keyword,nationality,type,page);
    }

    /**
     * 人员详细信息页面
     * @param makerName 人员名字
     * @param type 类型（演员或导演）
     * @return
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ModelAndView makerDetail(String makerName, String type, HttpSession session){
        ModelAndView makerDetail = new ModelAndView("makerDetail");
        MakerDetailVO vo = makerDetailService.getMakerDetail(makerName);
        Iterator<String> movieNameIterator = makerDetailService.getMovies(vo.getName());
        while ((movieNameIterator.hasNext())){
            String name = movieNameIterator.next();
            vo.getMovies().add(new ListVO(movieDetailService.getMovie(name)));
        }
        makerDetail.addObject("detail",vo);
        makerDetail.addObject("bigUrl", ToBigImage.toBigImage(vo.getImageUrl()));
        //演员的票房信息
        //v1为票房总额，v2为参演电影数目
        makerDetail.addObject("gross",makerDetailService.getGrossPerYear(makerName));
        makerDetail.addObject("reward",makerDetailService.getReward(makerName));
        //是否在收藏夹中,若未登陆，返回false
        makerDetail.addObject("isInFavorite",session.getAttribute("username")==null?false:favouriteService.isInFavourties((String)session.getAttribute("username"),makerName,type));
        //判断是否能画雷达图
        boolean canDraw;
        Map<String,Double> topSix = makerDetailService.getTopGenre(makerName);
        if(topSix == null){
            canDraw = false;
        }else {
            canDraw = true;
        }
        //获取雷达图所需数据
        //记录该明星各个genre中的平均分
        ArrayList<ChartParaVO> genreAndAvg = new ArrayList<>();
        if(canDraw) {
            for (Map.Entry<String, Double> entry : topSix.entrySet()) {
                genreAndAvg.add(new ChartParaVO(entry.getKey(), String.valueOf(entry.getValue())));
            }
        }
        makerDetail.addObject("canDraw",canDraw);
        makerDetail.addObject("genreAndAvg",genreAndAvg);

        DecimalFormat df = new DecimalFormat("#.00");
        ArrayList<ChartParaVO> radarData = new ArrayList<>();
        for(Map.Entry<String,Double> entry:makerDetailService.getMakerRadarMap(makerName,type).entrySet()){
            radarData.add(new ChartParaVO(entry.getKey(),df.format(entry.getValue())));
        }
        makerDetail.addObject("radarData",radarData);

        //各年的提名和获奖数
        makerDetail.addObject("awardAndNom",makerDetailService.getAwardAndNom(makerName,type));

        return makerDetail;
    }

    /**
     * 返回一个演员或导演的拍档信息
     * @param name 名字
     * @param type 类型
     * @return
     */
    @RequestMapping("/partner")
    @ResponseBody
    public String partner(String name,String type){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(PartnerVO vo:makerDetailService.getPartners(name,type)){
            jsonObject = new JSONObject();
            jsonObject.put("title",vo.getTitle());
            jsonObject.put("name",vo.getName());
            jsonObject.put("imgUrl",vo.getImgUrl());
            jsonObject.put("movieImgUrl",vo.getMovieImgUrl());
            jsonObject.put("percent",vo.getPercent());
            jsonObject.put("presentativeMovie",vo.getPresentativeMovie());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }


}

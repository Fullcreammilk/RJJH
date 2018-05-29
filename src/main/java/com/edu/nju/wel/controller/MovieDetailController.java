package com.edu.nju.wel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.model.MovieDetailVO;
import com.edu.nju.wel.model.MovieReviewPO;
import com.edu.nju.wel.model.RecMovieVO;
import com.edu.nju.wel.service.FavouriteService;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.MovieDetailService;
import com.edu.nju.wel.util.ItemOfPageNum;
import com.edu.nju.wel.util.ToBigImage;
import com.edu.nju.wel.util.dataCollector.Coefficient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by qianzhihao on 2017/5/10.
 */
@RequestMapping("/movie")
@Controller
public class MovieDetailController {
    @Resource(name="MovieDetailService")
    MovieDetailService movieDetailService;

    @Resource(name = "MakerDetailService")
    MakerDetailService makerDetailService;

    @Resource(name = "FavouriteService")
    FavouriteService favouriteService;

    /**
     * 电影详情
     * @param movieName 电影名
     * @return 电影详情页面
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public ModelAndView movieDetail(String movieName, String page, HttpSession session){
        ModelAndView movie = new ModelAndView("movieDetail");
        //TODO
        MovieDetailVO vo = movieDetailService.getMovie(movieName==null?"12 Angry Men":movieName);
        Iterator<String> temp = movieDetailService.getStarsName(vo.getName());
        while (temp.hasNext()){
            String name = temp.next();
            vo.getStars().add(new ListVO(makerDetailService.getMakerDetail(name)));
        }
        temp = movieDetailService.getDirectorsName(vo.getName());
        while (temp.hasNext()){
            String name = temp.next();
            vo.getCreators().add(new ListVO(makerDetailService.getMakerDetail(name)));
        }
        movie.addObject("movie",vo);
        movie.addObject("bigImage", ToBigImage.toBigImage(vo.getImageUrl()));

        List<MovieReviewPO> reviews = movieDetailService.getPersonReview(movieName);
        int index = page==null?1:Integer.valueOf(page);
        movie.addObject("numOfPages",reviews.size()%ItemOfPageNum.REVIEW ==0?reviews.size()/ItemOfPageNum.REVIEW :reviews.size()/ItemOfPageNum.REVIEW +1);
        movie.addObject("personReviews", ItemOfPageNum.getPageItem(index,ItemOfPageNum.REVIEW,reviews));
        movie.addObject("nowPage",index);

        //是否在收藏夹中,若未登陆，返回false
        movie.addObject("isInFavorite",session.getAttribute("username")==null?false:favouriteService.isInFavourties((String)session.getAttribute("username"),movieName,"movie"));

        if(session.getAttribute("username")!=null)
            movie.addObject("favourites",favouriteService.getFavourites(session.getAttribute("username").toString(),"movie"));

        //数轴图
        movie.addObject("aixsCharts",movieDetailService.getAxisParam(movieName));

        //获奖情况
        movie.addObject("reward",movieDetailService.getAward(movieName));
        return movie;
    }

    /**
     * 一部电影的相关推荐电影
     * @param movieName 电影名
     * @return
     */
    @RequestMapping("/recMovies")
    @ResponseBody
    public String recMovies(String movieName){
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        for(RecMovieVO vo:movieDetailService.recMovie(movieDetailService.getMovie(movieName))){
            jsonObject = new JSONObject();
            jsonObject.put("name",vo.getName());
            jsonObject.put("imgUrl",vo.getImgUrl());
            //各个方面匹配情况的参数，每次点击传回后台，加入数据库，方便计算权重
            jsonObject.put("sameArray",vo.getSameNum());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * 每次点击，更新推荐电影各个方面权重的数组
     */
    @RequestMapping(value = "/updateSameArray",method = RequestMethod.GET)
    public void updateSameArray(@RequestParam(value = "sameArray[]") String[] sameArray){
        for(int i=0;i<sameArray.length;i++){
            //若符合某一条件，权重加1
            if(Integer.valueOf(sameArray[i])!=0)
                Coefficient.addSameCoefficient(i);
        }
    }
}

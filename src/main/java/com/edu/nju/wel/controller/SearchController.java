package com.edu.nju.wel.controller;

import com.alibaba.fastjson.JSONObject;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.service.KeywordHelpService;
import com.edu.nju.wel.service.MovieDetailService;
import com.edu.nju.wel.service.StatisticService;
import com.edu.nju.wel.util.ItemOfPageNum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianzhihao on 2017/5/16.
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    @Resource(name = "StatisticService")
    StatisticService statisticService;

    @Resource(name = "MovieDetailService")
    MovieDetailService movieDetailService;

    @Resource(name = "KeywordHelpService")
    KeywordHelpService keywordHelpService;


    /**
     * 电影搜索界面
     *
     * @param keywords 关键词
     * @param tag      标签
     * @param sortWay  排序方式
     * @return 电影页面
     */
    @RequestMapping("/movSch")
    public ModelAndView movieSch(String keywords, String tag, String sortWay, String page) {
        ModelAndView movies = new ModelAndView("movieSearch");
        ArrayList<ListVO> movieList = movieDetailService.getMovies(
                //判断是否有关键词要求，若无，则传入空字符串
                keywords == null ? "" : keywords,
                //若tag为All，则搜索无tag要求，传入null
                (tag == null || tag.equals("All")) ? null : tag,
                //若无sortWay这一参数，默认按得分排序
                sortWay == null ? "Score" : sortWay);

        int index = page == null ? 1 : Integer.valueOf(page);
        List<ListVO> res = ItemOfPageNum.getPageItem(index,ItemOfPageNum.MOVIE_SCH,movieList);
        //统计页码数
        movies.addObject("pageNum", movieList.size() % ItemOfPageNum.MOVIE_SCH == 0 ? movieList.size() / ItemOfPageNum.MOVIE_SCH : movieList.size() / ItemOfPageNum.MOVIE_SCH + 1);
        //当前页码数
        movies.addObject("presentPage", index);
        movies.addObject("movies", res);
        movies.addObject("tags", statisticService.getAllGenres());
        return movies;
    }

    /**
     * 智能搜索推荐
     * @param keyword 关键字
     * @param type 类型，有movie,star,creator
     * @return 符合关键字的推荐
     */
    @RequestMapping("/help")
    @ResponseBody
    public String keywordHelp(String keyword,String type){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",keywordHelpService.keywordHelp(keyword,type));
        return jsonObject.toJSONString();
    }
}

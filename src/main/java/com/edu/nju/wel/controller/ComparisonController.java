package com.edu.nju.wel.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.edu.nju.wel.model.ChartParaVO;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.model.TwoValueChartVO;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qianzhihao on 2017/6/7.
 */
@Controller
@RequestMapping("/comparison")
public class ComparisonController {
    @Resource(name = "MakerDetailService")
    MakerDetailService makerDetailService = new MakerDetailImpl();

    @RequestMapping("/star")
    public ModelAndView comparison(String star1, String star2) {
        ModelAndView comparison = new ModelAndView("starsComparison");
        if (star1 != null && star2 != null) {



        }
        return comparison;
    }

    /**
     * 两个明星对比的雷达图所需数据
     * @param star1
     * @param star2
     * @return
     */
    @RequestMapping("/radar")
    @ResponseBody
    public String radarChart(String star1, String star2) {
        JSONObject res = new JSONObject();

        DecimalFormat df = new DecimalFormat("0.00");
        Map<String, Double> star1Map = makerDetailService.getMakerRadarMap(star1, "star");
        Map<String, Double> star2Map = makerDetailService.getMakerRadarMap(star2, "star");


        //判断是否可以画出雷达图
        res.put("canDrawRadar", (star1Map != null && star2Map != null));

        //演员1
        res.put("star1",addToRaderJsonArray(star1Map,df));
        //演员2
        res.put("star2",addToRaderJsonArray(star2Map,df));

        return res.toJSONString();
    }

    @RequestMapping("/gross")
    @ResponseBody
    public String grossChart(String star1,String star2){
        //票房统计对比
        //两个列表
        ArrayList<TwoValueChartVO> gross1 = makerDetailService.getGrossPerYear(star1);
        ArrayList<TwoValueChartVO> gross2 = makerDetailService.getGrossPerYear(star2);

        //使用游标标记，把只有一个有的年份填上
        int star1Itr = 0;
        int star2Itr = 0;
        while (star1Itr < gross1.size() || star2Itr < gross2.size()) {
            //把游标指向的小的年份加到另一个列表，并把游标前移
            int compare = 0;
            if (star1Itr == gross1.size()) {
                while (star2Itr < gross2.size()) {
                    gross1.add(new TwoValueChartVO(gross2.get(star2Itr).getAttr(), 0, 0));
                    star2Itr++;
                }
                break;
            } else if (star2Itr == gross2.size()) {
                while (star1Itr < gross1.size()) {
                    gross2.add(new TwoValueChartVO(gross1.get(star1Itr).getAttr(), 0, 0));
                    star1Itr++;
                }
                break;
            } else {
                compare = gross1.get(star1Itr).compareTo(gross2.get(star2Itr));
            }
            if (compare < 0) {
                gross2.add(star2Itr, new TwoValueChartVO(gross1.get(star1Itr).getAttr(), 0, 0));
            } else if (compare > 0) {
                gross1.add(star1Itr, new TwoValueChartVO(gross2.get(star2Itr).getAttr(), 0, 0));
            }
            //由于插入了一个数据，所以另一个游标也要加
            //相等则一起前移
            star1Itr++;
            star2Itr++;
        }

        JSONArray jsonArray1 = addToGrossArray(gross1);
        JSONArray jsonArray2 = addToGrossArray(gross2);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("star1",jsonArray1);
        jsonObject.put("star2",jsonArray2);
        return jsonObject.toJSONString();
    }

    /**
     * 返回所有符合条件的明星的ListVO
     *
     * @param keyword 关键字
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public String starlist(String keyword) {
        JSONObject jsonObject = new JSONObject();
        ArrayList<ListVO> starList = makerDetailService.getStarList(keyword, "All");
        String[] names = new String[starList.size()];
        String[] imgUrls = new String[starList.size()];
        for (int i = 0; i < starList.size(); i++) {
            names[i] = starList.get(i).getName();
            imgUrls[i] = starList.get(i).getImgUrl();
        }

        jsonObject.put("names", names);
        jsonObject.put("imgUrls", imgUrls);
        return jsonObject.toJSONString();
    }

    private JSONArray addToRaderJsonArray(Map<String,Double> map,DecimalFormat df){
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("attr",entry.getKey());
            jsonObject.put("value",df.format(entry.getValue()));
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    private JSONArray addToGrossArray(ArrayList<TwoValueChartVO> gross){
        JSONArray jsonArray = new JSONArray();
        for(TwoValueChartVO vo:gross){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("attr",vo.getAttr());
            jsonObject.put("v1",vo.getV1());
            jsonObject.put("v2",vo.getV2());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

//    public static void main(String[] args) {
//        System.out.println(new ComparisonController().radarChart("Leonardo DiCaprio","Pete Postlethwaite"));
//        System.out.println(new ComparisonController().grossChart("Leonardo DiCaprio","Pete Postlethwaite"));
//    }
}

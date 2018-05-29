package com.edu.nju.wel.util;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.model.MovieDetailVO;
import com.edu.nju.wel.model.TagAttr;
import com.edu.nju.wel.model.TwoValueChartVO;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qianzhihao on 2017/6/7.
 */
public class MoviesGrossPerYear {
    public static ArrayList<TwoValueChartVO> getGrossPerYear(List<MovieDetailPO> movies){
        //提取年份的正则表达式
        Pattern p = Pattern.compile("(\\d{4})");
        //保存结果的Map
        HashMap<String,TagAttr> resMap = new HashMap<>();

        String movieName;
        for(MovieDetailPO po:movies){
            String time = po.getReleDate();

            //判断是否有时间和票房属性
            if(time!=null&&po.getGross()!=null) {
                int gross = Money2Int.money2int(po.getGross());
                Matcher m = p.matcher(time);
                m.find();
                if (resMap.get(m.group()) == null) {
                    TagAttr tagAttr = new TagAttr(time);
                    tagAttr.addTag(Double.valueOf(gross));
                    resMap.put(m.group(), tagAttr);
                } else {
                    resMap.get(m.group()).addTag(Double.valueOf(gross));
                }
            }
        }

        ArrayList<TwoValueChartVO> chartParas = new ArrayList<>();
        for(Map.Entry<String,TagAttr> entry:resMap.entrySet()){
            chartParas.add(new TwoValueChartVO(entry.getKey(),entry.getValue().getSum(),entry.getValue().getNum()));
        }
        Collections.sort(chartParas);

        return chartParas;
    }
}

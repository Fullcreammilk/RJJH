package com.edu.nju.wel.util;

import com.edu.nju.wel.model.MovieDetailPO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ${WX} on 2017/6/8.
 */
public class TagAverage {
    public static HashMap<String,AverageCounter > countTagAverage(List<MovieDetailPO> list){
        String[] tags;
        HashMap<String, AverageCounter> map=new HashMap<>();
        for (MovieDetailPO po:list
                ) {
            if (po.getGenres() == null||po.getRatingValue()==0)
                continue;
            tags = po.getGenres().split(" ");
            for (int i = 0; i < tags.length; i++) {
                if (map.containsKey(tags[i])) {
                    map.get(tags[i]).sum += po.getRatingValue();
                    map.get(tags[i]).num++;
                } else {
                    map.put(tags[i], new AverageCounter(po.getRatingValue()));
                }
            }
        }
        return map;
    }
}

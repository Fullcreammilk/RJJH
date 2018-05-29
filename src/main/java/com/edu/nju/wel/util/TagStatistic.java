package com.edu.nju.wel.util;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.dao.MakerDetailDao;
import com.edu.nju.wel.dao.MovieDetailDao;
import com.edu.nju.wel.dao.impl.MakerDetailDaoImpl;
import com.edu.nju.wel.model.MakerDetailPO;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.model.TagAttr;
import com.mchange.v2.collection.MapEntry;

import java.util.HashMap;
import java.util.Map;

/**
 * 关于标签的统计预处理
 * Created by qianzhihao on 2017/5/15.
 */
public class TagStatistic {
    public static HashMap<String, TagAttr> tagMap = new HashMap<>();

    /**
     * 记录所有电影的平均分，作为默认值
     */
    public static double movieAvg;

    static {
        update();
    }

    private static void update() {
        movieAvg = 0;
        for (MovieDetailPO po : Persistence.getAllMovie()) {
            movieAvg += po.getRatingValue();
            //若评分为0则不计入
            if (po.getGenres() != null&&po.getRatingValue()!=0) {
                for (String tag : po.getGenres().split(" ")) {
                    addToMap(tag, po.getRatingValue());
                }
            }
            //若无标签，则归入other
            else {
                addToMap("Other", po.getRatingValue());
            }
        }
        movieAvg /= Persistence.getAllMovie().size();
    }

    private static void addToMap(String tag, double score) {
        //若已经存在对应项目
        if (tagMap.get(tag) != null) {
            tagMap.get(tag).addTag(score);
        } else {
            tagMap.put(tag, new TagAttr(tag));
        }
    }

    public static void main(String[] args) {
        for(Map.Entry<String, TagAttr> entry:tagMap.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue().getAvg());
        }
    }

}

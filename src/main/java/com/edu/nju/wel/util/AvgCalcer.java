package com.edu.nju.wel.util;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.dao.MakerDetailDao;
import com.edu.nju.wel.dao.MovieDetailDao;
import com.edu.nju.wel.model.AveragePO;
import com.edu.nju.wel.model.MakerDetailPO;
import com.edu.nju.wel.model.MovieDetailPO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 平均分计算类
 * Created by Adminstrator on 2017/5/13.
 */
public class AvgCalcer {
    public static HashMap<String, Double> starsAvg = new HashMap<>();
    public static HashMap<String, Double> creatorsAvg = new HashMap<>();

    public static HashMap<String, Integer> starsMovieNum = new HashMap<>();
    public static HashMap<String, Integer> creatorsMovieNum = new HashMap<>();


    public static void calc() {
        double average = 0;
        addToMap("star", starsAvg,starsMovieNum);
        addToMap("creator", creatorsAvg,creatorsMovieNum);
    }

    /**
     * 计算人物的平均分
     * @param type 类型
     * @param valueMap 分数的map
     * @param numMap 统计数目的map
     */
    private static void addToMap(String type, HashMap<String, Double> valueMap,HashMap<String,Integer> numMap) {
        List<MakerDetailPO> makerList = Persistence.getAllMaker(type);
        for (MakerDetailPO maker : makerList) {
            Iterator<String> makerMovies = DAOManager.makerDetailDao.getMovies(maker.getName());
            String movieName=null;
            double sum = 0;
            int count = 0;
            while (makerMovies.hasNext()){
                movieName = makerMovies.next();
                MovieDetailPO movieDetail= DAOManager.movieDetailDao.getByName(movieName);
                double sco= movieDetail.getRatingValue();
                if(sco!=0) {
                    sum += sco;
                    count++;
                }

            }
            if(count!=0) {
                Double avg = sum / count;
                valueMap.put(maker.getName(), avg);
                numMap.put(maker.getName(),count);
            }
        }
    }

    public static void main(String[] args) {
        calc();
        for(Map.Entry<String,Double> entry:starsAvg.entrySet()){
            DAOManager.averageDao.add(new AveragePO(entry.getKey(),"star",entry.getValue(),starsMovieNum.get(entry.getKey())));
        }
        for(Map.Entry<String,Double> entry:creatorsAvg.entrySet()){
            DAOManager.averageDao.add(new AveragePO(entry.getKey(),"creator",entry.getValue(),creatorsMovieNum.get(entry.getKey())));
        }
    }
}


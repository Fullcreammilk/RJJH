package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.StatisticService;
import com.edu.nju.wel.util.Money2Int;
import com.edu.nju.wel.util.Persistence;
import com.edu.nju.wel.util.TagStatistic;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by qianzhihao on 2017/5/16.
 */
@Service("StatisticService")
public class StatisticServiceImpl implements StatisticService{
    public static ArrayList<String> genres;

    /**
     * 上top榜单至少需要的电影数
     */
    private static final int MIN_MOVIE_TO_TOP = 10;

    @Override
    public ArrayList<ChartParaVO> getTagNum() {
        ArrayList<ChartParaVO> chartParas = new ArrayList<>();
        for(Map.Entry entry: TagStatistic.tagMap.entrySet()){
            chartParas.add(new ChartParaVO((String)entry.getKey(),String.valueOf(((TagAttr)entry.getValue()).getNum())));
        }
        return chartParas;
    }

    @Override
    public ArrayList<BoxPlotParaVO> getTagRatingValues() {
        ArrayList<BoxPlotParaVO> boxPlotParas = new ArrayList<>();
        for(Map.Entry entry: TagStatistic.tagMap.entrySet()){
            TagAttr tagAttr = TagStatistic.tagMap.get(entry.getKey());
            double[] scores = new double[tagAttr.getNum()];
            for(int i=0;i<tagAttr.getNum();i++){
                scores[i] = tagAttr.getScore(i);
            }
            boxPlotParas.add(new BoxPlotParaVO((String)entry.getKey(),scores));
        }
        return boxPlotParas;
    }

    @Override
    public ArrayList<String> getAllGenres() {
        return allGenres();
    }

    @Override
    public ArrayList<ChartParaVO> ratingAndGross() {
        ArrayList<ChartParaVO> chartParas = new ArrayList<>();
        for(MovieDetailPO po: Persistence.getAllMovie()){
            if(po.getRatingValue()!=0&&po.getGross()!=null)
                chartParas.add(new ChartParaVO(String.valueOf(po.getRatingValue()), String.valueOf(Money2Int.money2int(po.getGross()))));
        }
        Collections.sort(chartParas);
        return chartParas;
    }

    @Override
    public ArrayList<ChartParaVO> reviewAndGross() {
        ArrayList<ChartParaVO> chartParas = new ArrayList<>();
        for(MovieDetailPO po: Persistence.getAllMovie()){
            int num = DAOManager.movieReviewDao.getPersonalReviewNum(po.getName());
            if(num!=0&&po.getGross()!=null)
                chartParas.add(new ChartParaVO(String.valueOf(num), String.valueOf(Money2Int.money2int(po.getGross()))));
        }
        Collections.sort(chartParas);
        return chartParas;
    }

    @Override
    public ArrayList<ListVO> topTenRatingStar() {
        return getTops("star");
    }

    @Override
    public ArrayList<ListVO> topTenRatingCreator() {
        return getTops("creator");
    }

    @Override
    public ArrayList<ListVO> topTenRatingMovie() {
        ArrayList<ListVO> res = new ArrayList<>();
        Collections.sort(Persistence.getAllMovie());
        for(int i=0;i<10;i++){
            res.add(new ListVO(Persistence.getAllMovie().get(i)));
        }
        return res;
    }

    public static ArrayList<String> allGenres(){
        if(genres == null) {
            genres = new ArrayList<>();
            //添加一个All的标签
            genres.add("All");
            for(Map.Entry<String,TagAttr> entry: TagStatistic.tagMap.entrySet()){
                genres.add(entry.getKey());
            }
        }
        return genres;
    }

    private ArrayList<ListVO> getTops(String type){
        List<AveragePO> avgs = DAOManager.averageDao.getAll(type);
        Collections.sort(avgs);
        ArrayList<ListVO> res = new ArrayList<>();
        int i=-1;
        while (res.size()<10){
            i++;
            if(avgs.get(i).getNum()<MIN_MOVIE_TO_TOP){
                continue;
            }
            ListVO vo = new ListVO(DAOManager.makerDetailDao.getByName(avgs.get(i).getName()));
            DecimalFormat df = new DecimalFormat("0.0");
            vo.setScore(df.format(avgs.get(i).getAve()));
            res.add(vo);
        }
        return res;
    }
}

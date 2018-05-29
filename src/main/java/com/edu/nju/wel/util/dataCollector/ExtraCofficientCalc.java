package com.edu.nju.wel.util.dataCollector;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.service.ForecastService;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.impl.ForecastServiceImpl;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import com.edu.nju.wel.util.Persistence;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qianzhihao on 2017/6/8.
 */
public class ExtraCofficientCalc {
    static MakerDetailService makerDetailService = new MakerDetailImpl();

    static ForecastServiceImpl forecastService = new ForecastServiceImpl();
    /**
     * 统计额外效果对评分的加成所需的数据
     */
    public static void collectExtraCofficientData(){
        DataCollector dataCollector = new DataCollector("src/main/java/com/edu/nju/wel/util/dataCollector/ExtraCofficientData");
        for(MovieDetailPO po:Persistence.getAllMovie()){
            Iterator<String> iterator = DAOManager.movieDetailDao.getStars(po.getName());
            String[] stars = getMakers(iterator);
            iterator = DAOManager.movieDetailDao.getCreators(po.getName());
            String[] creators = getMakers(iterator);
            String[] genres = po.getGenres().split(" ");
            forecastService.forecastRatingValue(genres,stars,creators);

//            double[] datas = forecastService.getExtraCofficientData();
//            datas[4] = po.getRatingValue();
//            dataCollector.writeData(datas);
        }
        dataCollector.closeWriter();
    }

    private static String[] getMakers(Iterator<String> iterator){
        ArrayList<String> makerList = new ArrayList<>();
        while (iterator.hasNext()){
            makerList.add(iterator.next());
        }
        String[] makers = new String[makerList.size()];
        for(int i=0;i<makerList.size();i++){
            makers[i] = makerList.get(i);
        }
        return makers;
    }

    public static void main(String[] args) {
        collectExtraCofficientData();
    }
}

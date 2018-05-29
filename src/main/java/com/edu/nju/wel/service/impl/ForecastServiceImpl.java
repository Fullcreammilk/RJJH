package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.AveragePO;
import com.edu.nju.wel.model.ForecastVO;
import com.edu.nju.wel.model.MakerDetailPO;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.service.ForecastService;
import com.edu.nju.wel.util.TagStatistic;
import com.edu.nju.wel.util.dataCollector.Coefficient;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qianzhihao on 2017/6/1.
 */
@Service("ForecastService")
public class ForecastServiceImpl implements ForecastService{
    //记录每次预测中的特殊效果
    private ArrayList<ForecastVO> forecastVOs;

    //存放适合对应标签的修正系数
    private ArrayList<Double> suitableCoefficient = new ArrayList<>();

//    public static double[] extraCoefficientData = new double[5];

    @Override
    public double forecastRatingValue(String[] genres,String[] stars,String[] creators) {
        double[] datas = new double[3];

        suitableCoefficient.clear();

        forecastVOs = new ArrayList<>();

        //判断是否为空
        if(genres==null||genres.length==0){
            datas[0] = TagStatistic.movieAvg;
        }
        else {
            double sum = 0;
            for (String genre:genres){
                sum += TagStatistic.tagMap.get(genre).getAvg();
            }
            datas[0] = sum/genres.length;
        }

        datas[1] = getMakerAvgAndGetMax(stars,"star",genres);
        datas[2] = getMakerAvgAndGetMax(creators,"creator",genres);

        //计算搭档情况
        String[] bestPartner = new String[2];
        double maxPercent = 0;
        String[] worstPartner = new String[2];
        double minPercent = 1;

        for(String creator:creators){
            //统计导演的电影
            ArrayList<String> creatorMovies = new ArrayList<>();
            Iterator<String> movieIterator = DAOManager.makerDetailDao.getMovies(creator);
            String temp;
            while (movieIterator.hasNext()){
                temp = movieIterator.next();
                creatorMovies.add(temp);
            }

            //遍历演员匹配相同电影
            for(String star:stars) {
                movieIterator = DAOManager.makerDetailDao.getMovies(star);
                double sum = 0;
                int count = 0;
                while (movieIterator.hasNext()){
                    temp = movieIterator.next();
                    for(String creatorMovie:creatorMovies){
                        //若有匹配电影
                        if(temp.equals(creatorMovie)){
                            MovieDetailPO po = DAOManager.movieDetailDao.getByName(temp);
                            //若评分为0直接跳过
                            if(po.getRatingValue()==0){
                                continue;
                            }
                            sum+=po.getRatingValue();
                            count++;
                        }
                    }
                }
                if(count!=0){
                    double res = sum/count;
                    //计算与导演合作和该演员自己的所有作品相比分数的变化情况
                    res/=DAOManager.averageDao.getByName(star,"star").getAve();

                    if(res>maxPercent) {
                        maxPercent = res;
                        bestPartner[0] = creator;
                        bestPartner[1] = star;
                    }
                    if(res<minPercent) {
                        minPercent = res;
                        worstPartner[0] = creator;
                        worstPartner[1] = star;
                    }
                }
            }
        }
        //利用线性回归系数计算出一个基准分
        double baseValue = Coefficient.constant+ Coefficient.genre*datas[0]+ Coefficient.star*datas[1]+ Coefficient.creator*datas[2];

        for(double coefficient:suitableCoefficient){
            baseValue*=(1+coefficient);
        }
//        for(int i = 0; i< extraCoefficientData.length; i++){
//            extraCoefficientData[i] = 0;
//        }
//        extraCoefficientData[0] = baseValue;
//        extraCoefficientData[1] = 1;
//        extraCoefficientData[2] = 1;


        //判断是否有拍档，以及最佳拍档百分比是否大于1，最差拍档百分比是否小于1
        if(maxPercent!=0&&maxPercent>1){
//            extraCoefficientData[1] = maxPercent;
            baseValue*=maxPercent;
            String[] imgUrls = {DAOManager.makerDetailDao.getByName(bestPartner[0]).getImageUrl(),DAOManager.makerDetailDao.getByName(bestPartner[1]).getImageUrl()};
            DecimalFormat df = new DecimalFormat("0.0");
            forecastVOs.add(new ForecastVO(bestPartner,imgUrls,"Best Partner","When "+bestPartner[1]+" cooperates with director "+bestPartner[0]+", his/her rating value improves by "+df.format((maxPercent-1)*100)+"%."));
        }
        if(minPercent!=1&&minPercent<1){
//            extraCoefficientData[2] = minPercent;
            baseValue*=minPercent;
            String[] imgUrls = {DAOManager.makerDetailDao.getByName(worstPartner[0]).getImageUrl(),DAOManager.makerDetailDao.getByName(worstPartner[1]).getImageUrl()};
            DecimalFormat df = new DecimalFormat("0.0");
            forecastVOs.add(new ForecastVO(worstPartner,imgUrls,"Worst Partner","When "+worstPartner[1]+" cooperates with director "+worstPartner[0]+", his/her rating value drops by "+df.format((1-minPercent)*100)+"%."));
        }
//        extraCoefficientData[3] = baseValue;
        //补上一个系数，防止分数过高
        baseValue*=0.9;

        return baseValue;
    }

    @Override
    public ArrayList<ForecastVO> getExtraEffect() {
        return forecastVOs;
    }

//    public double[] getExtraCofficientData(){
//        return extraCoefficientData;
//    }

    private double getMakerAvgAndGetMax(String[] makers,String type,String[] genres){
        double res;
        //判断是否为空
        if(makers==null||makers.length==0){
            res = TagStatistic.movieAvg;
        }
        else {
            //统计在标签中提升最多的百分比的演员及对应标签
            double sum = 0;
            int count = 0;
            double maxPercent = 0;
            String bestMaker = null;
            String bestGenre = null;
            for (String maker:makers){
                if(DAOManager.averageDao.getByName(maker,type)==null){
                    continue;
                }
                double avg = DAOManager.averageDao.getByName(maker,type).getAve();


                //找到该演员的标签中的最高分
                double max = 0;
                //最高分的标签
                String bGenre = null;
                Map<String,Double> genreMap = MakerDetailImpl.getTops(maker);
                for(String genre:genres){
                    Double genreAvg = genreMap.get(genre);
                    if(genreAvg!=null&&genreAvg>max){
                        max = genreAvg;
                        bGenre = genre;
                    }
                }

                //计算提升的百分比
                double percent = (max - avg)/avg;

                if(percent>maxPercent){
                    maxPercent = percent;
                    bestMaker = maker;
                    bestGenre = bGenre;
                }
                sum += avg;
                count++;
            }
            if(count!=0) {
                res = sum / count;
            }
            else {
                res = TagStatistic.movieAvg;
            }
            if(bestMaker!=null) {
                MakerDetailPO po = DAOManager.makerDetailDao.getByName(bestMaker);
                String[] names = {po.getName()};
                String[] imgUrls = {po.getImageUrl()};
                DecimalFormat df = new DecimalFormat("0.0");
                suitableCoefficient.add(maxPercent);
                forecastVOs.add(new ForecastVO(names,imgUrls,"Suitable "+type,"When he/she acts in "+bestGenre+" movies, his/her rating value improves by "+df.format(maxPercent*100)+"%."));
            }
        }
        return res;
    }
}

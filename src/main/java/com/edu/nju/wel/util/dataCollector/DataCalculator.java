package com.edu.nju.wel.util.dataCollector;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.dao.MovieDetailDao;
import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import com.edu.nju.wel.util.AverageCounter;
import com.edu.nju.wel.util.GrossToInt;
import com.edu.nju.wel.util.Persistence;
import com.edu.nju.wel.util.TagStatistic;

import java.util.Iterator;
import java.util.List;

/**
 * 用于计算线性回归所需数据
 * Created by qianzhihao on 2017/5/31.
 */
public class DataCalculator {
    static MakerDetailService makerDetailService = new MakerDetailImpl();

    static MovieDetailDao movieDetailDao = DAOManager.movieDetailDao;

    static DataCollector dataCollector = new DataCollector("src/main/java/com/edu/nju/wel/util/dataCollector/data");

    public static void calculate(){
        double[] res = new double[4];
        List<MovieDetailPO> movies = Persistence.getAllMovie();
        for(MovieDetailPO po:movies){
            //标签
            if(po.getGenres()==null){
                continue;
            }else {
                String[] genres = po.getGenres().split(" ");
                res[0] = 0;
                for(String genre:genres){
                    if(TagStatistic.tagMap.get(genre)==null)
                        continue;
                    res[0] += TagStatistic.tagMap.get(genre).getAvg();
                }
                res[0]/=genres.length;
            }

            //演员
            Iterator<String> makerIterator = movieDetailDao.getStars(po.getName());
            if(!makerIterator.hasNext()){
                continue;
            }
            res[1] = calcMakerAvg(makerIterator);

            //导演
            makerIterator = movieDetailDao.getCreators(po.getName());
            if(!makerIterator.hasNext()){
                continue;
            }
            res[2] = calcMakerAvg(makerIterator);

            //评分
            res[3] = po.getRatingValue();

            //判断是否有0
            if(res[0]!=0&&res[1]!=0&&res[2]!=0&&res[3]!=0)
                dataCollector.writeData(res);
        }
        dataCollector.closeWriter();
    }

    /**
     * 计算导演演员的平均分
     * @return
     */
    private static double calcMakerAvg(Iterator<String> makerIterator){
        double sum = 0;
        int count = 0;
        while (makerIterator.hasNext()){
            String name = makerIterator.next();

            AveragePO avg = DAOManager.averageDao.getByName(name,"star");
            if(avg!=null) {
                sum += avg.getAve();
                count++;
            }
        }
        if(count==0){
            return 0;
        }else {
            return sum/count;
        }
    }

    public static void calcMaxGross(){
        double average;
        AverageCounter counter=new AverageCounter();
        List<MovieDetailPO> list=DAOManager.movieDetailDao.getAll();
        for (MovieDetailPO po:
             list) {
            if (po.getGross()!=null){
                counter.sum+= GrossToInt.toInt(po.getGross());
                counter.num++;
            }
        }
        average=counter.count();
        List<MakerDetailPO> makerDetailPOS=DAOManager.makerDetailDao.getAll("star");
        Iterator<String> iterator;
        double temp,max=0;
        MovieDetailPO movie;
        for(MakerDetailPO po:makerDetailPOS ){
            temp=0;
            iterator=DAOManager.makerDetailDao.getMovies(po.getName());
            while(iterator.hasNext()){
                movie=DAOManager.movieDetailDao.getByName(iterator.next());
                if(movie.getGross()!=null)
                    temp+=GrossToInt.toInt(movie.getGross());
                else
                    temp+=average;
            }
            if (max<temp)
                max=temp;
        }
        makerDetailPOS=DAOManager.makerDetailDao.getAll("creator");
        for(MakerDetailPO po:makerDetailPOS ){
            temp=0;
            iterator=DAOManager.makerDetailDao.getMovies(po.getName());
            while(iterator.hasNext()){
                movie=DAOManager.movieDetailDao.getByName(iterator.next());
                if(movie.getGross()!=null)
                    temp+=GrossToInt.toInt(movie.getGross());
                else
                    temp+=average;
            }
            if (max<temp)
                max=temp;
        }
        CoefficientPO coefficientPO=new CoefficientPO();
        coefficientPO.setName("maxGross");
        coefficientPO.setNum(max);
        DAOManager.coefficientDao.update(coefficientPO);
    }

    public static void calcMaxAwards(){
        double max=0,temp;
        String name=null;
        List<MakerDetailPO> makerDetailPOS1=Persistence.getAllMaker("star");
        List<MakerDetailPO> makerDetailPOS2=Persistence.getAllMaker("creator");
        List<LittleAwardsPO> littleAwardsPOS;
        Iterator<LittleAwardsPO> iterator;
        LittleAwardsPO littleAwardsPO;
        for (MakerDetailPO po:
             makerDetailPOS1) {
            temp=0;
            littleAwardsPOS=DAOManager.littleAwardsDao.getByName(po.getName(),po.getType());
            iterator=littleAwardsPOS.iterator();
            while(iterator.hasNext()){
                littleAwardsPO=iterator.next();
                temp+=(littleAwardsPO.getWon()+littleAwardsPO.getNominated()*0.5);
            }
            if(max<temp) {
                max = temp;
                name=po.getName();
            }
//            if(max==1440){
//                System.out.println(po.getName());
//                return;
//            }
        }
        for (MakerDetailPO po:
                makerDetailPOS2) {
            temp=0;
            littleAwardsPOS=DAOManager.littleAwardsDao.getByName(po.getName(),po.getType());
            iterator=littleAwardsPOS.iterator();
            while(iterator.hasNext()){
                littleAwardsPO=iterator.next();
                temp+=littleAwardsPO.getWon()+littleAwardsPO.getNominated()*0.5;
            }
            if(max<temp) {
                max = temp;
                name=po.getName();
            }
        }
        CoefficientPO coefficientPO=new CoefficientPO();
        coefficientPO.setNum(max);
        coefficientPO.setName("maxAwards");
        DAOManager.coefficientDao.update(coefficientPO);
        System.out.println(name);
    }


    public static void main(String[] args) {

    }
}

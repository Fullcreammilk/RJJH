package com.edu.nju.wel.util.Timer;

import com.edu.nju.wel.util.AvgCalcer;
import com.edu.nju.wel.util.Crawler;
import com.edu.nju.wel.util.Persistence;
import com.edu.nju.wel.util.dataCollector.Coefficient;
import com.edu.nju.wel.util.dataCollector.DataCalculator;
import com.edu.nju.wel.util.dataCollector.DataCollector;
import com.edu.nju.wel.util.dataCollector.LinearRegression;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

/**
 * Created by ${WX} on 2017/6/10.
 */
public class NFDFlightDataTimerTask extends TimerTask {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run() {
        try {
            //在这里写你要执行的内容
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            Crawler.getMostPopular();
//            Crawler.getMovieCompany();
//            Crawler.getLittleAwards("star");
//            Crawler.getLittleAwards("creator");
//            //计算所有演员导演平均分
//            AvgCalcer.calc();
//            //计算线性回归参数
//            DataCalculator.calculate();
//            LinearRegression m = new LinearRegression("src/main/java/com/edu/nju/wel/util/dataCollector/data", 0.001, 10000);
//            m.trainTheta();
//            m.printTheta();
//            //更新持久化的列表
//            Persistence.update();
//            //更新相关性权重
//            Coefficient.addToDB();

            System.out.println("执行当前时间"+formatter.format(Calendar.getInstance().getTime()));
        } catch (Exception e) {
            System.out.println("-------------解析信息发生异常--------------");
        }
    }

}
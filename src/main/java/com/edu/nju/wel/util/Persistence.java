package com.edu.nju.wel.util;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.dao.MakerDetailDao;
import com.edu.nju.wel.dao.MovieDetailDao;
import com.edu.nju.wel.model.MakerDetailPO;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.service.MakerDetailService;

import javax.xml.ws.soap.Addressing;
import java.util.ArrayList;
import java.util.List;

/**
 * 对一些列表持久化引用
 * Created by qianzhihao on 2017/6/1.
 */
public class Persistence {
    private static List<MovieDetailPO> allMovie;

    private static List<MakerDetailPO> allStar;

    private static List<MakerDetailPO> allCreator;

    static {
        update();
    }

    public static List<MovieDetailPO> getAllMovie(){
        return allMovie;
    }

    public static List<MakerDetailPO> getAllMaker(String type){
        if(type.equals("star")){
            return allStar;
        }
        else {
            return allCreator;
        }
    }

    public static void update(){
        allMovie = DAOManager.movieDetailDao.getAll();
        allStar = DAOManager.makerDetailDao.getAll("star");
        allCreator = DAOManager.makerDetailDao.getAll("creator");
    }
}

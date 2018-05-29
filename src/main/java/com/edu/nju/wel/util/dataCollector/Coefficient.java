package com.edu.nju.wel.util.dataCollector;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.CoefficientPO;

/**
 * 记录线性回归系数
 * Created by qianzhihao on 2017/5/31.
 */
public class Coefficient {
    public static double constant = DAOManager.coefficientDao.getByName("constant");

    public static double genre = DAOManager.coefficientDao.getByName("genre");

    public static double star = DAOManager.coefficientDao.getByName("star");

    public static double creator = DAOManager.coefficientDao.getByName("creator");

    //分别对应同导演、明星、流派、关键字
    public static int[] same = {(int)DAOManager.coefficientDao.getByName("sameCreator"),
            (int)DAOManager.coefficientDao.getByName("sameStar"),
            (int)DAOManager.coefficientDao.getByName("sameGenre"),
            (int)DAOManager.coefficientDao.getByName("sameWord")};

    public static void addSameCoefficient(int index){
        same[index]++;
    }

    public static void addToDB(){
        DAOManager.coefficientDao.update(new CoefficientPO("sameCreator",same[0]));
        DAOManager.coefficientDao.update(new CoefficientPO("sameStar",same[1]));
        DAOManager.coefficientDao.update(new CoefficientPO("sameGenre",same[2]));
        DAOManager.coefficientDao.update(new CoefficientPO("sameWord",same[3]));
    }
}

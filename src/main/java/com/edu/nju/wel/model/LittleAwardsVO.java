package com.edu.nju.wel.model;

/**
 * Created by qianzhihao on 2017/6/7.
 */
public class LittleAwardsVO {
    int year;

    int won;

    int nominated;

    public LittleAwardsVO(LittleAwardsPO po) {
        year = po.getYear();
        won = po.getWon();
        nominated = po.getNominated();
    }

    public int getYear() {
        return year;
    }

    public int getWon() {
        return won;
    }

    public int getNominated() {
        return nominated;
    }
}

package com.edu.nju.wel.model;

import java.util.ArrayList;

/**
 * 存放tag的各个属性
 */
public class TagAttr{
    String tag;

    ArrayList<Double> scores;

    public TagAttr(String tag) {
        this.tag = tag;
        scores = new ArrayList<>();
    }

    public double getAvg(){
        return getSum()/getNum();
    }

    public String getTag() {
        return tag;
    }

    public int getNum() {
        return scores.size();
    }

    public void addTag(Double score){
        scores.add(score);
    }

    public Double getScore(int i){
        return scores.get(i);
    }

    public double getSum(){
        double sum = 0;
        for(Double s:scores){
           sum+=Double.valueOf(s);
        }
        return sum;
    }
}
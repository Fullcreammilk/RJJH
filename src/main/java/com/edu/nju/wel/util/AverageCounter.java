package com.edu.nju.wel.util;

/**
 * Created by ${WX} on 2017/5/31.
 */
public class AverageCounter {
    public double sum;

    public int num;

    public AverageCounter(double first){
        this.sum=first;
        this.num=1;
    }

    public AverageCounter(){
        this.num=0;
        this.sum=0;
    }

    public double count(){
        return sum/num;
    }
}

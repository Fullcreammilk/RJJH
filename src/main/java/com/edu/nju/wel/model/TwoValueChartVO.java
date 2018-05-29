package com.edu.nju.wel.model;

/**
 * Created by qianzhihao on 2017/5/24.
 */
public class TwoValueChartVO implements Comparable<TwoValueChartVO>{
    private String attr;

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    private double v1;
    private double v2;

    public TwoValueChartVO(String attr, double v1, double v2) {
        this.attr = attr;
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getAttr() {
        return attr;
    }

    public double getV1() {
        return v1;
    }

    public double getV2() {
        return v2;
    }

    @Override
    public int compareTo(TwoValueChartVO o) {
        if(Integer.valueOf(attr)>Integer.valueOf(o.getAttr())){
            return 1;
        }else if(Integer.valueOf(attr)==Integer.valueOf(o.getAttr())){
            return 0;
        }
        else {
            return -1;
        }
    }
}

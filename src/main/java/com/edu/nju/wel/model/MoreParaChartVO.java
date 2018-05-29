package com.edu.nju.wel.model;

/**
 * Created by qianzhihao on 2017/6/3.
 */
public class MoreParaChartVO implements Comparable<MoreParaChartVO>{
    String attr;

    double[] values;

    public MoreParaChartVO(String attr, double[] values) {
        this.attr = attr;
        this.values = values;
    }

    public String getAttr() {

        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    @Override
    public int compareTo(MoreParaChartVO o) {
        return Integer.valueOf(attr).compareTo(Integer.valueOf(o.getAttr()));
    }
}

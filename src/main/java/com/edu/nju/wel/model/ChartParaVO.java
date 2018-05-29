package com.edu.nju.wel.model;

/**
 * Created by qianzhihao on 2017/5/15.
 */
public class ChartParaVO implements Comparable<ChartParaVO>{
    String attr;

    String value;

    public ChartParaVO(String attr, String value) {
        this.attr = attr;
        this.value = value;
    }

    public String getAttr() {
        return attr;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(ChartParaVO o) {
        return Double.valueOf(attr).compareTo(Double.valueOf(o.attr));
    }
}


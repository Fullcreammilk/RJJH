package com.edu.nju.wel.model;

/**
 * 数轴图的数据
 * Created by qianzhihao on 2017/5/24.
 */
public class AxisParamVO {
    String name;

    double min;

    double max;

    double avg;

    double value;

    public AxisParamVO(String name, double min, double max, double avg, double value) {
        this.name = name;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getAvg() {
        return avg;
    }

    public double getValue() {
        return value;
    }
}

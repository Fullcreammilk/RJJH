package com.edu.nju.wel.model;

/**
 * Created by qianzhihao on 2017/5/17.
 */
public class BoxPlotParaVO {
    String xAxis;

    double[] datas;

    public BoxPlotParaVO(String xAxis, double[] datas) {
        this.xAxis = xAxis;
        this.datas = datas;
    }

    public String getxAxis() {
        return xAxis;
    }

    public double[] getDatas() {
        return datas;
    }
}

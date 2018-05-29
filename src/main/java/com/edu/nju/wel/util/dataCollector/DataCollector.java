package com.edu.nju.wel.util.dataCollector;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 用于收集线性回归所需数据
 * Created by qianzhihao on 2017/5/31.
 */
public class DataCollector {
    BufferedWriter fw;

    public DataCollector(String filepath) {
        try {
            fw = new BufferedWriter(new FileWriter(filepath, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData(double[] datas) {
        try {
            for (int i = 0; i < datas.length; i++) {
                fw.write(String.valueOf(datas[i]) + " ");
            }
            fw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriter(){
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

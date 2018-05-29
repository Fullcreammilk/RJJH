package com.edu.nju.wel.model;

import com.edu.nju.wel.util.dataCollector.Coefficient;

/**
 * 记录相关电影的相关程度
 * Created by qianzhihao on 2017/6/2.
 */
public class RecMovieVO implements Comparable<RecMovieVO>{
    private String name;

    private String imgUrl;

    //4个值分别对应：同导演、同演员、同流派、同关键字
    private int[] sameNum;

    public RecMovieVO(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        sameNum = new int[4];
        for(int i=0;i<sameNum.length;i++){
            sameNum[i]=0;
        }
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int[] getSameNum() {
        return sameNum;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void addRecValue(int index, int value){
        sameNum[index]+=value;
    }

    @Override
    public int compareTo(RecMovieVO o) {
        int value1 = 0;
        int value2 = 0;
        //计算符合度
        for(int i=0;i<sameNum.length;i++){
            value1 += Coefficient.same[i]*sameNum[i];
            value2 += Coefficient.same[i]*o.sameNum[i];
        }
        return -Integer.valueOf(value1).compareTo(Integer.valueOf(value2));
    }
}

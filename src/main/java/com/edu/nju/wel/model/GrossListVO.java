package com.edu.nju.wel.model;

/**
 * Created by qianzhihao on 2017/6/7.
 */
public class GrossListVO {
    String movieName;

    String imgUrl;

    String gross;

    public GrossListVO(MovieDetailPO po) {
        movieName = po.getName();
        imgUrl = po.getImageUrl();
        gross = po.getGross();
    }

    public String getMovieName() {
        return movieName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getGross() {
        return gross;
    }
}

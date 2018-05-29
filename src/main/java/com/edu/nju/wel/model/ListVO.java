package com.edu.nju.wel.model;

import java.text.DecimalFormat;

/**
 * 电影、导演、演员在列表中展示时需要的数据
 * Created by qianzhihao on 2017/5/12.
 */
public class ListVO{
    private String name;

    private String imgUrl;

    private String score;

    private String releDate;

    public ListVO(MakerDetailPO po) {
        name = po.getName();
        imgUrl = po.getImageUrl();
    }

    //TODO
    public ListVO(MakerDetailVO po) {
        name = po.getName();
        imgUrl = po.getImageUrl();
    }


    public ListVO(MovieDetailPO po) {
        name = po.getName();
        imgUrl = po.getImageUrl();
        score = String.valueOf(po.getRatingValue());
        releDate = po.getReleDate();
    }

    //TODO
    public ListVO(MovieDetailVO vo) {
        name = vo.getName();
        imgUrl = vo.getImageUrl();
        score = String.valueOf(vo.getRatingValue());
        releDate = vo.getReleDate();
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getScore() {
        return score;
    }

    public String getReleDate() {
        return releDate;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

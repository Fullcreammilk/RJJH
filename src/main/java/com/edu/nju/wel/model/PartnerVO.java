package com.edu.nju.wel.model;

/**
 * Created by qianzhihao on 2017/6/8.
 */
public class PartnerVO {
    /**
     * 名字
     */
    private String name;

    /**
     * 头像
     */
    private String imgUrl;

    /**
     * 标题（Best Partner或Worst Partner）
     */
    private String title;

    /**
     * 合作分数变化百分比（含正负）
     */
    private String percent;

    /**
     * 代表电影，最佳拍档的最高分电影和最差拍档的最低分电影
     */
    private String presentativeMovie;

    /**
     * 电影海报
     */
    private String movieImgUrl;

    public PartnerVO(String name, String imgUrl, String title, String percent, String presentativeMovie, String movieImgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.title = title;
        this.percent = percent;
        this.presentativeMovie = presentativeMovie;
        this.movieImgUrl = movieImgUrl;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPercent() {
        return percent;
    }

    public String getPresentativeMovie() {
        return presentativeMovie;
    }

    public String getMovieImgUrl() {
        return movieImgUrl;
    }
}

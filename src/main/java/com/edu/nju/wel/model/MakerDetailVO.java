package com.edu.nju.wel.model;

import java.util.ArrayList;

/**
 * Created by qianzhihao on 2017/5/11.
 */
public class MakerDetailVO {
    private String name;

    private String imageUrl;

    private String birthday;

    private String height;

    private String bio;

    private String type;

    private ArrayList<ListVO> movies;

    public MakerDetailVO(MakerDetailPO po) {
        name = po.getName();
        imageUrl = po.getImageUrl();
        birthday = po.getBirthday();
        height = po.getHeight();
        bio = po.getBio();
        type = po.getType();
        movies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getHeight() {
        return height;
    }

    public String getBio() {
        return bio;
    }

    public String getType() {
        return type;
    }

    public ArrayList<ListVO> getMovies() {
        return movies;
    }

}

package com.edu.nju.wel.model;

import com.edu.nju.wel.util.Iter2List;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 用于排序的VO
 * Created by qianzhihao on 2017/5/11.
 */
public class MovieDetailVO {
    private String name;

    private String imageUrl;

    private double ratingValue;

    private String storyLine;

    private String genres;

    private String country;

    private String language;

    private String releDate;

    private String budget;

    private String openingWeek;

    private String gross;

    private String runTime;

    private ArrayList<ListVO> stars;

    private ArrayList<ListVO> creators;

    public MovieDetailVO(MovieDetailPO po) {
        name = po.getName();
        imageUrl = po.getImageUrl();
        ratingValue = po.getRatingValue();
        storyLine = po.getStoryLine();
        genres = po.getGenres();
        country = po.getCountry();
        language = po.getLanguage();
        releDate = po.getReleDate();
        budget = po.getBudget();
        openingWeek = po.getOpeningWeek();
        gross = po.getGross();
        runTime = po.getRunTime();
        stars = new ArrayList<>();
        creators = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public String getStoryLine() {
        return storyLine;
    }

    public String getGenres() {
        return genres;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getReleDate() {
        return releDate;
    }

    public String getBudget() {
        return budget;
    }

    public String getOpeningWeek() {
        return openingWeek;
    }

    public String getGross() {
        return gross;
    }

    public String getRunTime() {
        return runTime;
    }

    public ArrayList<ListVO> getStars() {
        return stars;
    }

    public ArrayList<ListVO> getCreators() {
        return creators;
    }

    public void setStars(ArrayList<ListVO> stars) {
        this.stars = stars;
    }

    public void setCreators(ArrayList<ListVO> creators) {
        this.creators = creators;
    }
}

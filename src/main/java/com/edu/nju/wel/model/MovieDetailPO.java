package com.edu.nju.wel.model;

import javax.persistence.*;

/**
 * Created by ${WX} on 2017/5/8.
 */
@Entity
@Table(name="movie")
public class MovieDetailPO implements Comparable<MovieDetailPO>{

    @Id
    private String name;

    private String imageUrl;

    private double ratingValue;

    @Column(columnDefinition="text")
    private String storyLine;

    @Column(columnDefinition="text")
    private String genres;

    @Column(columnDefinition="text")
    private String country;

    @Column(columnDefinition="text")
    private String language;

    @Column(columnDefinition="text")
    private String releDate;

    @Column(columnDefinition="text")
    private String budget;

    @Column(columnDefinition="text")
    private String openingWeek;

    @Column(columnDefinition="text")
    private String gross;

    @Column(columnDefinition="text")
    private String runTime;

    private int id=0;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getStoryLine() {
        return storyLine;
    }

    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getReleDate() {
        return releDate;
    }

    public void setReleDate(String releDate) {
        this.releDate = releDate;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getOpeningWeek() {
        return openingWeek;
    }

    public void setOpeningWeek(String openingWeek) {
        this.openingWeek = openingWeek;
    }

    public String getGross() {
        return gross;
    }

    public void setGross(String gross) {
        this.gross = gross;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    @Override
    public int compareTo(MovieDetailPO o) {
        //高分排在前面
        return Double.valueOf(o.ratingValue).compareTo(Double.valueOf(ratingValue));
    }
}

package com.edu.nju.wel.util;

import java.util.ArrayList;

/**
 * Created by qianzhihao on 2017/6/8.
 */
public class PartnerMovieData {
    double sum;

    int count;

    String maxMovie;

    String maxMovieImgUrl;

    double maxRating;

    String minMovie;

    String minMovieImgUrl;

    double minRating;

    public PartnerMovieData() {
        sum = 0;
        count = 0;
        minRating = 10;
        maxRating = 0;
    }

    public String getMinMovie() {
        return minMovie;
    }

    public String getMinMovieImgUrl() {
        return minMovieImgUrl;
    }

    public double getMinRating() {
        return minRating;
    }


    public String getMaxMovie() {
        return maxMovie;
    }

    public String getMaxMovieImgUrl() {
        return maxMovieImgUrl;
    }

    public double getMaxRating() {
        return maxRating;
    }

    public double getAvg(){
        return sum/count;
    }

    public void addMovie(double ratingValue,String movieName,String movieImgUrl){
        if(ratingValue>maxRating){
            maxRating = ratingValue;
            maxMovie = movieName;
            maxMovieImgUrl = movieImgUrl;
        }
        if(ratingValue<minRating){
            minRating = ratingValue;
            minMovie = movieName;
            minMovieImgUrl = movieImgUrl;
        }
        sum+=ratingValue;
        count++;
    }
}

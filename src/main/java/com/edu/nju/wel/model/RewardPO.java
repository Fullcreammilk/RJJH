package com.edu.nju.wel.model;

import javax.persistence.*;

/**
 * Created by ${WX} on 2017/5/23.
 */
@Entity
@Table(name="rewards")
public class RewardPO implements Comparable<RewardPO>{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  long id;

    @Column(columnDefinition="text")
    private String rewardType;

    private String rewardName;

    private int rewardYear;

    @Column(columnDefinition="text")
    private String peopleName;

    private String movieName;

    private String getType;

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public int getRewardYear() {
        return rewardYear;
    }

    public void setRewardYear(int rewardYear) {
        this.rewardYear = rewardYear;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getGetType() {
        return getType;
    }

    public void setGetType(String getType) {
        this.getType = getType;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public int compareTo(RewardPO o) {
        if(Integer.valueOf(rewardYear)>Integer.valueOf(o.getRewardYear())){
            return 1;
        }else if(Integer.valueOf(rewardYear).equals(Integer.valueOf(o.getRewardYear()))){
            return 0;
        }
        else {
            return -1;
        }
    }
}

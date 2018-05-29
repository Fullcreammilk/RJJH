package com.edu.nju.wel.model;

import javax.persistence.*;

/**
 * Created by ${WX} on 2017/5/9.
 */
@Entity
@Table(name="creator2movie")
public class Creator2Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  long id;

    private String creatorName;

    private String movieName;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
    public Creator2Movie(){}

    public Creator2Movie(String creatorName,String movieName){
        this.movieName=movieName;
        this.creatorName=creatorName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}

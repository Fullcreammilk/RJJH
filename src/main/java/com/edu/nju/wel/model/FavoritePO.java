package com.edu.nju.wel.model;

import javax.persistence.*;

/**
 * Created by ${WX} on 2017/5/31.
 */
@Entity
@Table(name="favorite")
public class FavoritePO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  long id;

    private String userName;

    private String name;

    private String type;

    public FavoritePO(String userName, String movieName,String type) {
        this.userName = userName;
        this.name = movieName;
        this.type=type;
    }

    public FavoritePO(){}

    //    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMovieName() {
        return name;
    }

    public void setMovieName(String movieName) {
        this.name = movieName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

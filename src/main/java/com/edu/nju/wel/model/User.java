package com.edu.nju.wel.model;

import javax.persistence.*;

/**
 * Created by zs on 2017/3/2.
 */
@Entity
@Table(name="user")
public class User {
    @Id
    private String name;
    private String password;

    public User() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

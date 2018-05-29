package com.edu.nju.wel.model;

import javax.persistence.*;

/**
 * Created by ${WX} on 2017/6/4.
 */
@Entity
@Table(name="average")
public class AveragePO implements Comparable<AveragePO>{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    private String type;

    private double ave;

    private int num;

    public AveragePO() {
    }

    public AveragePO(String name, String type, double ave,int num) {
        this.name = name;
        this.type = type;
        this.ave = ave;
        this.num=num;
    }

    //    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAve() {
        return ave;
    }

    public void setAve(double ave) {
        this.ave = ave;
    }

    @Override
    public int compareTo(AveragePO o) {
        return -Double.valueOf(ave).compareTo(Double.valueOf(o.ave));
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

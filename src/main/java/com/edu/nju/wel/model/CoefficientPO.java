package com.edu.nju.wel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ${WX} on 2017/6/7.
 */
@Entity
@Table(name="coefficient")
public class CoefficientPO {
    @Id
    private String name;

    private double num;

    public CoefficientPO() {
    }

    public CoefficientPO(String name, double num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
}

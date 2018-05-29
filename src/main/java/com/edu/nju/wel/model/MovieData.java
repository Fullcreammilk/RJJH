package com.edu.nju.wel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ${WX} on 2017/5/21.
 */
@Entity
@Table(name="moviedata")
public class MovieData {
    @Id
    private String name;

    private int personReview;

    private int perfessionReview;

    @Column(columnDefinition="text")
    private String company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPersonReview() {
        return personReview;
    }

    public void setPersonReview(int personReview) {
        this.personReview = personReview;
    }

    public int getPerfessionReview() {
        return perfessionReview;
    }

    public void setPerfessionReview(int perfessionReview) {
        this.perfessionReview = perfessionReview;
    }
}

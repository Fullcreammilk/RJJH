package com.edu.nju.wel.model;

import javax.persistence.*;

/**
 * Created by ${WX} on 2017/4/15.
 */
@Entity
@Table(name="personreview")
public class MovieReviewPO {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  long id;

    private int usefulnum;

    private int allnum;

    private String moviename;

    private String authorname;

    private String titel;

    private String time;

    private String rating;

    @Column(columnDefinition="text")
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUseful() {
        return usefulnum;
    }

    public void setUseful(int useful) {
        this.usefulnum = useful;
    }

    public int getAll() {
        return allnum;
    }

    public void setAll(int all) {
        this.allnum = all;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getAuthor() {
        return authorname;
    }

    public void setAuthor(String author) {
        this.authorname = author;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

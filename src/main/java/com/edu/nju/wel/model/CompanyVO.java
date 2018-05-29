package com.edu.nju.wel.model;

/**
 * Created by ${WX} on 2017/6/3.
 */
public class CompanyVO {
    private String name;

    private String founded;

    private String founder;

    private String background;

    private String history;

    private String imageurl;

    public CompanyVO(CompanyPO po){
        setName(po.getName());
        setBackground(po.getBackground());
        setFounded(po.getFounded());
        setFounder(po.getFounder());
        setHistory(po.getHistory());
        setImageurl("/graphics/company/"+po.getName()+".jpg");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounded() {
        return founded;
    }

    public void setFounded(String founded) {
        this.founded = founded;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}

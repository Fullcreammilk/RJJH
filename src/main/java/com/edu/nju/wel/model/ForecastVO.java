package com.edu.nju.wel.model;

/**
 * Created by qianzhihao on 2017/6/4.
 */
public class ForecastVO {
    //与加成项相关的名字
    private String[] names;
    //对应人员图片链接
    private String[] imgUrls;
    //加成项标题
    private String title;
    //加成项详细信息
    private String msg;

    public ForecastVO(String[] names, String[] imgUrls, String title, String msg) {
        this.names = names;
        this.imgUrls = imgUrls;
        this.title = title;
        this.msg = msg;
    }

    public String[] getNames() {
        return names;
    }

    public String[] getImgUrls() {
        return imgUrls;
    }

    public String getTitle() {
        return title;
    }

    public String getMsg() {
        return msg;
    }
}

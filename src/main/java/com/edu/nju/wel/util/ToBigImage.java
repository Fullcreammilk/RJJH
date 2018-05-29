package com.edu.nju.wel.util;

/**
 * Created by ${WX} on 2017/5/11.
 */
public  class ToBigImage {
    public static String toBigImage(String url){
        if(url==null)
            return null;
        return url.split("_V1_")[0]+"_V1_.jsp";
    }
}

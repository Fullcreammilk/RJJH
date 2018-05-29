package com.edu.nju.wel.util;

/**
 * Created by ${WX} on 2017/6/7.
 */
public class GrossToInt {
    public static int toInt(String gross){
        if (gross==null)
            return 0;
        gross=gross.replace("$","");
        gross=gross.replaceAll(",","");
        return Integer.valueOf(gross);
    }
}

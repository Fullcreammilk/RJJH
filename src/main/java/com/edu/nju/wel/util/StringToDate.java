package com.edu.nju.wel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 将月份的英语转成数字
 * Created by qianzhihao on 2017/5/10.
 */
public class StringToDate {
    /**
     * 月份映射表
     */
    private static Map<String,String> monthMap;

    static {
        //初始化月份映射表
        monthMap = new HashMap<String, String>();
        monthMap.put("January","1");
        monthMap.put("February","2");
        monthMap.put("March","3");
        monthMap.put("April","4");
        monthMap.put("May","5");
        monthMap.put("June","6");
        monthMap.put("July","7");
        monthMap.put("August","8");
        monthMap.put("September","9");
        monthMap.put("October","10");
        monthMap.put("November","11");
        monthMap.put("December","12");
    }

    /**
     * 将字符串转成日期
     * @param dateString 日期的字符串，形如：6 July 1994 (USA)
     * @return 对应Date对象
     */
    public static Date stringToDate(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("d M yyyy");
        //格式应如：
        //6 July 1994 (USA)
        //或者
        //April 1957 (USA)
        //或
        //1925 (Germany)
        //或
        //null

        //若为null，默认最早
        if(dateString==null){
            return new Date(0,0,0);
        }

        //去除（）
        dateString = dateString.replaceAll("\\((.*)\\)","");
        String array[] = dateString.split(" ");

        Date date = null;
        String res = "";
        //判断是哪一种格式
        if(array.length==3) {
            //将月份转为数字
            array[1] = monthMap.get(array[1]);
            res = array[0] + " " + array[1] + " " + array[2];
        }else if(array.length==2){
            //将月份转为数字
            array[0] = monthMap.get(array[0]);
            //若没有上映日期，则默认为1
            res = "1" + " " + array[0] + " " + array[1];
        }else {
            res = "1 1 "+array[0];
        }
        try {
            date = sdf.parse(res);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

package com.edu.nju.wel.util;

import java.util.regex.Pattern;

/**
 * 解析生日中的国籍
 * Created by qianzhihao on 2017/5/21.
 */
public class Birthday2Nationality {
    /**
     * 解析生日中的国籍
     * 格式有：
     * 1 June1937, Memphis, Tennessee, USA
     * 5 December1890, Vienna, Austria-Hungary [now Austria]
     * 8 April1902
     * @param birthday 生日
     * @return
     */
    public static String getNationality(String birthday){
        if(birthday==null||birthday.matches("\\d* \\w*\\d*$")){
            return "Others";
        }
        else {
            //正常情况
            if(!birthday.endsWith("]")) {
                String nationality[] = birthday.split(", ");
                return nationality[nationality.length-1];
            }else {
                String pattern = "[^\\[]*\\[(.*)]";
                String res = birthday.replaceAll(pattern,"$1").substring(4);
                String splited[] = res.split(", ");
                return splited[splited.length-1];
            }
        }
    }
}

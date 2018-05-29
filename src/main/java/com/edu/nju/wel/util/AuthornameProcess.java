package com.edu.nju.wel.util;

/**
 * 处理掉authorname中的
 * Created by qianzhihao on 2017/5/23.
 */
public class AuthornameProcess {
    public static String authornameProcess(String authorname){
        String res;
        //去除括号内的邮箱
        res = authorname.replaceAll("\\((.*)\\)","");
        //去除from以及后面的地址
        res = res.replaceAll("from(.*)","");
        //去除没有地址的null
        res = res.replaceAll("null$","");
        return res;
    }
}

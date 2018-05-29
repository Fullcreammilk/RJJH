package com.edu.nju.wel.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${WX} on 2017/5/10.
 */
public class DelTag {
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    public static String delHTMLTag(String htmlStr) {
        htmlStr=htmlStr.replaceAll("\n","");
        htmlStr=htmlStr.replaceAll("&nbsp;"," ");
        htmlStr = htmlStr.replaceAll("<br />", "\r\n"); //过滤
        htmlStr = htmlStr.replaceAll("<p>", "\r\n");

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签


        return htmlStr.trim(); // 返回文本字符串
    }
}

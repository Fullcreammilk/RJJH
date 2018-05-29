package com.edu.nju.wel.service;

import java.util.ArrayList;

/**
 * 关键词搜索智能推荐
 * Created by qianzhihao on 2017/6/6.
 */
public interface KeywordHelpService {
    public ArrayList<String> keywordHelp(String keyword,String type);
}

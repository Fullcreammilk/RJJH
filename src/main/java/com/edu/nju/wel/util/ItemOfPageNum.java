package com.edu.nju.wel.util;

import com.edu.nju.wel.model.ListVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录每个页面每页放置多少项目
 * Created by qianzhihao on 2017/5/21.
 */
public class ItemOfPageNum {
    public static final int MOVIE_SCH = 20;

    public static final int REVIEW = 5;

    public static final int FORECAST = 20;

    public static final int COMPANY_MOVIE = 15;

    /**
     * 将list分页，取出对应页码中的对象
     * @param page 页码
     * @param itemNum 每页的项目
     * @param list 列表
     * @return 对应页码中的对象
     */
    public static List getPageItem(int page, int itemNum, List list) {
        List res = new ArrayList();
        for (int i = (page - 1) * itemNum; i < page * itemNum && i < list.size(); i++) {
            res.add(list.get(i));
        }
        return res;
    }
}

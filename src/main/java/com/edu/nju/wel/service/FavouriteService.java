package com.edu.nju.wel.service;

import com.edu.nju.wel.model.ListVO;

import java.util.ArrayList;

/**
 * Created by qianzhihao on 2017/6/1.
 */
public interface FavouriteService {
    /**
     * 添加收藏
     * @param username 用户名
     * @param name 电影名
     */
    public void addFavourite(String username,String name,String type);

    /**
     * 删除收藏
     * @param username 用户名
     * @param name 电影名
     */
    public void deleteFavourite(String username,String name,String type);

    /**
     * 判断该用户是否收藏了某部电影
     * @param username 用户名
     * @param name 电影名
     * @return 是否在该用户收藏夹中
     */
    public boolean isInFavourties(String username,String name,String type);

    /**
     * 得到一个用户的收藏并封装成listvo
     * @param username 用户名
     * @return 收藏的电影的list
     */
    public ArrayList<ListVO> getFavourites(String username,String type);
}

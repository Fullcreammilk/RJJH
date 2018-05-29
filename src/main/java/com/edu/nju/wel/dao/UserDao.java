package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.User;

/**
 * Created by zs on 2017/3/2.
 */
public interface UserDao {
    /**
     * 获得一个uer实例
     * @param name 用户名
     * @return 用户实例
     */
    public User find(String name);

    /**
     * 新增一个用户实例
     * @param u 要新增的用户实例
     */
    public void add(User u);
}

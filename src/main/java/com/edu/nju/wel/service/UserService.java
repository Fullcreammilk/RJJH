package com.edu.nju.wel.service;

import com.edu.nju.wel.model.User;

import java.util.List;

/**
 * 用户登陆注册模块
 * Created by zs on 2017/3/2.
 */
public interface UserService {
    /**
     * 根据用户名查找对应用户
     * @param username 用户名
     * @return 对应用户信息
     */
    public User find(String username);

    /**
     * 添加用户信息
     * @param user
     */
    public void addUser(User user);
}

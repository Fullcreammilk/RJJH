package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.User;
import com.edu.nju.wel.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qianzhihao on 2017/3/2.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {


    public User find(String username) {
        return DAOManager.userDao.find(username);
    }

    public void addUser(User user) {
        DAOManager.userDao.add(user);
    }
}

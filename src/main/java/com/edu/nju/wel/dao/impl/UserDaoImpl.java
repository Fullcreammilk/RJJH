package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.UserDao;
import com.edu.nju.wel.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zs on 2017/3/2.
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public User find(String name) {
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.User where name = ?";
        Query query = session.createQuery(hql)
                .setParameter(0,name);
        List<User> list = query.list();


    if(list.size()>0)
        return list.get(0);
        else
            return null;
    }

    public void add(User u) {
        session=sessionFactory.getCurrentSession();
        session.save(u);
    }
}

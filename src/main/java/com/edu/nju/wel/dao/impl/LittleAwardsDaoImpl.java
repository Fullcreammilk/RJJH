package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.LittleAwardsDao;
import com.edu.nju.wel.model.LittleAwardsPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${WX} on 2017/6/6.
 */
@Repository
public class LittleAwardsDaoImpl implements LittleAwardsDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;


    @Override
    public void add(LittleAwardsPO po) {
        session=sessionFactory.getCurrentSession();
        session.save(po);
    }

    @Override
    public List<LittleAwardsPO> getByName(String name, String type) {
        session=sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.LittleAwardsPO where name = ? and type = ? order by year";
        Query query=session.createQuery(hql)
                .setParameter(0,name)
                .setParameter(1,type);
        List<LittleAwardsPO> list=query.list();
        return list;
    }

    @Override
    public void delete(LittleAwardsPO po) {
        session=sessionFactory.getCurrentSession();
        session.delete(po);
    }
}

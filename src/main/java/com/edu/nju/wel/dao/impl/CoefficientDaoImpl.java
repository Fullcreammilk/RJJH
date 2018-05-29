package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.CoefficientDao;
import com.edu.nju.wel.model.CoefficientPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${WX} on 2017/6/7.
 */
@Repository
public class CoefficientDaoImpl implements CoefficientDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    @Override
    public void add(CoefficientPO po) {
        session=sessionFactory.getCurrentSession();
        session.save(po);
    }

    @Override
    public void update(CoefficientPO po) {
        session=sessionFactory.getCurrentSession();
        session.update(po);
    }

    @Override
    public double getByName(String key) {
        session=sessionFactory.getCurrentSession();
        String hql ="select num from com.edu.nju.wel.model.CoefficientPO where name = ?";
        Query query=session.createQuery(hql)
                .setParameter(0,key);
        List<Double> list=query.list();
        if(list.size()>0)
            return list.get(0);
        return -100;
    }
}

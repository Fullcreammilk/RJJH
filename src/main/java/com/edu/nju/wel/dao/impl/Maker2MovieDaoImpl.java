package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.Maker2MovieDao;
import com.edu.nju.wel.model.Creator2Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/10.
 */
@Repository
public class Maker2MovieDaoImpl implements Maker2MovieDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public void add(Creator2Movie relation) {
        session=sessionFactory.getCurrentSession();
        session.save(relation);
    }

    @Override
    public void delete(Creator2Movie relation) {
        session=sessionFactory.getCurrentSession();
        session.delete(relation);
    }

    @Override
    public List<Creator2Movie> getAll() {
        session=sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.Creator2Movie";
        Query query = session.createQuery(hql);
        List<Creator2Movie> list=query.list();
        return list;
    }
}

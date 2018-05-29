package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.MovieReviewDao;
import com.edu.nju.wel.model.MovieReviewPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${WX} on 2017/4/15.
 */
@Repository
public class MovieReviewDaoImpl implements MovieReviewDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public List<MovieReviewPO> find(String movieName) {
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.MovieReviewPO where moviename=?";
        Query query = session.createQuery(hql)
                .setParameter(0,movieName);
        List<MovieReviewPO> list = query.list();

        return list;
    }

    @Override
    public void add(MovieReviewPO movie) {
        session=sessionFactory.getCurrentSession();
        session.save(movie);
    }

    @Override
    public int getPersonalReviewNum(String movieName) {
        int res=0;
        session = sessionFactory.getCurrentSession();
        String hql = "select personReview from com.edu.nju.wel.model.MovieData where name=?";
        Query query = session.createQuery(hql)
                .setParameter(0,movieName);
        List<Integer> list = query.list();

        if(list.size()>0)
            res=list.get(0);

        return res;
    }
}

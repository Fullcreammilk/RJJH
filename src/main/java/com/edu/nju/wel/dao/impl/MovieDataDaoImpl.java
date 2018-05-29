package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.MovieDataDao;
import com.edu.nju.wel.model.MovieData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/21.
 */
@Repository
public class MovieDataDaoImpl implements MovieDataDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    @Override
    public void add(MovieData data) {
        session=sessionFactory.getCurrentSession();
        session.save(data);
    }

    @Override
    public List<MovieData> getAll() {
        session=sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.MovieData";
        Query query = session.createQuery(hql);
        List<MovieData> list = query.list();
//        for (MovieReviewPO u:list
//                ) {
//            System.out.println(u.getMoviename());
//        }

        return list;
    }

    @Override
    public void update(MovieData data) {
        session=sessionFactory.getCurrentSession();
        session.update(data);
    }

    @Override
    public void delete(MovieData data) {
        session=sessionFactory.getCurrentSession();
        session.delete(data);
    }
}

package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.MovieDetailDao;
import com.edu.nju.wel.model.MovieDetailPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ${WX} on 2017/5/9.
 */
@Repository
public class MovieDetailDaoImpl implements MovieDetailDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;


    public List<MovieDetailPO> getAll(){
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.MovieDetailPO where ratingValue != 0";
        Query query = session.createQuery(hql);
        List<MovieDetailPO> list = query.list();
        return list;
    }

    public MovieDetailPO getByName(String movieName) {
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.MovieDetailPO where name=?";
        Query query = session.createQuery(hql)
                .setParameter(0,movieName);
        List<MovieDetailPO> list = query.list();
//        for (MovieReviewPO u:list
//                ) {
//            System.out.println(u.getMoviename());
//        }
        if(list.size()>0)
            return list.get(0);
        return null;
    }

    public Iterator<String> getCreators(String movieName) {
        session = sessionFactory.getCurrentSession();
        String hql = "select distinct creatorName from com.edu.nju.wel.model.Creator2Movie where movieName=? and creatorName in " +
                "(select name from com.edu.nju.wel.model.MakerDetailPO where type='creator')";
        Query query = session.createQuery(hql)
                .setParameter(0,movieName);
        List<String> list=query.list();
        return list.iterator();
    }

    public Iterator<String> getStars(String movieName) {
        session = sessionFactory.getCurrentSession();
        String hql = "select distinct  creatorName from com.edu.nju.wel.model.Creator2Movie where movieName=? and creatorName in " +
                "(select name from com.edu.nju.wel.model.MakerDetailPO where type='star')";
        Query query = session.createQuery(hql)
                .setParameter(0,movieName);
        List<String> list=query.list();
        return list.iterator();
    }

    public void add(MovieDetailPO po) {
        session=sessionFactory.getCurrentSession();
        session.save(po);
    }
}

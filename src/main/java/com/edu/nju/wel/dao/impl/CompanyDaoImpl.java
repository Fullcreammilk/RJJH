package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.CompanyDao;
import com.edu.nju.wel.model.CompanyPO;
import com.edu.nju.wel.model.MovieDetailPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/31.
 */
@Repository
public class CompanyDaoImpl implements CompanyDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    @Override
    public  List<CompanyPO> getAll() {
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.CompanyPO";
        Query query = session.createQuery(hql);
        List<CompanyPO> list = query.list();
        return list;
    }

    @Override
    public List<MovieDetailPO> getMoviesByCompanyName(String companyName) {
        session = sessionFactory.getCurrentSession();
        String hql ="from com.edu.nju.wel.model.MovieDetailPO movie where movie.name in " +
                "(select name from com.edu.nju.wel.model.MovieData where company like ?)";
        Query query=session.createQuery(hql)
                .setParameter(0,"%"+companyName+"%");
        List<MovieDetailPO> list=query.list();
        return list;
    }

    @Override
    public CompanyPO getByName(String comoanyName) {
        session = sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.CompanyPO where name like ?";
        Query query=session.createQuery(hql)
                .setParameter(0,"%"+comoanyName+"%");
        List<CompanyPO> list= query.list();
        if(list.size()>0)
            return list.get(0);
        return null;
    }
}

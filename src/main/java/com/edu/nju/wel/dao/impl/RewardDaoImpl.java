package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.RewardDao;
import com.edu.nju.wel.model.RewardPO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/23.
 */
@Repository
public class RewardDaoImpl implements RewardDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;
    @Override
    public void add(RewardPO po) {
        session=sessionFactory.getCurrentSession();
        session.save(po);
    }

    @Override
    public List<RewardPO> getByPeopleName(String name) {
        session=sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.RewardPO where peopleName like ?";
        Query query = session.createQuery(hql)
                .setParameter(0,"%"+name+"%");
        List<RewardPO> list=query.list();
        return list;
    }

    @Override
    public List<RewardPO> getByMovieName(String name) {
        session=sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.RewardPO where movieName like ?";
        Query query = session.createQuery(hql)
                .setParameter(0,"%"+name+"%");
        List<RewardPO> list=query.list();
        return list;
    }

    @Override
    public List<RewardPO> getAll() {
        session=sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.RewardPO";
        Query query = session.createQuery(hql);
        List<RewardPO> list=query.list();
        return list;
    }

    @Override
    public void update(RewardPO po) {
        session=sessionFactory.getCurrentSession();
        session.update(po);
    }
}

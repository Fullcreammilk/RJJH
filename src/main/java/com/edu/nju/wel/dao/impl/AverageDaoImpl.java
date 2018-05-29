package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.AverageDao;
import com.edu.nju.wel.model.AveragePO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ${WX} on 2017/6/4.
 */
@Repository
public class AverageDaoImpl implements AverageDao {
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    @Override
    public void add(AveragePO po) {
        session=sessionFactory.getCurrentSession();
        session.save(po);
    }

    @Override
    public AveragePO getByName(String name, String type) {
        session=sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.AveragePO where name = ? and type = ?";
        Query query=session.createQuery(hql)
                .setParameter(0,name)
                .setParameter(1,type);
        List<AveragePO> list=query.list();
        if(list.size()>0)
            return list.get(0);
        return null;
    }

    @Override
    public void update(AveragePO po) {
        session=sessionFactory.getCurrentSession();
        session.update(po);
    }

    @Override
    public List<AveragePO> getAll(String type) {
        session=sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.AveragePO where  type = ? order by ave desc";
        Query query=session.createQuery(hql)
                .setParameter(0,type);
        List<AveragePO> list=query.list();
        return list;
    }
}

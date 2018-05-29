package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.MakerDetailDao;
import com.edu.nju.wel.model.MakerDetailPO;
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
public class MakerDetailDaoImpl implements MakerDetailDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    public List<MakerDetailPO> getAll(String type) {
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.MakerDetailPO where type=?";
        Query query = session.createQuery(hql)
                .setParameter(0,type);
        List<MakerDetailPO> list = query.list();
        return list;
    }

    @Override
    public List<MakerDetailPO> getAllByFirstLetter(String type,String firstChar) {
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.MakerDetailPO where type=? and name like ?";
        Query query = session.createQuery(hql)
                .setParameter(0,type)
                .setParameter(1,firstChar+"%");
        List<MakerDetailPO> list = query.list();
        return list;
    }

    public MakerDetailPO getByName(String creatorName) {
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.MakerDetailPO where name=?";
        Query query = session.createQuery(hql)
                .setParameter(0,creatorName);
        List<MakerDetailPO> list = query.list();

        if(list.size()>0)
            return list.get(0);
        return null;
    }

    public Iterator<String> getMovies(String creatorName) {
        session = sessionFactory.getCurrentSession();
        String hql = "select distinct movieName from com.edu.nju.wel.model.Creator2Movie where creatorName=?";
        Query query = session.createQuery(hql)
                .setParameter(0,creatorName);
        List<String> list = query.list();
        return list.iterator();
    }

    public void add(MakerDetailPO po) {
        session=sessionFactory.getCurrentSession();
        session.save(po);
    }

    @Override
    public List<MakerDetailPO> modify(String name, String type) {
        session=sessionFactory.getCurrentSession();
        session = sessionFactory.getCurrentSession();
        String hql = "from com.edu.nju.wel.model.MakerDetailPO where name = ? and type = ?";
        Query query = session.createQuery(hql)
                .setParameter(0,name)
                .setParameter(1,type);
        List<MakerDetailPO> list = query.list();
        //System.out.println(list.size());
        return list;
    }

    @Override
    public void delete(MakerDetailPO po){
        //System.out.println("here");
        session=sessionFactory.getCurrentSession();
        session.delete(po);
    }
}

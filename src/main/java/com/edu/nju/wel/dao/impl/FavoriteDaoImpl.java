package com.edu.nju.wel.dao.impl;

import com.edu.nju.wel.dao.FavoriteDao;
import com.edu.nju.wel.model.FavoritePO;
import com.edu.nju.wel.model.MakerDetailPO;
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
public class FavoriteDaoImpl implements FavoriteDao{
    @Autowired
    protected SessionFactory sessionFactory;
    private Session session;

    @Override
    public List<MovieDetailPO> getMoviesByUserName(String userName) {
        session=sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.MovieDetailPO where name in" +
                "(select name from com.edu.nju.wel.model.FavoritePO where userName=? and type = 'movie') ";
        Query query=session.createQuery(hql)
                .setParameter(0,userName);
        List<MovieDetailPO> list=query.list();

        return list;
    }

    @Override
    public List<MakerDetailPO> getMakerByUserName(String userName,String type) {
        session=sessionFactory.getCurrentSession();
        String hql="from com.edu.nju.wel.model.MakerDetailPO where type = ? and name in" +
                "(select name from com.edu.nju.wel.model.FavoritePO  where userName=? and type = ? )";
        Query query=session.createQuery(hql)
                .setParameter(0,type)
                .setParameter(1,userName)
                .setParameter(2,type);
        List<MakerDetailPO> list=query.list();
        System.out.println(list.size());

        return list;
    }

    @Override
    public void add(FavoritePO po) {
        session=sessionFactory.getCurrentSession();
        session.save(po);
    }

    @Override
    public void delete(FavoritePO po) {
        System.out.println(po.getUserName()+po.getUserName()+po.getType() );
        session=sessionFactory.getCurrentSession();
        String hql="delete from com.edu.nju.wel.model.FavoritePO po where po.userName=? and po.name=? and po.type = ?";
        Query query=session.createQuery(hql)
                .setParameter(0,po.getUserName())
                .setParameter(1,po.getMovieName())
                .setParameter(2,po.getType());
        query.executeUpdate();
    }
}

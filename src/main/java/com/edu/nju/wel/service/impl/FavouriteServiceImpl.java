package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.FavoritePO;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.model.MakerDetailPO;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.service.FavouriteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by qianzhihao on 2017/6/1.
 */
@Service("FavouriteService")
public class FavouriteServiceImpl implements FavouriteService{
    @Override
    public void addFavourite(String username, String name, String type) {
        DAOManager.favoriteDao.add(new FavoritePO(username, name,type));
    }

    @Override
    public void deleteFavourite(String username, String name, String type) {
        DAOManager.favoriteDao.delete(new FavoritePO(username, name,type));
    }

    @Override
    public boolean isInFavourties(String username, String name, String type) {
        if(type.equals("movie")) {
            for(MovieDetailPO po:DAOManager.favoriteDao.getMoviesByUserName(username)){
                if(po.getName().equals(name)){
                    return true;
                }
            }
            return false;
        }else{
            for(MakerDetailPO po:DAOManager.favoriteDao.getMakerByUserName(username,type)){
                if(po.getName().equals(name)){
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public ArrayList<ListVO> getFavourites(String username,String type) {
        ArrayList<ListVO> res = new ArrayList<>();
        if(type.equals("movie")) {
            for (MovieDetailPO favourite : DAOManager.favoriteDao.getMoviesByUserName(username)) {
                res.add(new ListVO(favourite));
            }
        }
        else {
            for (MakerDetailPO favourite : DAOManager.favoriteDao.getMakerByUserName(username,type)) {
                res.add(new ListVO(favourite));
            }
        }
        return res;
    }
}

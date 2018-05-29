package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.FavoritePO;
import com.edu.nju.wel.model.MakerDetailPO;
import com.edu.nju.wel.model.MovieDetailPO;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/31.
 */
public interface FavoriteDao {
    /**
     * 获得一个用户收藏的电影
     * @param userName 用户名
     * @return 电影实例列表
     */
    public List<MovieDetailPO> getMoviesByUserName(String userName);

    /**
     * 获得一个用户收藏的maker信息
     *
     * @param userName 用户名
     * @param type makertype
      * @return 返回maker实例列表
     */

    public List<MakerDetailPO> getMakerByUserName(String userName,String type);

    /**
     * 新增一个收藏实例
     * @param po 要新增的实例
     */
    public void add(FavoritePO po);

    /**
     * 删除一个收藏实例
     * @param po 要删除的实例
     */
    public void delete(FavoritePO po);
}

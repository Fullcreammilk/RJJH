package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.MakerDetailPO;

import java.util.Iterator;
import java.util.List;

/**
 * Created by ${WX} on 2017/5/9.
 */
public interface MakerDetailDao {
    /**
     * 根据maker类型获得该类所有的makerpo
     * @param type maker的类型 star creator
     * @return 符合要求的polist
     */
    public List<MakerDetailPO> getAll(String type);

    /**
     * 根据类型和首字母获得符合要求的makerpo
     * @param type maker的类型 star creator
     * @param firstChar 名字首字母
     * @return  符合要求的polist
     */
    public List<MakerDetailPO> getAllByFirstLetter(String type,String firstChar);

    /**
     * 根据名字获得某个makerpo
     * @param creatorName creator或者star的名字
     * @return返回符合要求的po 若没有则返回null
     */
    public MakerDetailPO getByName(String creatorName);

    /**
     *
     * @param creatorName 返回某个maker参与的电影名
     * @return所有电影名的迭代器
     */
    public Iterator<String> getMovies(String creatorName);

    /**
     * 新增一个maker实例
     * @param po 要新增的实例
     */
    public void add(MakerDetailPO po);

    /**
     * 用于数据层查重的
     * @param name
     * @param type
     * @return
     */

    public List<MakerDetailPO> modify(String name,String type);

    /**
     * 删除一个maker实例
     * @param po 要删除的实例
     */
    public void delete(MakerDetailPO po);
}

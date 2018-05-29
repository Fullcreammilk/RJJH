package com.edu.nju.wel.dao;

import com.edu.nju.wel.model.CompanyPO;
import com.edu.nju.wel.model.MovieDetailPO;

import java.util.List;

/**
 * Created by ${WX} on 2017/5/31.
 */
public interface CompanyDao {
    /**
     * 获得所有公司实例
     * @return 公司实例列表
     */
    public  List<CompanyPO> getAll();

    /**
     * 获得一个公司的电影实例
     *
     * @param companyName 公司名字
     * @return 返回公司电影实例列表
     */
    public List<MovieDetailPO> getMoviesByCompanyName(String companyName);

    /**
     * 获得一个公司实例
     *
     * @param comoanyName 公司名
     * @return 公司实例
     */
    public CompanyPO getByName(String comoanyName);
}

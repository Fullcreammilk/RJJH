package com.edu.nju.wel.service;

import com.edu.nju.wel.model.CompanyVO;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.model.TwoValueChartVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取公司详细信息
 * Created by Administrator on 2017/5/31.
 */
public interface CompanyDetailService {
    /**
     * 获取一个公司的详细信息
     * @param companyName 公司名
     * @return 对应公司的详细信息
     */
    public CompanyVO getCompanyDetail(String companyName);

    /**
     * 返回所有公司的资料
     * @return
     */
    public List<CompanyVO> getAllCompany();

    /**
     * 获取某个公司的某流派的电影
     * @param companyName 公司名
     * @param genre 流派
     * @return
     */
    public List<ListVO> getMoviesByCompanyName(String companyName,String genre);

    /**
     * 该公司所有的标签评分统计
     * @param companyName 公司名
     * @return
     */
    public Map<String,Double> getTopSix(String companyName);

    /**
     *
     * @param companyName 公司名
     * @param getType 提名或者获得 具体看数据库相应字段
     * @param awardType oscar or global
     * @return
     */
    public Map<Integer,Integer> getCompanyAwards(String companyName,String getType,String awardType);

    public ArrayList<TwoValueChartVO> getGrossPerYear(String companyName);
}

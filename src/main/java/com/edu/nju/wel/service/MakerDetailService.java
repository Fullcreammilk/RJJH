package com.edu.nju.wel.service;

import com.edu.nju.wel.model.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 获取明星、导演、作者等人物的详细信息
 * Created by qianzhihao on 2017/5/10.
 */
public interface MakerDetailService {
    /**
     * 获取一个人物的详细信息
     * @param makerName 人名
     * @return 对应人物的详细信息
     */
    public MakerDetailVO getMakerDetail(String makerName);

    /**
     * 根据首字母返回对应明星信息
     * @param keyword 关键词
     * @param nationality 国籍
     * @return 对应明星的信息列表
     */
    public ArrayList<ListVO> getStarList(String keyword,String nationality);

    /**
     * 根据首字母返回对应导演作者的信息
     * @param keyword 关键词
     * @param nationality 国籍
     * @return 对应导演作者的信息列表
     */
    public ArrayList<ListVO> getCreatorList(String keyword,String nationality);

    /**
     * 获得一个明星或者导演的所有电影名
     * @param makerName 名字
     * @return 对应电影名
     */
    public Iterator<String> getMovies(String makerName);

    /**
     * 获得所有明星的所有国籍
     * @return
     */
    public ArrayList<String> getNationalities();

    /**
     * 获得一个导演或明星的每年的票房
     * @param makerName 名字
     * @return 每年的年份及票房和主演电影数
     */
    public ArrayList<TwoValueChartVO> getGrossPerYear(String makerName);

    /**
     * 获得一个明星、导演的所有奖项
     * @param makerName 人名
     * @return 对应人的所有奖项
     */
    public List<RewardPO> getReward(String makerName);

    /**
     * 返回一个演员或导演的作品中最高分的最多6个genre，不足2个则返回null
     * @param makerName 人
     * @return
     */
    public Map<String,Double> getTopGenre(String makerName);

    /**
     * 获得提名和获奖情况
     * @param makerName 人名
     * @param type 类型(导演或演员)
     * @return
     */
    public ArrayList<LittleAwardsVO> getAwardAndNom(String makerName,String type);

    public ModelAndView makerSearch(String viewName, String keyword, String nationality, String type, String page);

    public Map<String,Double> getMakerRadarMap(String name,String type);

    /**
     * 获得一个演员或导演对应的合作拍档
     * @param name 名字
     * @param type 类型
     * @return
     */
    public ArrayList<PartnerVO> getPartners(String name,String type);
}

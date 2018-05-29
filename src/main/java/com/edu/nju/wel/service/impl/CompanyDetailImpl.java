package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.CompanyDetailService;
import com.edu.nju.wel.util.AverageCounter;
import com.edu.nju.wel.util.MoviesGrossPerYear;
import com.edu.nju.wel.util.TagAverage;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2017/5/31.
 */
@Service("CompanyDetailService")
public class CompanyDetailImpl implements CompanyDetailService {

    @Override
    public CompanyVO getCompanyDetail(String companyName) {
        //TODO
        return new CompanyVO(DAOManager.companyDao.getByName(companyName));
    }

    @Override
    public List<CompanyVO> getAllCompany() {
        List<CompanyVO> list = new ArrayList<>();
        for (CompanyPO po : DAOManager.companyDao.getAll()) {
            list.add(new CompanyVO(po));
        }
        return list;
    }

    @Override
    public List<ListVO> getMoviesByCompanyName(String companyName,String genre) {
        List<ListVO> res = new ArrayList<>();
        for (MovieDetailPO po : DAOManager.companyDao.getMoviesByCompanyName(companyName)) {
            //筛选符合流派条件的电影
            if(genre.equals("All")||po.getGenres().indexOf(genre)>=0)
                res.add(new ListVO(po));
        }
        return res;
    }

    @Override
    public Map<String, Double> getTopSix(String companyName) {
        List<MovieDetailPO> list = DAOManager.companyDao.getMoviesByCompanyName(companyName);
        //System.out.println(list.size());
        HashMap<String, AverageCounter> map;
        String[] tags;
        map= TagAverage.countTagAverage(list);
        Iterator<String> itr;
        if (map.size() < 3)
            return null;
        Map<String, Double> res = new HashMap<>();
        Set<String> set = map.keySet();
        itr = set.iterator();
        String key;
        while (itr.hasNext()) {
            key = itr.next();
            res.put(key, map.get(key).count());
        }
//        if (res.size() < 7)
//            return res;
//        List<Map.Entry<String, Double>> list1 =
//                new ArrayList<>(res.entrySet());
//
//        Collections.sort(list1, new Comparator<Map.Entry<String, Double>>() {
//            public int compare(Map.Entry<String, Double> o1,
//                               Map.Entry<String, Double> o2) {
//                return (o2.getValue().compareTo(o1.getValue()));
//            }
//        });
//        res.clear();
//        for (int i = 0; i < 6; i++) {
//            res.put(list1.get(i).getKey(), list1.get(i).getValue());
//        }
        return res;
    }

    @Override
    public Map<Integer, Integer> getCompanyAwards(String companyName, String getType, String awardType) {
        int year;
        if (awardType.equals("oscar")) {
            year = 2018;
        } else {
            year = 2017;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1960; i < year; i++) {
            map.put(i, 0);
        }
        List<MovieDetailPO> list = DAOManager.companyDao.getMoviesByCompanyName(companyName);
        for (MovieDetailPO po : list
                ) {
            List<RewardPO> rewardPOS = DAOManager.rewardDao.getByMovieName(po.getName());
            for (RewardPO repo :
                    rewardPOS) {
                if (repo.getGetType().equals(getType)) {
                    //System.out.println("done");
                    map.put(repo.getRewardYear(), map.get(repo.getRewardYear()) + 1);
                }
            }
        }
        return map;
    }

    @Override
    public ArrayList<TwoValueChartVO> getGrossPerYear(String companyName) {
        List<MovieDetailPO> movies = DAOManager.companyDao.getMoviesByCompanyName(companyName);
        return MoviesGrossPerYear.getGrossPerYear(movies);
    }


}

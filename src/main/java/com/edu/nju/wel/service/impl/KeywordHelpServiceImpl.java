package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.MakerDetailPO;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.service.KeywordHelpService;
import com.edu.nju.wel.util.ConformityLevel;
import com.edu.nju.wel.util.Persistence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by qianzhihao on 2017/6/6.
 */
@Service("KeywordHelpService")
public class KeywordHelpServiceImpl implements KeywordHelpService{
    private static final int KEYWORD_NUM = 5;
    @Override
    public ArrayList<String> keywordHelp(String keyword, String type) {
        ArrayList<String> res = new ArrayList<>();

        if(type.equals("movie")){
            for(MovieDetailPO po: Persistence.getAllMovie()){
                int level = ConformityLevel.getConformityLevel(keyword,po.getName());
                addToList(res,po.getName(),level);
            }
        }else if(type.equals("star")||type.equals("creator")){
            for(MakerDetailPO po: Persistence.getAllMaker(type)){
                int level = ConformityLevel.getConformityLevel(keyword,po.getName());
                addToList(res,po.getName(),level);
            }
        }
        while(res.size()>KEYWORD_NUM){
            res.remove(KEYWORD_NUM);
        }
        return res;
    }

    /**
     * 将一个名字加入list
     * @param list
     * @param name
     * @param level
     */
    private void addToList(ArrayList<String> list,String name,int level){
        if(level==1){
            list.add(name);
        }
        //是0，即完全相同，放第一个
        else if(level==0){
            list.add(0,name);
        }
    }
}

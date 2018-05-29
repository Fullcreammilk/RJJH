package com.edu.nju.wel.service.impl.sortStrategy;

import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.model.MovieDetailVO;

import java.util.Comparator;

/**
 * 得分比较器,由高到低排列
 * Created by qianzhihao on 2017/3/24.
 */
public class Score implements Comparator<ListVO> {
    public int compare(ListVO o1, ListVO o2) {

        if(Double.valueOf(o1.getScore())>Double.valueOf(o2.getScore())){
            return -1;
        }else if(Double.valueOf(o1.getScore()).equals(Double.valueOf(o2.getScore()))){
            return 0;
        }else {
            return 1;
        }
    }
}

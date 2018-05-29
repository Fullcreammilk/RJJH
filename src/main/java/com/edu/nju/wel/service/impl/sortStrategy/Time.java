package com.edu.nju.wel.service.impl.sortStrategy;

import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.model.MovieDetailPO;
import com.edu.nju.wel.model.MovieDetailVO;
import com.edu.nju.wel.util.StringToDate;

import java.util.Comparator;
import java.util.Date;

/**
 * 时间比较器，日期由大到小排列
 * Created by qianzhihao on 2017/3/24.
 */
public class Time implements Comparator<ListVO>{
    public int compare(ListVO o1, ListVO o2) {
        Date date1 = StringToDate.stringToDate(o1.getReleDate());
        Date date2 = StringToDate.stringToDate(o2.getReleDate());
        if(date1.after(date2)) {
            return -1;
        }
        else if(date1.equals(date2)){
            return  0;
        }
        else {
            return 1;
        }
    }
}

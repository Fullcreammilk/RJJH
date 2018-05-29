package com.edu.nju.wel.service.impl.sortStrategy;

import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.util.ConformityLevel;

import java.util.Comparator;

/**
 * 符合程度比较器,按与关键字中相同的单词数由大到小排列
 * Created by qianzhihao on 2017/5/10.
 */
public class ConfromityLevelComparator implements Comparator<ListVO>{
    String keyword;

    public ConfromityLevelComparator(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public int compare(ListVO o1, ListVO o2) {
        int l1 = ConformityLevel.getConformityLevel(keyword,o1.getName());
        int l2 = ConformityLevel.getConformityLevel(keyword,o2.getName());
        if(l1>l2)
            return -1;
        if(l1==l2)
            return 0;
        else
            return 1;
    }
}

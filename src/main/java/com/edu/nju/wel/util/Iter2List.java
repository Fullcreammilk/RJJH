package com.edu.nju.wel.util;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by qianzhihao on 2017/5/14.
 */
public class Iter2List {
    public static ArrayList iter2list(Iterator iterator){
        ArrayList res = new ArrayList();
        Object o = null;
        while (iterator.hasNext()){
            o = iterator.next();
            res.add(o);
        }
        return res;
    }
}

package com.edu.nju.wel.util;

/**
 * 将带有货币单位转成int变量
 * Created by qianzhihao on 2017/5/23.
 */
public class Money2Int {
    public static int money2int(String money){
        if(money.startsWith("$")){
            return Integer.valueOf(money.replace("$","").replace(",",""));
        }
        else
            //TODO
            return 0;
    }
}

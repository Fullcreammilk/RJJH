package com.edu.nju.wel.util;

import com.edu.nju.wel.model.MoreParaChartVO;
import com.edu.nju.wel.model.RewardPO;
import com.edu.nju.wel.model.TwoValueChartVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianzhihao on 2017/6/3.
 */
public class AwardStatistic {
    public static ArrayList<MoreParaChartVO> countAwards(List<RewardPO> rewards){
        ArrayList<MoreParaChartVO> res = new ArrayList<>();
        for(RewardPO po:rewards){
            //判断是否已经存在该年份
            boolean exist = false;
            //是否是获奖者 TODO winnner
            boolean isWinner = (po.getGetType().equals("winnner"));
            //是否是奥斯卡
            boolean isOscar = (po.getRewardName().endsWith("oscar"));

            //数组4个分别为奥斯卡得奖、提名、全球将得奖、提名
            for(MoreParaChartVO vo:res){
                if(vo.getAttr().equals(String.valueOf(po.getRewardYear()))){
                    double[] values = vo.getValues();
                    values = addAward(isOscar,isWinner,values);

                    vo.setValues(values);
                    exist = true;
                    break;
                }
            }

            if(!exist){
                double[] values = {0,0,0,0};
                values = addAward(isOscar,isWinner,values);

                res.add(new MoreParaChartVO(String.valueOf(po.getRewardYear()), values));
            }
        }
        return res;
    }

    private static double[] addAward(boolean isOscar,boolean isWinner,double[] values){
        if(isWinner) {
            if (isOscar)
                values[0]++;
            else
                values[2]++;
        }
        else{
            if(isOscar)
                values[1]++;
            else
                values[3]++;
        }
        return values;
    }
}

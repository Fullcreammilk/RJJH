package test.com.edu.nju.wel.service.impl;

import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.MovieDetailService;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import com.edu.nju.wel.service.impl.MovieDetailServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.Iterator;
import java.util.Map;

/**
 * MakerDetailImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 15, 2017</pre>
 */
public class MakerDetailImplTest {
    MakerDetailService makerDetailService = new MakerDetailImpl();

    MovieDetailService movieDetailService = new MovieDetailServiceImpl();
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getMakerDetail(String makerName)
     */
    @Test
    public void testGetMakerDetail() throws Exception {
        Assert.assertEquals("star",makerDetailService.getMakerDetail("Aaron Eckhart").getType());
    }

    /**
     * Method: getStarList(String firstLetter)
     */
    @Test
    public void testGetStarDetailsByFirstLetter() throws Exception {
        Assert.assertEquals("Aaron Eckhart",makerDetailService.getStarList("Aaron Eckhart","All").get(0).getName());
        Assert.assertEquals(8,makerDetailService.getStarList("Ben","All").size());
        Assert.assertEquals(19,makerDetailService.getStarList("","France").size());
    }
    /**
     * Method: getStarList(String firstLetter)
     */
    @Test
    public void testGetStarDetails() throws Exception {
        Assert.assertEquals(2218,makerDetailService.getStarList("","All").size());
    }

    /**
     * Method: getCreatorList(String firstLetter)
     */
    @Test
    public void testGetDnWDetailsByFirstLetter() throws Exception {
        Assert.assertEquals(1,makerDetailService.getCreatorList("Adam Elliot","All").size());
    }

    @Test
    public void testGetNationalities()throws Exception{
        for(String x:makerDetailService.getNationalities()){
            System.out.println(x);
        }
    }

    @Test
    public void testGetGrossPerYear()throws Exception{
        for(TwoValueChartVO vo:new MakerDetailImpl().getGrossPerYear("Leonardo DiCaprio")){
            System.out.println(vo.getAttr()+" "+vo.getV1()+" "+vo.getV2());
        }
    }

    @Test
    public void testGetAxisParam()throws Exception{
        for(AxisParamVO vo:(movieDetailService.getAxisParam("Die Hard"))){
            System.out.println(vo.getName()+": min "+vo.getMin()+" max "+vo.getMax()+" avg "+vo.getAvg()+" value "+vo.getValue());
        }
    }

    @Test
    public void testGetReward()throws Exception{
        for(RewardPO po:makerDetailService.getReward("Leonardo DiCaprio")){
            System.out.println(po.getRewardYear()+" "+po.getRewardName()+" "+po.getGetType()+" "+po.getMovieName()+" "+po.getPeopleName());
        }
    }

    @Test
    public void testGetAwardAndNom()throws Exception{
        for(LittleAwardsVO vo:makerDetailService.getAwardAndNom("Leonardo DiCaprio","star")){
            System.out.println(vo.getYear()+" "+vo.getWon()+" "+vo.getNominated());
        }
    }

    @Test
    public void testRadar(){
        Map<String,Double> map=makerDetailService.getTopGenre("Leonardo DiCaprio");
        Iterator<String> iterator=map.keySet().iterator();
        while(iterator.hasNext()){
            String s=iterator.next();
            System.out.println(s+":"+map.get(s));
        }
    }

    @Test
    public void testGetPartner()throws Exception{
        for(PartnerVO vo:makerDetailService.getPartners("Leonardo DiCaprio","star")){
            System.out.println(vo.getTitle()+" "+vo.getName()+" "+vo.getPresentativeMovie()+" "+vo.getPercent()+" "+vo.getMovieImgUrl());
        }
    }
//    @Test
//    public void test()throws Exception{
//        ArrayList<TwoValueChartVO> gross1 = makerDetailService.getGrossPerYear("Leonardo DiCaprio");
//        ArrayList<TwoValueChartVO> gross2 = makerDetailService.getGrossPerYear("Pete Postlethwaite");
//
//        //使用游标标记，把只有一个有的年份填上
//        int star1Itr = 0;
//        int star2Itr = 0;
//        while (star1Itr<gross1.size()||star2Itr<gross2.size()){
//            //把游标指向的小的年份加到另一个列表，并把游标前移
//            int compare = 0;
//            if(star1Itr==gross1.size()){
//                while (star2Itr<gross2.size()){
//                    gross1.add(new TwoValueChartVO(gross2.get(star2Itr).getAttr(),0,0));
//                    star2Itr++;
//                }
//                break;
//            }else if(star2Itr == gross2.size()){
//                while (star1Itr<gross1.size()){
//                    gross2.add(new TwoValueChartVO(gross1.get(star1Itr).getAttr(),0,0));
//                    star1Itr++;
//                }
//                break;
//            }else {
//                compare = gross1.get(star1Itr).compareTo(gross2.get(star2Itr));
//            }
//            if(compare<0){
//                gross2.add(star2Itr,new TwoValueChartVO(gross1.get(star1Itr).getAttr(),0,0));
//            }
//            else if(compare>0){
//                gross1.add(star1Itr,new TwoValueChartVO(gross2.get(star2Itr).getAttr(),0,0));
//            }
//            //由于插入了一个数据，所以另一个游标也要加
//            //相等则一起前移
//            star1Itr++;
//            star2Itr++;
//        }
//
//        JSONArray jsonArray1 = new JSONArray();
//        JSONArray jsonArray2 = new JSONArray();
//
//        for(TwoValueChartVO vo:gross1){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("attr",vo.getAttr());
//            jsonObject.put("v1",vo.getV1());
//            jsonObject.put("v2",vo.getV2());
//            jsonArray1.add(jsonObject);
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("canDraw",true);
//        jsonObject.put("datas",jsonArray1);
//        System.out.println(jsonObject.toJSONString());
//    }


}

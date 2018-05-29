package com.edu.nju.wel.util;

/**
 * Created by ${WX} on 2017/5/8.
 */

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Crawler {
    private static final Pattern MOVIE_NAME=Pattern.compile("<h1 itemprop=\"name\" class=\"(long)?\">(.*?)&nbsp;");

    private static final Pattern MOVIE_IMAGE=Pattern.compile("<img alt=\"(.*?)Poster\" title=\"(.*?)Poster\"\\s*" +
            "src=\"(.*?)\"\\s*" +
            "itemprop=\"image\" />");

    private static final Pattern RATING_VALUE=Pattern.compile("<span itemprop=\"ratingValue\">(.*?)</span>");

    private static final Pattern MOVIE_DIR=Pattern.compile("<span itemprop=\"director\" itemscope itemtype=\"http://schema.org/Person\">\\s*" +
            "<a href=\"(.*?)\"\\s*" +
            "itemprop='url'>");

    private static final Pattern MOVIE_STAR=Pattern.compile("<span itemprop=\"actors\" itemscope itemtype=\"http://schema.org/Person\">\\s*" +
            "<a href=\"(.*?)\"\\s*" +
            "itemprop='url'>");

    private static final Pattern STROYLINE=Pattern.compile("<h2>Storyline</h2>\\s*" +
            "\\s*" +
            "        <div class=\"inline canwrap\" itemprop=\"description\">\\s*" +
            "            <p>\\s*" +
            "(.*?)</p>");

    private static final Pattern GENRES=Pattern.compile("<span class=\"itemprop\" itemprop=\"genre\">(.*?)</span>");

    private static final Pattern COUNTRY=Pattern.compile("<h4 class=\"inline\">Country:</h4>\\s*" +
            "        <a href=\"(.*?)\"\\s*" +
            "itemprop='url'>(.*?)</a>");

    private static final Pattern LANGUAGE=Pattern.compile("<h4 class=\"inline\">Language:</h4>\\s*" +
            "        <a href=\"(.*?)\"\\s*" +
            "itemprop='url'>(.*?)</a>");

    private static final Pattern RELEDATE=Pattern.compile("title=\"See more release dates\" >(.*?)\\s*" +
            "<meta itemprop=\"datePublished\"");

    private static final Pattern BUDGET=Pattern.compile("<h4 class=\"inline\">Budget:</h4>        (.*?)        ");

    private static final Pattern OPENGING_WEEK=Pattern.compile("<h4 class=\"inline\">Opening Weekend:</h4>        (.*?)        ");

    private static final Pattern GROSS=Pattern.compile("<h4 class=\"inline\">Gross:</h4>        (.*?)        ");

    private static final Pattern RUN_TIME=Pattern.compile("<time itemprop=\"duration\" datetime=\"PT(.*?)\">");


    private static final Pattern MAKER_NAME_AND_IMAGE=Pattern.compile("<img id=\"name-poster\"(.*?)" +
            "alt=\"(.*?) Picture\"\\s*" +
            "title=\"(.*?) Picture\"\\s*" +
            "src=\"(.*?)\"\\s*" +
            "itemprop=\"image\" />");

    private static final Pattern FULL_BIO=Pattern.compile("<span class=\"see-more inline nobr-only\">\\s*" +
            "                    "+"<a href=\"(.*?)\"\\s*" +
            ">See full bio</a>");

    private static final Pattern BIRTH=Pattern.compile("<td class=\"label\">Date of Birth</td>\\s*" +
            "    <td>\\s*" +
            "    (.*?)    </td>");

    private static final Pattern HEIGHT=Pattern.compile("<td class=\"label\">Height</td>\\s*" +
            "    <td>\\s*" +
            "(.*?)    </td>");

    private static final Pattern BIO=Pattern.compile("Mini Bio ...</h4>\\s*" +
            "        <div class=\"soda odd\">\\s*" +
            "          <p>\\s*" +
            "(.*?)</p>");

    private static final Pattern PERSON_REVIEW=Pattern.compile("<div>\\s*<small>(.*?) out of (.*?) people found the following review useful:</small><br>\\s*" +
            "<a href=\"(.*?)\"><img class=\"avatar\" src=\"(.*?)\" height=(.*?) width=(.*?)></a>\\s*" +
            "<h2>(.*?)</h2>\\s*" +
            "(<img width=\"102\" height=\"12\" alt=\"(.*?)\" src=\"(.*?)\"><br>)?\\s*" +
            "<b>Author:</b>\\s*" +
            "<a href=\"(.*?)\">(.*?)</a>( <small>(.*?)</small>)?<br>\\s*" +
            "<small>(.*?)</small><br>\\s*" +
            "(.*?)\\s*" +
            "\\s*" +
            "<div class=\"yn\"");

    private static  final Pattern FIND_FIRST=Pattern.compile("<table class=\"findList\">\\s*" +
            "<tr class=\"findResult odd\">\\s*<td class=\"primary_photo\">\\s*<a href=\"/title/(.*?)/\\?ref_=fn_al_tt_1\" ><img");

    private static  final Pattern FIND_FIRST_NAME=Pattern.compile("<table class=\"findList\">\\s*" +
            "<tr class=\"findResult odd\">\\s*<td class=\"primary_photo\">\\s*<a href=\"/name/(.*?)/\\?ref_=fn_nm_nm_1\" ><img");

    private static final  Pattern PAGE_NUM=Pattern.compile("Page 1 of (.*?):");

    private static final Pattern BEST_OSCAR_MOVIE=Pattern.compile("<h1>Oscar</h1>\\s*" +
            "  <blockquote>\\s*" +
            "      \\s*" +
            "\\s*" +
            "<h2>(.*?)</h2>\\s*" +
            "<blockquote>\\s*" +
            "<h3>WINNER</h3>\\s*" +
            "<div class='alt'>\\s*" +
            "\\s*" +
            "<div style=\"float:left;height:32px;width:25px;\"><a href=\".*?\"><img src=\".*?\" border='0' /></a></div>\\s*" +
            "<strong>\\s*" +
            "<a  href=\".*?\" >(.*?)</a>: </strong>");

    private static final Pattern OSCAR_NORMAL=Pattern.compile("<h2>(.*?)</h2>\\s*" +
            "<blockquote>\\s*" +
            "<h3>WINNER</h3>\\s*" +
            "<div class='alt'>\\s*" +
            "\\s*" +
            "<div style=\".*?\"><a href=\".*?\"><img src=\".*?\" border='0' /></a></div>\\s*" +
            "(.*?)</div><h3>NOMINEES</h3>\\s*" +
            "<div class='.*?'>\\s*" +
            "\\s*" +
            "<div style=\".*?\"><a href=\".*?\"><img src=\".*?\" border='0' /></a></div>\\s*" +
            "(.*?)</div><hr style=\".*?\"><div class='.*?'>\\s*" +
            "\\s*" +
            "<div style=\".*?\"><a href=\".*?\"><img src=\".*?\" border='0' /></a></div>\\s*" +
            "(.*?)</div><hr style=\".*?\"><div class='.*?'>\\s*" +
            "\\s*" +
            "<div style=\".*?\"><a href=\".*?\"><img src=\".*?\" border='0' /></a></div>\\s*" +
            "(.*?)</div><hr style=\".*?\"><div class='.*?'>\\s*" +
            "\\s*" +
            "<div style=\".*?\"><a href=\".*?\"><img src=\".*?\" border='0' /></a></div>\\s*" +
            "(.*?)</div></blockquote>");

    private static final Pattern COMPANGS=Pattern.compile("<h4 class=\"inline\">Production Co:</h4>\\s*" +
            "        <span itemprop=\"creator\" itemscope itemtype=\"http://schema.org/Organization\">\\s*" +
            "(.*?)</span>      <span class=\"see-more inline\">\\s*" +
            "        <a href=\".*?\"\\s*" +
            "itemprop='url'>See more</a>");

    private static final Pattern REVIEWNUM=Pattern.compile("<a href=\"/title/.*?/reviews\\?ref_=tt_urv\"\\s*" +
            ">See all (.*?) user reviews</a>");

    private static final Pattern SMALL_AWARDS=Pattern.compile("<td class=\"award_year\" align=\"center\" rowspan=\"1\">\\s*" +
            "<a href=\".*?\"\\s*" +
            "> (.*?)\\s*" +
            "</a>\\s*</td>\\s*" +
            "        <td class=\"award_outcome\" rowspan=\"1\">\\s*" +
            "            <b>(.*?)</b><br />");

    private final static String[] agents={"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_8; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
            "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0",
            "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0)",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1",
            "Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; TencentTraveler 4.0)",
            "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)"
    };

    private static Random random=new Random();

    private final static Pattern MOST_RATING=Pattern.compile("</span>\\s*" +
            "<a href=\"(.*?)\"\\s*" +
            "> <img src=\"(.*?)\" width=\"45\" height=\"67\"/>\\s*" +
            "</a>    </td>");

    public static void main(String[] args) {
//        StringBuilder page;
//        Matcher matcher;
//        LittleAwardsPO littleAwardsPO;
//        page=getPage("http://www.imdb.com/name/nm0000154/awards");
//        Map<Integer,WonAndN> map=new HashMap();
//        matcher=SMALL_AWARDS.matcher(page);
//        littleAwardsPO=new LittleAwardsPO();
//        littleAwardsPO.setName("Mel Gibson");
//        littleAwardsPO.setType("star");
//        while(matcher.find()){
//            if(!map.containsKey(Integer.valueOf(matcher.group(1)))){
//                map.put(Integer.valueOf(matcher.group(1)),new WonAndN());
//            }
//            if(matcher.group(2).equals("Nominated"))
//                map.get(Integer.valueOf(matcher.group(1))).nominated++;
//            else
//                map.get(Integer.valueOf(matcher.group(1))).won++;
//        }
//        Iterator<Integer> itr=map.keySet().iterator();
//        WonAndN wonAndN;
//        while(itr.hasNext()){
//            int i=itr.next();
//            wonAndN=map.get(i);
//            littleAwardsPO.setYear(i);
//            littleAwardsPO.setWon(wonAndN.won);
//            littleAwardsPO.setNominated(wonAndN.nominated);
//            DAOManager.littleAwardsDao.add(littleAwardsPO);
//        }

//            List<LittleAwardsPO> littleAwardsPOS=DAOManager.littleAwardsDao.getByName("Mel Gibson","star");
//        for (LittleAwardsPO po:
//             littleAwardsPOS) {
//            DAOManager.littleAwardsDao.delete(po);
//        }

//        List<MakerDetailPO> list=Persistence.getAllMaker("creator");
//        for (MakerDetailPO po:
//             list) {
//            List<MakerDetailPO> makerDetailPOS=DAOManager.makerDetailDao.modify(po.getName(),po.getType());
//            for(int i=1;i<makerDetailPOS.size();i++){
//                DAOManager.makerDetailDao.delete(makerDetailPOS.get(i));
//            }
//        }
        //getMaker("http://www.imdb.com/name/nm0515950/?ref_=tt_ov_st_sm","","star");

//        StringBuilder page=getPage("http://www.imdb.com/chart/top/?ref_=nv_mv_250_6");
//        Matcher matcher=MOST_RATING.matcher(page);
//        //int count=0;
//        int i=0;
//        while(matcher.find()){
//            i++;
//            if(i<229)
//                continue;
//            System.out.println(i);
//            //System.out.println("http://www.imdb.com"+matcher.group(1));
//            //count++;
//            page=getPage("http://www.imdb.com"+matcher.group(1));
//            if (page==null)
//                continue;
//            Matcher matcher1;
//            matcher1=MOVIE_STAR.matcher(page);
//            while(matcher1.find()){
//                getMaker("http://www.imdb.com"+matcher1.group(1),"","star");
//            }
//
//        }

        //getMostPopular();

        getMovie("http://www.imdb.com/title/tt1790809/");

    }

    /**
     *删除数据库获奖信息的一些错误信息
     *
     */
    private static void deleteTag(){
        List<RewardPO> list=DAOManager.rewardDao.getAll();
        for (RewardPO po:
                list) {
            if (po.getRewardType().contains("</h2>")){
                po.setRewardType(po.getRewardType().split("</h2>")[0]);
                //System.out.println(po.getRewardType());
                DAOManager.rewardDao.update(po);
            }

        }
    }


    /**
     * 获得每个明星的一些杂项获奖
     * @param type
     */
    public static void getLittleAwards(String type){
        List<MakerDetailPO> list=DAOManager.makerDetailDao.getAll(type);
        int remain=list.size();
        String id;
        String find="http://www.imdb.com/find?ref_=nv_sr_fn&q=*&s=nm";
        StringBuilder page;
        Matcher matcher;
        LittleAwardsPO littleAwardsPO;
        for (MakerDetailPO maker:
             list) {
            System.out.println("remain:"+remain);
            remain--;
            if(DAOManager.littleAwardsDao.getByName(maker.getName(),maker.getType()).size()>0)
                continue;
//            if(remain>881)
//                continue;
            id=null;
            page=getPage(find.replace("*",maker.getName().replaceAll(" ","+")));
            if(page==null)
                continue;
            matcher=FIND_FIRST_NAME.matcher(page);
            if(matcher.find())
                id=matcher.group(1);
            if (id==null)
                continue;
            page=getPage("http://www.imdb.com/name/"+id+"/awards/");
            if(page==null)
                continue;
            Map<Integer,WonAndN> map=new HashMap();
            matcher=SMALL_AWARDS.matcher(page);
            littleAwardsPO=new LittleAwardsPO();
            littleAwardsPO.setName(maker.getName());
            littleAwardsPO.setType(maker.getType());
            while(matcher.find()){
                if(!map.containsKey(Integer.valueOf(matcher.group(1)))){
                    map.put(Integer.valueOf(matcher.group(1)),new WonAndN());
                }
                if(matcher.group(2).equals("Nominated"))
                    map.get(Integer.valueOf(matcher.group(1))).nominated++;
                else
                    map.get(Integer.valueOf(matcher.group(1))).won++;
            }
            Iterator<Integer> itr=map.keySet().iterator();
            WonAndN wonAndN;
            while(itr.hasNext()){
                int i=itr.next();
                wonAndN=map.get(i);
                littleAwardsPO.setYear(i);
                littleAwardsPO.setWon(wonAndN.won);
                littleAwardsPO.setNominated(wonAndN.nominated);
                DAOManager.littleAwardsDao.add(littleAwardsPO);
            }
        }
    }

    public static void getMostPopular(){
        StringBuilder page=getPage("http://www.imdb.com/chart/moviemeter?ref_=nv_mv_mpm_8");
        Matcher matcher=MOST_RATING.matcher(page);
        //int count=0;
        while(matcher.find()){
            //System.out.println("http://www.imdb.com"+matcher.group(1));
            //count++;
           getMovie("http://www.imdb.com"+matcher.group(1));
        }
    }

    /**
     * 获得电影的公司
     */
    public static void getMovieCompany(){
        List<MovieData> list=DAOManager.movieDataDao.getAll();
        String id;
        for (MovieData data:
             list) {
            if(data.getCompany()!=null)
                continue;
            id=null;
            String find="http://www.imdb.com/find?ref_=nv_sr_fn&q=*&s=all";
            StringBuilder page;
            page=getPage(find.replace("*",data.getName().replaceAll(" ","+")));
            if(page==null)
                continue;

            Matcher matche;
            matche=FIND_FIRST.matcher(page);
            if(matche.find())
                id=matche.group(1);
            if(id==null)
                continue;
            page=getPage("http://www.imdb.com/title/"+id+"/");
            if(page==null)
                continue;
            matche=COMPANGS.matcher(page);
            //System.out.println("done");
            if(matche.find()){
                data.setCompany(DelTag.delHTMLTag(matche.group(1)));
                DAOManager.movieDataDao.update(data);
            }

            if(data.getPersonReview()!=0)
                continue;

            matche=REVIEWNUM.matcher(page);
            if(matche.find()) {
                try {
                    data.setPersonReview(Integer.valueOf(matche.group(1).replace(",","")));
                    DAOManager.movieDataDao.update(data);
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }

        }
    }
    /**
     * 用于获得获奖电影的方法
     */
    public static void getRewardMovies(){
        List<RewardPO> list=DAOManager.rewardDao.getAll();
        String id;
        for(int i=0;i<list.size();i++){
            id=null;
            String find="http://www.imdb.com/find?ref_=nv_sr_fn&q=*&s=all";
            StringBuilder page;
            page=getPage(find.replace("*",list.get(i).getMovieName().replaceAll(" ","+")));
            if(page==null)
                continue;

            Matcher matche;
            matche=FIND_FIRST.matcher(page);
            if(matche.find())
                id=matche.group(1);
            if(id==null)
                continue;
            getMovie("http://www.imdb.com/title/"+id+"/");
//            System.out.println(list.get(i).getMovieName());
//            System.out.println("http://www.imdb.com/title/"+id);

        }
    }

    /**
     * 用于爬取1960-2017的global电影奖项
     *
     *
     */

    public static void getGlobal(){
        String s="http://www.imdb.com/event/ev0000292/*";
        for(int i=1960;i<2017;i++){
            StringBuilder builder = getPage(s.replace("*",String.valueOf(i)));
            if(builder==null)
                continue;
            Matcher matcher = OSCAR_NORMAL.matcher(builder);
            RewardPO po=new RewardPO();
            po.setRewardYear(i);
            po.setRewardName("global");
            int j=0;
            while(matcher.find()&&j<11){
                if (j==0) {
                    try {
                        po.setRewardType(matcher.group(1).split("<h2>")[1]);
                    } catch (Exception e) {
                        continue;
                    }
                }else {
                    po.setRewardType(matcher.group(1));
                }
                for(int x=2;x<7;x++) {
                    if (x!=2)
                        po.setGetType("nomiees");
                    else
                        po.setGetType("winnner");

                    if(j<4){
                        po.setMovieName(DelTag.delHTMLTag(matcher.group(x)));
                        DAOManager.rewardDao.add(po);
                        continue;
                    }else{
                        String[] res=DelTag.delHTMLTag(matcher.group(x)).split(":");
                        try {
                            if(res.length>2){
                                res[0]=res[0]+":"+res[1];
                                res[1]=res[2];
                            }
                            po.setMovieName(res[0]);
                            po.setPeopleName(res[1]);
                            DAOManager.rewardDao.add(po);
                            continue;
                        }catch (Exception e){
                            e.printStackTrace();
                            continue;
                        }
                    }
                }
                j++;
            }
        }
    }
    /**
     * 用于爬取1960-2017年oscar的获奖信息
     *
     *
     */

    public static void getOscar(){
        String s="http://www.imdb.com/event/ev0000003/*";
        for (int i=1960;i<2018;i++) {
            StringBuilder builder = getPage(s.replace("*",String.valueOf(i)));
            if(builder==null)
                continue;
            Matcher matcher = OSCAR_NORMAL.matcher(builder);
            RewardPO po=new RewardPO();
            po.setRewardYear(i);
            po.setRewardName("oscar");
            int j=0;
            while(j<8&&matcher.find()){
                po.setRewardType(matcher.group(1));
                if (j>0){
                    for(int x=2;x<7;x++){
                        if (x!=2)
                            po.setGetType("nomiees");
                        else
                            po.setGetType("winnner");
                        String[] res=DelTag.delHTMLTag(matcher.group(x)).split(":");
                        try {
                            if(res.length>2){
                                res[0]=res[0]+":"+res[1];
                                res[1]=res[2];
                            }
                            po.setMovieName(res[0]);
                            po.setPeopleName(res[1]);
                            DAOManager.rewardDao.add(po);
                        }catch (Exception e){
                            e.printStackTrace();
                            continue;
                        }

                    }
                }
                j++;
            }
            matcher=BEST_OSCAR_MOVIE.matcher(builder);
            if(matcher.find()){
                po.setRewardType(matcher.group(1));
                po.setMovieName(DelTag.delHTMLTag(matcher.group(2)));
                po.setGetType("winner");
                DAOManager.rewardDao.add(po);
            }
        }
    }
    /**
    * 用户获取电影的评论，每部电影100条，包括该电影的评论总条数
    *
    *
    *
    * */
    public static void getReview(){
        String find="http://www.imdb.com/find?ref_=nv_sr_fn&q=*&s=all";
        StringBuilder page;
        List<MovieData> list=DAOManager.movieDataDao.getAll();
        String id;
        int num;
        for(int j=0;j<list.size();j++) {
            if(list.get(j).getPersonReview()!=0)
                continue;
            id=null;
            page=getPage(find.replace("*",list.get(j).getName().replaceAll(" ","+")));
            if(page==null)
                continue;

            Matcher matche;
            matche=FIND_FIRST.matcher(page);
            if(matche.find())
               id=matche.group(1);
            if(id==null)
               continue;
            String review="http://www.imdb.com/title/*1/reviews?start=*2";
            review=review.replace("*1",id);
            review=review.replace("*2","0");
            //System.out.println(review);
            page=getPage(review);
            if(review==null)
                continue;
            matche=PAGE_NUM.matcher(page);
            matche.find();
            num=Integer.valueOf(matche.group(1))*10;
            list.get(j).setPersonReview(num);

            DAOManager.movieDataDao.update(list.get(j));
            if (num>10)
                num=10;

            for(int x=0;x<num;x++) {
                int m=x*10;

                String url="http://www.imdb.com/title/"+id+"/reviews?start=*";

                url=url.replace("*",String.valueOf(m));

                page=getPage(url);

                if(page==null)
                   continue;

                matche=PERSON_REVIEW.matcher(page);

                while (matche.find()) {
                    MovieReviewPO po=new MovieReviewPO();
                    po.setMoviename(list.get(j).getName());
                    po.setUseful(Integer.valueOf(matche.group(1)));
                    po.setAll(Integer.valueOf(matche.group(2)));
                    po.setTitel(matche.group(7));
                    po.setRating(matche.group(9));
                    String name=matche.group(12)+" "+matche.group(14);
                    po.setAuthor(name);
                    po.setTime(matche.group(15));
                    po.setContent(DelTag.delHTMLTag(matche.group(16)));
                    //System.out.println(po.getAuthor());
                    try {
                        DAOManager.movieReviewDao.add(po);
                    }catch (Exception e){
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        }
    }
/**
* 用于获取imdb上评分排名前250 的电影信息
*
*
*
*
* */
    private static void getTopFive(){
        StringBuilder page=getPage("http://www.imdb.com/chart/top/?ref_=nv_mv_250_6");
        Matcher matcher=MOST_RATING.matcher(page);
        //int count=0;
        while(matcher.find()){
            //System.out.println("http://www.imdb.com"+matcher.group(1));
            //count++;
            getMovie("http://www.imdb.com"+matcher.group(1));
        }
    }




/**
* 用于获取HTML文件
*
* */
    private static StringBuilder getPage(String path){
        StringBuilder sb = new StringBuilder();
        try {
            Thread.sleep(800);
        }catch (Exception e){}

        try{
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("User-Agent", agents[random.nextInt(agents.length-1)]);
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while((line = in.readLine()) != null){
                sb.append(line);
            }
            connection.disconnect();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return sb;
    }
/**
* 用于获得单部电影的详情
*
* */
    private static void getMovie(String url){
        StringBuilder page=getPage(url);
        if(page==null)
            return;
        MovieDetailPO po=new MovieDetailPO();
        MovieData data=new MovieData();
        Matcher matcher=MOVIE_NAME.matcher(page);
        if (matcher.find()){
            if(DAOManager.movieDetailDao.getByName(matcher.group(2))!=null)
                return;
            po.setName(matcher.group(2));
            //System.out.println(po.getName());
        }else{
            return;
        }
        matcher=MOVIE_IMAGE.matcher(page);
        if(matcher.find()){
            po.setImageUrl(matcher.group(3));
        }
        matcher=MOVIE_DIR.matcher(page);
        while(matcher.find()){
            getMaker("http://www.imdb.com"+matcher.group(1),po.getName(),"creator");
        }
        matcher=RATING_VALUE.matcher(page);
        if(matcher.find()){
            po.setRatingValue(Double.valueOf(matcher.group(1)));
        }else{
            po.setRatingValue(0);
        }
        matcher=MOVIE_STAR.matcher(page);
        while(matcher.find()){
            getMaker("http://www.imdb.com"+matcher.group(1),po.getName(),"star");
        }
        matcher=STROYLINE.matcher(page);
        if(matcher.find()){
            po.setStoryLine(DelTag.delHTMLTag(matcher.group(1)));
        }
        matcher=GENRES.matcher(page);
        String tags="";
        while(matcher.find()){
           tags+=matcher.group(1)+" ";
        }
        if(tags.equals(""))
            tags=null;
        po.setGenres(tags);

        matcher=COUNTRY.matcher(page);
        if(matcher.find()){
            po.setCountry(matcher.group(2));
        }
        matcher=LANGUAGE.matcher(page);
        if(matcher.find()){
            po.setLanguage(matcher.group(2));
        }
        matcher=RELEDATE.matcher(page);
        if(matcher.find()){
            po.setReleDate(matcher.group(1));
        }
        matcher=BUDGET.matcher(page);
        if(matcher.find()){
            po.setBudget(matcher.group(1));
        }
        matcher=OPENGING_WEEK.matcher(page);
        if(matcher.find()){
            po.setOpeningWeek(matcher.group(1));
        }
        matcher=GROSS.matcher(page);
        if(matcher.find()){
           po.setGross(matcher.group(1));
        }
        matcher=RUN_TIME.matcher(page);
        if (matcher.find()){
            po.setRunTime(matcher.group(1));
        }
        data.setName(po.getName());
        data.setPerfessionReview(0);
        data.setPersonReview(0);
//        System.out.println(po.getName());
        try {
            DAOManager.movieDataDao.add(data);
            DAOManager.movieDetailDao.add(po);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
/**
* 用于获得导演和明星的详情
*
* */
    private static void getMaker(String url,String movieName,String type){
        StringBuilder page=getPage(url);
        if(page==null)
            return;
        MakerDetailPO po=new MakerDetailPO();
        po.setType(type);
        Matcher matcher=MAKER_NAME_AND_IMAGE.matcher(page);
        if(matcher.find()){
            if(DAOManager.makerDetailDao.getByName(matcher.group(3))!=null){
                if(DAOManager.makerDetailDao.modify(matcher.group(3),type).size()>0) {
                    try {
                        DAOManager.maker2MovieDao.add(new Creator2Movie(matcher.group(3), movieName));
                    }catch (Exception e){
                        e.printStackTrace();
                        return;
                    }
                        return;
                }
            }
            po.setName(matcher.group(3));
            po.setImageUrl(matcher.group(4));
        }else{
            return;
        }
        matcher=FULL_BIO.matcher(page);
        if(matcher.find()){
            //System.out.println(matcher.group(1));
            page=getPage("http://www.imdb.com"+matcher.group(1));
            if(page==null){
                try {
                    DAOManager.makerDetailDao.add(po);
                    DAOManager.maker2MovieDao.add(new Creator2Movie(po.getName(), movieName));
                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }
                return;
            }
        }else{
            try {
                DAOManager.makerDetailDao.add(po);
                DAOManager.maker2MovieDao.add(new Creator2Movie(po.getName(), movieName));
            }catch (Exception e){
                e.printStackTrace();
                return;
            }
            return;
        }
        matcher=BIRTH.matcher(page);
        if (matcher.find()){
            po.setBirthday(DelTag.delHTMLTag(matcher.group(1)));
        }
        matcher=HEIGHT.matcher(page);
        if(matcher.find()){
            po.setHeight(DelTag.delHTMLTag(matcher.group(1)));
        }
        matcher=BIO.matcher(page);
        if (matcher.find()){
            po.setBio(DelTag.delHTMLTag(matcher.group(1)));
        }
        try {
            DAOManager.makerDetailDao.add(po);
            DAOManager.maker2MovieDao.add(new Creator2Movie(po.getName(), movieName));
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

}

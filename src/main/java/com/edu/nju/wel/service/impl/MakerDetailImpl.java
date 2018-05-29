package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by qianzhihao on 2017/5/11.
 */
@Service("MakerDetailService")
public class MakerDetailImpl implements MakerDetailService{
    private static ArrayList<String> nationalities;
    //记录已经有过的国籍以及数目
    private static HashMap<String,Integer> nationalityMap;
    //不超过该数目的国籍归入其他中
    private static final int NATIONALITY_MIN_NUM = 20;

    static {
        if(nationalities==null){
            nationalities = new ArrayList<>();
            nationalityMap = new HashMap<>();

            addToMap("star");
            addToMap("creator");

            for(Map.Entry<String,Integer> entry:nationalityMap.entrySet()){
                if(entry.getValue()>NATIONALITY_MIN_NUM){
                    nationalities.add(entry.getKey());
                }
            }

            nationalities.add(0,"All");
            nationalities.add("Others");
        }
    }

    @Override
    public MakerDetailVO getMakerDetail(String makerName) {
        return new MakerDetailVO(DAOManager.makerDetailDao.getByName(makerName));
    }

    @Override
    public ArrayList<ListVO> getStarList(String keyword, String nationality) {
        return getMakerList(keyword,nationality,"star");
    }

    @Override
    public ArrayList<ListVO> getCreatorList(String keyword, String nationality) {
        return getMakerList(keyword,nationality,"creator");
    }

    @Override
    public Iterator<String> getMovies(String makerName) {
        return DAOManager.makerDetailDao.getMovies(makerName);
    }

    @Override
    public ArrayList<String> getNationalities() {
        return nationalities;
    }

    @Override
    public ArrayList<TwoValueChartVO> getGrossPerYear(String makerName) {
        Iterator<String> movieIterator = DAOManager.makerDetailDao.getMovies(makerName);
        ArrayList<MovieDetailPO> movies = new ArrayList<>();

        String movieName;
        while (movieIterator.hasNext()){
            movieName = movieIterator.next();
            MovieDetailPO po = DAOManager.movieDetailDao.getByName(movieName);
            movies.add(po);
        }
        return MoviesGrossPerYear.getGrossPerYear(movies);
    }

    @Override
    public List<RewardPO> getReward(String makerName) {
        List<RewardPO> pos = DAOManager.rewardDao.getByPeopleName(makerName);
        Collections.sort(pos);
        for(RewardPO po:pos){
            po.setPeopleName(po.getPeopleName().replaceAll("\n(.*)","").replaceAll("^ ",""));
        }
        return pos;
    }

    @Override
    public Map<String, Double> getTopGenre(String makerName) {
        return getTops(makerName);
    }

    @Override
    public ArrayList<LittleAwardsVO> getAwardAndNom(String makerName,String type) {
        ArrayList<LittleAwardsVO> res = new ArrayList<>();
        for(LittleAwardsPO po:DAOManager.littleAwardsDao.getByName(makerName,type)){
            res.add(new LittleAwardsVO(po));
        }
        return res;
    }

    @Override
    public ModelAndView makerSearch(String viewName, String keyword, String nationality, String type, String page) {
            ModelAndView maker = new ModelAndView(viewName);
            ArrayList<ListVO> makerList;
            if(type==null||type.equals("star"))
                makerList = getStarList(keyword==null?"":keyword,nationality==null?"All":nationality);
            else
                makerList = getCreatorList(keyword==null?"":keyword,nationality==null?"All":nationality);

            int index;
            if(page==null){
                index = 1;
            }else {
                index = Integer.valueOf(page);
            }
            maker.addObject("numOfPages",makerList.size()% ItemOfPageNum.FORECAST ==0?makerList.size()/ItemOfPageNum.FORECAST :makerList.size()/ItemOfPageNum.FORECAST +1);
            maker.addObject("nowPage",index);

            maker.addObject("makers", ItemOfPageNum.getPageItem(index,ItemOfPageNum.FORECAST,makerList));

            //删除all标签
            ArrayList<String> genres = StatisticServiceImpl.allGenres();
            genres.remove("All");
            maker.addObject("genres",genres);
            maker.addObject("nationalities",getNationalities());
            return maker;
    }

    @Override
    public Map<String, Double> getMakerRadarMap(String name, String type) {
        Map<String,Double> res=new HashMap<>();
        double sum=0;
        double value;
        Iterator<String> iterator;
        iterator=DAOManager.makerDetailDao.getMovies(name);
        while(iterator.hasNext()){
            sum+=GrossToInt.toInt(DAOManager.movieDetailDao.getByName(iterator.next()).getGross());
        }
        if (sum>DAOManager.coefficientDao.getByName("maxGross"))
            value=10;
        else
            value=sum/DAOManager.coefficientDao.getByName("maxGross")*10;
        res.put("Gross",value);
        sum=0;
        iterator=DAOManager.makerDetailDao.getMovies(name);
        while (iterator.hasNext()){
            sum+=DAOManager.movieReviewDao.getPersonalReviewNum(iterator.next());
        }
        if(sum>DAOManager.coefficientDao.getByName("maxReview"))
            value=10;
        else
            value=sum/DAOManager.coefficientDao.getByName("maxReview")*10;
        res.put("review",value);
        List<LittleAwardsPO> littleAwardsPOS=DAOManager.littleAwardsDao.getByName(name,type);
        Iterator<LittleAwardsPO> littleAwardsPOIterator=littleAwardsPOS.iterator();
        LittleAwardsPO littleAwardsPO;
        sum=0;
        while(littleAwardsPOIterator.hasNext()){
            littleAwardsPO=littleAwardsPOIterator.next();
            sum+=(littleAwardsPO.getWon()+littleAwardsPO.getNominated()*0.5);
        }
        if (sum>DAOManager.coefficientDao.getByName("maxAwards"))
            value=10;
        else
            value=sum/DAOManager.coefficientDao.getByName("maxAwards")*10;
        res.put("awards",value);
        res.put("average",(DAOManager.averageDao.getByName(name,type).getAve()-DAOManager.coefficientDao.getByName("minAverage"))
                /(DAOManager.coefficientDao.getByName("maxAverage")-DAOManager.coefficientDao.getByName("minAverage"))*10);
        iterator=DAOManager.makerDetailDao.getMovies(name);
        ArrayList<String> stringArrayList=new ArrayList<>();
        String[] tags;
        while(iterator.hasNext()){
            tags=DAOManager.movieDetailDao.getByName(iterator.next()).getGenres().split(" ");
            for(int i=0;i<tags.length;i++){
                if(!stringArrayList.contains(tags[i]))
                    stringArrayList.add(tags[i]);
            }
        }
        sum=stringArrayList.size();
        if (sum>DAOManager.coefficientDao.getByName("maxTag"))
            value=10;
        else
            value=sum/DAOManager.coefficientDao.getByName("maxTag")*10;
        res.put("tags",value);

        return res;
    }

    @Override
    public ArrayList<PartnerVO> getPartners(String name, String type) {
        Iterator<String> movieIterator = DAOManager.makerDetailDao.getMovies(name);
        HashMap<String, PartnerMovieData> ratingMap = new HashMap<>();
        while (movieIterator.hasNext()) {
            MovieDetailPO po = DAOManager.movieDetailDao.getByName(movieIterator.next());
            //若评分为0则不予操作
            if (po.getRatingValue() != 0) {
                Iterator<String> partnerIterator = null;
                //若是演员，则partner为导演
                if (type.equals("star")) {
                    partnerIterator = DAOManager.movieDetailDao.getCreators(po.getName());
                }else {
                    partnerIterator = DAOManager.movieDetailDao.getStars(po.getName());
                }

                while(partnerIterator.hasNext()){
                    String partner = partnerIterator.next();
                    if(ratingMap.get(partner)!=null){
                        ratingMap.get(partner).addMovie(po.getRatingValue(),po.getName(),po.getImageUrl());
                    }else {
                        PartnerMovieData partnerMovieData = new PartnerMovieData();
                        partnerMovieData.addMovie(po.getRatingValue(),po.getName(),po.getImageUrl());
                        ratingMap.put(partner,partnerMovieData);
                    }
                }
            }
        }
        //比较得出最高分和最低分
        double maxAvg = 0;
        double minAvg = 10;
        String bestPartnerName = null;
        String worstPartnerName = null;
        PartnerMovieData bestPartner = null;
        PartnerMovieData worstPartner = null;

        for (Map.Entry<String, PartnerMovieData> entry : ratingMap.entrySet()) {
            double avg = entry.getValue().getAvg();
            if (avg > maxAvg) {
                maxAvg = avg;
                bestPartner = entry.getValue();
                bestPartnerName = entry.getKey();
            }
            if (avg < minAvg) {
                minAvg = avg;
                worstPartner = entry.getValue();
                worstPartnerName = entry.getKey();
            }
        }

        ArrayList<PartnerVO> res = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("#.00");

        double makerAvg = DAOManager.averageDao.getByName(name, type).getAve();

        if(makerAvg<maxAvg) {
            if (bestPartner != null) {
                double percent = (bestPartner.getAvg() - makerAvg) / makerAvg;
                String imgUrl = DAOManager.makerDetailDao.getByName(bestPartnerName).getImageUrl();
                res.add(new PartnerVO(bestPartnerName, imgUrl, "Best Partner", df.format(percent * 100) + "%", bestPartner.getMaxMovie(), bestPartner.getMaxMovieImgUrl()));
            }
        }
        if(makerAvg>minAvg) {
            if (bestPartner != null) {
                double percent = (worstPartner.getAvg() - makerAvg) / makerAvg;
                String imgUrl = DAOManager.makerDetailDao.getByName(worstPartnerName).getImageUrl();
                res.add(new PartnerVO(worstPartnerName, imgUrl, "Worst Partner", df.format(percent * 100) + "%", worstPartner.getMinMovie(), worstPartner.getMinMovieImgUrl()));
            }
        }

        return res;
    }

    public static Map<String, Double> getTops(String makerName){
        List<MovieDetailPO> list=new ArrayList<MovieDetailPO>();
        Iterator<String> itr=DAOManager.makerDetailDao.getMovies(makerName);
        HashMap<String,AverageCounter> map;
        while (itr.hasNext()){
            list.add(DAOManager.movieDetailDao.getByName(itr.next()));
        }
        map=TagAverage.countTagAverage(list);
        if (map.size()<3)
            return null;
        Map<String,Double> res=new HashMap<>();
        Set<String> set=map.keySet();
        itr=set.iterator();
        String key;
        while (itr.hasNext()){
            key=itr.next();
            res.put(key,map.get(key).count());
        }
//        if(res.size()<7)
//            return res;
//        List<Map.Entry<String,Double>> list1 =
//                new ArrayList<>(res.entrySet());
//
//        Collections.sort(list1, new Comparator<Map.Entry<String, Double>>() {
//            public int compare(Map.Entry<String, Double> o1,
//                               Map.Entry<String, Double> o2) {
//                return (o2.getValue().compareTo(o1.getValue()));
//            }
//        });
//        res.clear();
//        for(int i=0;i<6;i++){
//            res.put(list1.get(i).getKey(),list1.get(i).getValue());
//        }
        return res;
    }

    /**
     * 方法合并
     * 考虑到演员名特殊性，和为效率考虑，没和电影名的匹配一样用了很复杂的算法，只返回包含关键字的，并把完全一样的放在第一位
     * @param keyword 关键词
     * @param nationality 国籍
     * @return 过滤结果
     */
    private ArrayList<ListVO> getMakerList(String keyword, String nationality,String type){
        List<MakerDetailPO> pos = Persistence.getAllMaker(type);
        ArrayList<ListVO> res = new ArrayList<>();

        for(MakerDetailPO po:pos){
            boolean validNationality = true;

            //若不为all且国籍不符
            if(!nationality.equals("All")&&
                    !nationality.equals(Birthday2Nationality.getNationality(po.getBirthday()))){
                validNationality = false;
            }

            //判断是否是Others标签中内容
            if(nationality.equals("Others")&&(Birthday2Nationality.getNationality(po.getBirthday()).equals("Others")||nationalityMap.get(Birthday2Nationality.getNationality(po.getBirthday()))<NATIONALITY_MIN_NUM)){
                validNationality = true;
            }

            if(validNationality) {
                //将关键字和演员名转成小写
                String kw = keyword.toLowerCase();
                String name = po.getName().toLowerCase();
                //把关键字完全匹配的演员放在第一位
                if (kw.equals(name)) {
                    res.add(0, new ListVO(po));
                }
                //把含有关键字的加入
                else if (name.indexOf(kw) >= 0) {
                    res.add(new ListVO(po));
                }
            }
        }
        return res;
    }

    private static void addToMap(String type){
        for(MakerDetailPO po: Persistence.getAllMaker(type)){
            String n = Birthday2Nationality.getNationality(po.getBirthday());
            if(n.equals("Others")){
                continue;
            }
            if(nationalityMap.get(n)==null) {
                nationalityMap.put(n,1);
            }
            else{
                nationalityMap.put(n,nationalityMap.get(n)+1);
            }
        }
    }
}

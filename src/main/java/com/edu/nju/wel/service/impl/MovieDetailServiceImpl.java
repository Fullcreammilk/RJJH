package com.edu.nju.wel.service.impl;

import com.edu.nju.wel.dao.DAOManager;
import com.edu.nju.wel.dao.MovieDetailDao;
import com.edu.nju.wel.dao.MovieReviewDao;
import com.edu.nju.wel.dao.RewardDao;
import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.MovieDetailService;
import com.edu.nju.wel.service.impl.sortStrategy.ConfromityLevelComparator;
import com.edu.nju.wel.util.AuthornameProcess;
import com.edu.nju.wel.util.ConformityLevel;
import com.edu.nju.wel.util.Money2Int;
import com.edu.nju.wel.util.Persistence;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by qianzhihao on 2017/5/10.
 */
@Service("MovieDetailService")
public class MovieDetailServiceImpl implements MovieDetailService {
    MovieDetailDao movieDetailDao = DAOManager.movieDetailDao;

    MovieReviewDao movieReviewDao = DAOManager.movieReviewDao;

    RewardDao rewardDao = DAOManager.rewardDao;

    public ArrayList<ListVO> getMovies(String keywords, String tag, String sortWay) {
        List<MovieDetailPO> allMovies = Persistence.getAllMovie();
        ArrayList<ListVO> res = new ArrayList<>();
        //针对不同程度符合关键字的电影名建立不同表
        ArrayList<MovieDetailPO>[] resLists = new ArrayList[ConformityLevel.getLevels()];
        for (int i = 0; i < resLists.length; i++) {
            resLists[i] = new ArrayList<MovieDetailPO>();
        }

        //先根据tag进行筛选
        //然后根据符合程度加入不同表中
        for (MovieDetailPO po : allMovies) {
            //是否符合标签
            boolean tagCoincidence = true;
            //若对tag有要求
            if (tag != null) {
                tagCoincidence = false;
                //判断tag是否符合要求
                if (po.getGenres() != null) {
                    //判断是否符合标签内容，若不符合则不添加
                    String[] tags = po.getGenres().split(" ");
                    for (String t : tags) {
                        if (t.equals(tag)) {
                            tagCoincidence = true;
                            break;
                        }
                    }
                }
                //判断是否电影标签为空
                else if (po.getGenres() == null && tag != "Other") {
                    tagCoincidence = false;
                }
                //判断是否为其他标签
                else if (po.getGenres() == null && tag == "Other") {
                    tagCoincidence = true;
                }
            }
            //若符合条件
            if (tagCoincidence) {
                String name = po.getName();
                int level = ConformityLevel.getConformityLevel(keywords, name);
                if (level != -1 && level < 3) {
                    resLists[level].add(po);
                } else if (level >= 3) {
                    resLists[3].add(po);
                }
            }
        }

        //利用反射创建比较器对象
        Comparator<ListVO> comparator = null;
        try {
            Class c = Class.forName("com.edu.nju.wel.service.impl.sortStrategy." + sortWay);
            comparator = (Comparator) c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //整理成VO并对其进行排序
        for (int i = 0; i < resLists.length; i++) {
            ArrayList<ListVO> temp = new ArrayList<>();
            if (i != resLists.length - 1) {
                for (MovieDetailPO po : resLists[i]) {
                    temp.add(new ListVO(po));
                }
                //按要求方法排序
                Collections.sort(temp, comparator);
                //加入结果数组
                res.addAll(temp);
            }
            //将3+的列表进行排序
            if (i == resLists.length - 1 && keywords != null) {
                for (MovieDetailPO po : resLists[i]) {
                    temp.add(new ListVO(po));
                }
                Collections.sort(temp, new ConfromityLevelComparator(keywords));
                res.addAll(temp);
            }
        }

        return res;
    }

    public MovieDetailVO getMovie(String movieName) {
        return new MovieDetailVO(movieDetailDao.getByName(movieName));
    }

    @Override
    public Iterator<String> getStarsName(String movieName) {
        return movieDetailDao.getStars(movieName);
    }

    @Override
    public Iterator<String> getDirectorsName(String movieName) {
        return movieDetailDao.getCreators(movieName);
    }

    @Override
    public ArrayList<RecMovieVO> recMovie(MovieDetailVO movieVO) {
        HashMap<String,RecMovieVO> recMovieMap = new HashMap<>();

        Iterator<String> movieIterator = movieDetailDao.getCreators(movieVO.getName());
        ArrayList<String> movieCreators = new ArrayList<>();
        String temp;
        while (movieIterator.hasNext()) {
            temp = movieIterator.next();
            movieCreators.add(temp);
        }

        movieIterator = movieDetailDao.getStars(movieVO.getName());
        ArrayList<String> movieStars = new ArrayList<>();
        while (movieIterator.hasNext()) {
            temp = movieIterator.next();
            movieStars.add(temp);
        }

        String[] movieGenres = movieVO.getGenres().split(" ");

        for (MovieDetailPO po : Persistence.getAllMovie()) {
            //判断是否是同一部
            if (po.getName().equals(movieVO.getName())) {
                continue;
            }
            RecMovieVO vo = new RecMovieVO(po.getName(), po.getImageUrl());

            //判断是否是可推荐电影，即有任意一项属性相同
            boolean isRec = false;

            //名字符合度,对应数组第3项
            int conformity = ConformityLevel.getConformityLevel(movieVO.getName(), po.getName());
            if (conformity != -1) {
                vo.addRecValue(3, conformity);
                isRec = true;
            }

            //同流派
            if (po.getGenres() != null) {
                for(String genre:movieGenres){
                    if(po.getGenres().indexOf(genre)>=0){
                        //流派，对应数组第2项
                        vo.addRecValue(2,1);
                        isRec = true;
                    }
                }
            }

            if (isRec) {
                recMovieMap.put(po.getName(),vo);
            }

        }

        //去找到这部电影对应的导演和演员的电影
        //演员，对应数组第1项
        //导演，对应数组第0项
        addToRecMovieMap(recMovieMap,movieStars,1);
        addToRecMovieMap(recMovieMap,movieCreators,0);

        ArrayList<RecMovieVO> res = new ArrayList<>();
        for(Map.Entry<String,RecMovieVO> entry:recMovieMap.entrySet()){
            res.add(entry.getValue());
        }
        Collections.sort(res);
        //取出前5项
        ArrayList<RecMovieVO> topFive = new ArrayList<>();
        for(int i=0;i<5&&i<res.size();i++){
            //跳过本身
            if(res.get(i).getName().equals(movieVO.getName())){
                i--;
                continue;
            }
            topFive.add(res.get(i));
        }
        //补充没有图片链接的电影
        for(RecMovieVO vo:res){
            if(vo.getImgUrl()==null){
                vo.setImgUrl(DAOManager.movieDetailDao.getByName(vo.getName()).getImageUrl());
            }
        }

        return topFive;
    }

    @Override
    public List<MovieReviewPO> getPersonReview(String movieName) {
        List<MovieReviewPO> pos = movieReviewDao.find(movieName);
        for (MovieReviewPO po : pos) {
            //处理过长的名字
            po.setAuthor(AuthornameProcess.authornameProcess(po.getAuthor()));
        }
        return pos;
    }

    @Override
    public ArrayList<AxisParamVO> getAxisParam(String movieName) {
        ArrayList<AxisParamVO> res = new ArrayList<>();

        List<MovieDetailPO> movieDetails = Persistence.getAllMovie();
        MovieDetailPO moviepo = movieDetailDao.getByName(movieName);

        //把该电影的分数作为最高分最低分起始比较的基础分
        Datas ratingValueInCountry = new Datas(moviepo.getRatingValue());
        Datas ratingValueInGenre = new Datas(moviepo.getRatingValue());

        String country = moviepo.getCountry();
        String genre = moviepo.getGenres();
        for (MovieDetailPO po : movieDetails) {
            //统计同地区中的信息
            if (po.getCountry() != null && po.getCountry().equals(country)) {
                if(po.getRatingValue()!=0){
                    addToDatas(ratingValueInCountry,po.getRatingValue());
                }
            }
            if (po.getGenres() != null && po.getGenres().equals(genre)&&po.getRatingValue()!=0) {
                if(po.getRatingValue()!=0){
                    addToDatas(ratingValueInGenre,po.getRatingValue());
                }
            }

        }

        DecimalFormat df = new DecimalFormat("0.0");
        if(ratingValueInCountry.max!=ratingValueInCountry.min){
            res.add(new AxisParamVO("Rating Value In Same Country", ratingValueInCountry.min, ratingValueInCountry.max, Double.valueOf(df.format(ratingValueInCountry.sum/ratingValueInCountry.count)), moviepo.getRatingValue()));
        }
        if(ratingValueInGenre.max!=ratingValueInGenre.min){
            res.add(new AxisParamVO("Rating Value In Same Genre",ratingValueInGenre.min,ratingValueInGenre.max,Double.valueOf(df.format(ratingValueInGenre.sum/ratingValueInGenre.count)),moviepo.getRatingValue()));
        }

        return res;
    }

    @Override
    public List<RewardPO> getAward(String movieName) {
        List<RewardPO> pos = rewardDao.getByMovieName(movieName);
        Collections.sort(pos);
        for (RewardPO po : pos) {
            if (po.getPeopleName() != null)
                po.setPeopleName(po.getPeopleName().replaceAll("\n(.*)", "").replaceAll("^ ", ""));
        }
        return pos;
    }

    private void addToRecMovieMap(HashMap<String,RecMovieVO> map,ArrayList<String> makers,int index){
        for(String star:makers){
            Iterator<String> iterator = DAOManager.makerDetailDao.getMovies(star);
            while (iterator.hasNext()){
                String movie = iterator.next();
                if(map.get(movie)==null){
                    //为提高效率，暂时不去找电影对应的imgUrl，等筛选出最高符合度的5部后再添加
                    RecMovieVO vo = new RecMovieVO(movie,null);
                    vo.addRecValue(index,1);
                    map.put(movie,vo);
                }else {
                    map.get(movie).addRecValue(index,1);
                }
            }
        }
    }

    private void addToDatas(Datas datas, double value) {
        if (value > datas.max) {
            datas.max = value;
        }
        if (value < datas.min) {
            datas.min = value;
        }

        datas.sum += value;
        datas.count++;
    }

    private class Datas {
        double max;
        double min;
        double sum;
        int count;

        public Datas(double init) {
            this.max = init;
            this.min = init;
            sum = 0;
            count = 0;
        }
    }

}

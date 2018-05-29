package test;

import com.edu.nju.wel.dao.*;
import com.edu.nju.wel.model.*;
import com.edu.nju.wel.service.CompanyDetailService;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.UserService;
import com.edu.nju.wel.service.impl.CompanyDetailImpl;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import com.edu.nju.wel.util.Persistence;
import com.edu.nju.wel.util.ToBigImage;
import org.junit.Test;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ${WX} on 2017/4/15.
 */
@Controller
public class hqltest {
    @Resource(name = "UserService")
    UserService userService;

    CompanyDetailService companyDetailService = new CompanyDetailImpl();

    @Test
    public void test1() {
        UserDao userDao = DAOManager.userDao;
//        userDao.find("hyx");
//        MovieReviewDao movieReviewDao=DAOManager.movieReviewDao;
//        movieReviewDao.find();
        User u = new User();
        u.setName("gmd");
//        u.setAge(2);
        userDao.add(u);
    }

//    @Test
//    public void test2(){
//       // userService= (UserServiceImpl) ApplicationContextHelper.getApplicationContext().getBean("UserService");
//        List<User> list = userService.find();
//        for (User u:
//             list) {
//            System.out.println(u.getId());
//
//        }
//    }

    @Test
    public void test3() {
        MovieReviewDao dao = DAOManager.movieReviewDao;
        dao.find("b");
    }

    @Test
    public void test4() {
        MovieDetailDao dao = DAOManager.movieDetailDao;
        List<MovieDetailPO> list = dao.getAll();
        for (MovieDetailPO po : list
                ) {
            System.out.println(po.getName());
        }
        MovieDetailPO po = dao.getByName("The Shawshank Redemption");
        System.out.println(po.getName());
        Iterator<String> iterator = dao.getCreators("The Shawshank Redemption");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        iterator = dao.getStars("The Shawshank Redemption");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test5() {
        MakerDetailDao dao = DAOManager.makerDetailDao;
        List<MakerDetailPO> list = dao.getAll("star");
        for (MakerDetailPO po : list
                ) {
            System.out.println(po.getName());
        }
        MakerDetailPO po = dao.getByName("Arshad Warsi");
        System.out.println(po.getName());
        Iterator<String> iterator = dao.getMovies("Arshad Warsi");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test6() {
        System.out.println(ToBigImage.toBigImage("https://images-na.ssl-images-amazon.com/images/M/MV5BYzVlMWViZGEtYjEyYy00YWZmLThmZGEtYmM4MDZlN2Q5MmRmXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UX182_CR0,0,182,268_AL_.jpg"));
    }

    @Test
    public void test7() {
        List<MakerDetailPO> list = DAOManager.makerDetailDao.getAllByFirstLetter("star", "A");
//        for (MakerDetailPO po:list
//                ) {
//            System.out.println(po.getName());
//        }
    }

    @Test
    public void test8() {
        MovieReviewPO po = new MovieReviewPO();
        po.setAll(1);
        po.setUseful(2);
        DAOManager.movieReviewDao.find("");
    }

    @Test
    public void test9() {
        List<MovieReviewPO> list = DAOManager.movieReviewDao.find("Dangal");
        for (MovieReviewPO po :
                list) {
            System.out.println(po.getAuthor());
        }
        System.out.println(DAOManager.movieReviewDao.getPersonalReviewNum("12 Angry Men"));
    }

    @Test
    public void test10() {
        List<RewardPO> list = DAOManager.rewardDao.getByMovieName("Ben-Hur");
        for (RewardPO po : list
                ) {
            System.out.println(po.getMovieName());
            System.out.println(po.getPeopleName());
            System.out.println(po.getRewardYear());
            System.out.println(po.getRewardName());
            System.out.println(po.getRewardType());
        }
    }

    @Test
    public void test11() {
        DAOManager.makerDetailDao.getAll("star");
    }

    @Test
    public void test12() {
        List<MovieDetailPO> list = DAOManager.movieDetailDao.getAll();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getReleDate());
            count++;
        }
        System.out.println(count);
    }

    @Test
    public void test13() {
        Map<Integer, Integer> map = companyDetailService.getCompanyAwards("Twentieth Century Fox", "nomiees", "oscar");
        Iterator<Integer> integerIterator = map.keySet().iterator();
        while (integerIterator.hasNext()) {
            Integer i = integerIterator.next();
            System.out.println(i + ":" + map.get(i));
        }
    }

    @Test
    public void test14() {
        Map<String, Double> map = companyDetailService.getTopSix("Twentieth Century Fox");
        for (String s : map.keySet()) {
            System.out.println(s);
        }
    }

    @Test
    public void test15() {
        List<LittleAwardsPO> littleAwardsPOS = DAOManager.littleAwardsDao.getByName("Woody Allen", "star");
        Iterator<LittleAwardsPO> iterator = littleAwardsPOS.iterator();
        LittleAwardsPO littleAwardsPO;
        int temp = 0;
        while (iterator.hasNext()) {
            littleAwardsPO = iterator.next();
            temp += (littleAwardsPO.getWon() + littleAwardsPO.getNominated() * 0.5);
            System.out.println(littleAwardsPO.getWon() + " " + littleAwardsPO.getNominated());
        }
        System.out.println(temp);
    }

    @Test
    public void test16() {
        List<MakerDetailPO> list = Persistence.getAllMaker("star");
        for (MakerDetailPO po :
                list) {
            DAOManager.makerDetailDao.getByName(po.getName());
        }
    }

    @Test
    public void test17() {
        List<MakerDetailPO> list = DAOManager.makerDetailDao.modify("Woody Allen", "star");
        System.out.println(list.size());
        for (int i = 1; i < list.size(); i++) {
            DAOManager.makerDetailDao.delete(list.get(i));
        }
        DAOManager.makerDetailDao.getByName("Woody Allen");
    }

    @Test
    public void test18() {
        MakerDetailService service = new MakerDetailImpl();
        Map<String, Double> map = service.getMakerRadarMap("Woody Allen", "star");
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            System.out.println(s + ":" + map.get(s));
        }
    }

    @Test
    public void test19() {
        System.out.println(DAOManager.favoriteDao.getMakerByUserName("gmd","creator").get(0).getName());
    }

    @Test
    public void test20(){
        DAOManager.favoriteDao.getMakerByUserName("wx","creator");
    }
}

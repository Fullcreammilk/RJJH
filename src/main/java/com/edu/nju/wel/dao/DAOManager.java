package com.edu.nju.wel.dao;

import com.edu.nju.wel.util.ApplicationContextHelper;
import org.springframework.context.ApplicationContext;

/**
 * Created by Jerry Wang on 2016/12/30.
 */
public class DAOManager {

    public final static UserDao userDao;

    public final static MovieReviewDao movieReviewDao;

    public final static MovieDetailDao movieDetailDao;

    public final static MakerDetailDao makerDetailDao;

    public final static Maker2MovieDao maker2MovieDao;

    public final static MovieDataDao movieDataDao;

    public final static RewardDao rewardDao;

    public final static FavoriteDao favoriteDao;

    public final static CompanyDao companyDao;

    public final static AverageDao averageDao;

    public final static LittleAwardsDao littleAwardsDao;

    public final static CoefficientDao coefficientDao;


    static {
        ApplicationContext context = ApplicationContextHelper.getApplicationContext();
        userDao = context.getBean(UserDao.class);

        movieReviewDao=context.getBean(MovieReviewDao.class);

        movieDetailDao=context.getBean(MovieDetailDao.class);

        makerDetailDao=context.getBean(MakerDetailDao.class);

        maker2MovieDao=context.getBean(Maker2MovieDao.class);

        movieDataDao=context.getBean(MovieDataDao.class);

        rewardDao=context.getBean(RewardDao.class);

        favoriteDao=context.getBean(FavoriteDao.class);

        companyDao=context.getBean(CompanyDao.class);

        averageDao=context.getBean(AverageDao.class);

        littleAwardsDao=context.getBean(LittleAwardsDao.class);

        coefficientDao=context.getBean(CoefficientDao.class);
    }

}

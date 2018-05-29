package test.com.edu.nju.wel.service.impl;

import com.edu.nju.wel.model.ChartParaVO;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.StatisticService;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import com.edu.nju.wel.service.impl.StatisticServiceImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * StatisticServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 7, 2017</pre>
 */
public class StatisticServiceImplTest {
    MakerDetailService makerDetailService = new MakerDetailImpl();

    StatisticService statisticService = new StatisticServiceImpl();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getTagNum()
     */
    @Test
    public void testGetTagNum() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getTagRatingValues()
     */
    @Test
    public void testGetTagRatingValues() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getAllGenres()
     */
    @Test
    public void testGetAllGenres() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: ratingAndGross()
     */
    @Test
    public void testRatingAndGross() throws Exception {
        for(ChartParaVO vo:statisticService.ratingAndGross()){
            System.out.println(vo.getAttr()+" "+vo.getValue());
        }
    }

    /**
     * Method: reviewAndGross()
     */
    @Test
    public void testReviewAndGross() throws Exception {
        for(ChartParaVO vo:statisticService.reviewAndGross()){
            System.out.println(vo.getAttr()+" "+vo.getValue());
        }
    }

    @Test
    public void testTopTenRatingStar() throws Exception {
        for(ListVO vo:statisticService.topTenRatingStar()){
            System.out.println(vo.getName()+" "+vo.getScore());
        }
    }




} 

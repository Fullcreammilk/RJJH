package test.com.edu.nju.wel.service.impl;

import com.edu.nju.wel.model.ForecastVO;
import com.edu.nju.wel.service.ForecastService;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.impl.ForecastServiceImpl;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;

/**
 * ForecastServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 1, 2017</pre>
 */
public class ForecastServiceImplTest {
    MakerDetailService makerDetailService = new MakerDetailImpl();

    ForecastService forecastService = new ForecastServiceImpl();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: forecastRatingValue(ArrayList<String> genres, ArrayList<String> stars, ArrayList<String> creators)
     */
    @Test
    public void testForecastRatingValue() throws Exception {
        String[] genres = {};
        String[] stars = {"Tim Robbins","Morgan Freeman","Bob Gunton"};
        String[] creators = {"Frank Darabont"};

        System.out.println(forecastService.forecastRatingValue(genres,stars,creators));
        for(ForecastVO vo:forecastService.getExtraEffect()){
            System.out.println(vo.getTitle()+" "+vo.getNames()[0]+" "+vo.getMsg()+" "+vo.getImgUrls()[0]);
        }
    }

} 

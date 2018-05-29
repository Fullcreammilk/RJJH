package test.com.edu.nju.wel.service.impl;

import com.edu.nju.wel.model.AxisParamVO;
import com.edu.nju.wel.model.ListVO;
import com.edu.nju.wel.model.RecMovieVO;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.MovieDetailService;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import com.edu.nju.wel.service.impl.MovieDetailServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * MovieDetailServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 17, 2017</pre>
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/applicationContext.xml" })
public class MovieDetailServiceImplTest {
    MakerDetailService makerDetailService = new MakerDetailImpl();

    MovieDetailService movieDetailService = new MovieDetailServiceImpl();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getMovies(String keywords, String tag, String sortWay)
     */
    @Test
    public void testGetMovies() throws Exception {
//        movieDetailService.getMovies("",null,"Score");
        for(ListVO vo:movieDetailService.getMovies("",null,"Time")){
            System.out.println(vo.getName()+" "+vo.getReleDate());
        }
    }

    @Test
    public void testGetRecMovies() throws Exception {
        for(RecMovieVO vo:movieDetailService.recMovie(movieDetailService.getMovie("Girl with a Pearl Earring"))){
            System.out.println(vo.getName());
        }
    }


    @Test
    public void testGetAxisParam()throws Exception{
        for(AxisParamVO vo:(movieDetailService.getAxisParam("Pan's Labyrinth"))){
            System.out.println(vo.getName()+": min "+vo.getMin()+" max "+vo.getMax()+" avg "+vo.getAvg()+" value "+vo.getValue());
        }
    }
} 

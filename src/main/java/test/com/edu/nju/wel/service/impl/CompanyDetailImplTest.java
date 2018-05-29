package test.com.edu.nju.wel.service.impl;

import com.edu.nju.wel.model.TwoValueChartVO;
import com.edu.nju.wel.service.CompanyDetailService;
import com.edu.nju.wel.service.MakerDetailService;
import com.edu.nju.wel.service.impl.CompanyDetailImpl;
import com.edu.nju.wel.service.impl.MakerDetailImpl;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * CompanyDetailImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 7, 2017</pre>
 */
public class CompanyDetailImplTest {
    MakerDetailService makerDetailService = new MakerDetailImpl();

    CompanyDetailService companyDetailService = new CompanyDetailImpl();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getGrossPerYear(String companyName)
     */
    @Test
    public void testGetGrossPerYear() throws Exception {
        for(TwoValueChartVO vo:companyDetailService.getGrossPerYear("Warner Bros")){
            System.out.println(vo.getAttr()+" "+vo.getV1()+" "+vo.getV2());
        }
    }


} 

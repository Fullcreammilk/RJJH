package test.com.edu.nju.wel.util;

import com.edu.nju.wel.util.TagStatistic;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * TagStatistic Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 16, 2017</pre>
 */
public class TagStatisticTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: update()
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println(TagStatistic.tagMap.get("Drama").getNum());
    }


} 

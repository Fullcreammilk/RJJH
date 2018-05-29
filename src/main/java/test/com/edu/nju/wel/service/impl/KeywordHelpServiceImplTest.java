package test.com.edu.nju.wel.service.impl;

import com.edu.nju.wel.service.KeywordHelpService;
import com.edu.nju.wel.service.impl.KeywordHelpServiceImpl;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import javax.annotation.Resource;

/**
 * KeywordHelpServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>六月 7, 2017</pre>
 */
public class KeywordHelpServiceImplTest {
    KeywordHelpService keywordHelpService = new KeywordHelpServiceImpl();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: keywordHelp(String keyword, String type)
     */
    @Test
    public void testKeywordHelp() throws Exception {
        for(String res:keywordHelpService.keywordHelp("god","movie")){
            System.out.println(res);
        }
    }


    /**
     * Method: addToList(ArrayList<String> list, String name, int level)
     */
    @Test
    public void testAddToList() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = KeywordHelpServiceImpl.getClass().getMethod("addToList", ArrayList<String>.class, String.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 

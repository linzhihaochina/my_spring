import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-18 20:41   
 *  *    
 *  
 */
public class TestSpring {

    ApplicationContext applicationContext;

    @Before
    public void prepare() {
        applicationContext = new ClassPathXmlApplicationContext("application.xml");
    }

    @Test
    public void test01() {
        Object debugObj = applicationContext.getBean("debugObj");
        System.out.println(debugObj);
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
}

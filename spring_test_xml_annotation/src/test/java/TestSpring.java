import com.youngforcoding.pojo.AopTest;
import com.youngforcoding.pojo.DITarget;
import com.youngforcoding.pojo.LazyObj;
import com.youngforcoding.pojo.ResourceTest;
import com.youngforcoding.service.TransferService;
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
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        //  普通配置bean
        System.out.println(applicationContext.getBean("lazyObj", LazyObj.class));
        System.out.println(applicationContext.getBean("lazyObj", LazyObj.class));
        System.out.println(applicationContext.getBean("lazyObj", LazyObj.class));
        //  通过静态工厂方法的形式配置
        System.out.println(applicationContext.getBean("lazyObj2"));
        System.out.println(applicationContext.getBean("lazyObj2"));
        System.out.println(applicationContext.getBean("lazyObj2"));
        //  通过普通工厂方法的形式配置
        System.out.println(applicationContext.getBean("lazyObj3"));
        System.out.println(applicationContext.getBean("lazyObj3"));
        System.out.println(applicationContext.getBean("lazyObj3"));
        System.out.println(applicationContext.getBean("lazyObj3"));
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }

    @Test
    public void test02() {
        DITarget diTarget = (DITarget) applicationContext.getBean("diTarget");
        System.out.println(diTarget);
        System.out.println(diTarget.getLazyObj());

        System.out.println(applicationContext.getBean("diTarget2"));
    }

    @Test
    public void test03() {
        ResourceTest resourceTest = (ResourceTest) applicationContext.getBean("resourceTest");
//        System.out.println(resourceTest.getProxyFactory());
        Object aa2 = applicationContext.getBean("java.lang.Object");
        Object aa3 = applicationContext.getBean("java.lang.Object");
        Object aa4 = applicationContext.getBean("java.lang.Object");
        System.out.println(aa2);
        System.out.println(aa3);
        System.out.println(aa4);
        System.out.println(resourceTest);
        System.out.println(resourceTest.getAa2());
    }

    @Test
    public void test04() {
        AopTest aopTest = (AopTest) applicationContext.getBean("aopTest");
        aopTest.sayHello();
    }

    @Test
    public void test05() throws Exception {
        TransferService transferService = (TransferService) applicationContext.getBean("transferService");
        transferService.transfer("6029621011001", "6029621011000", 10);
    }
}

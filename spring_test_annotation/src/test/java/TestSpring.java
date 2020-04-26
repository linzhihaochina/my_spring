import com.youngforcoding.config.SpringConfig;
import com.youngforcoding.dao.AccountDao;
import com.youngforcoding.pojo.AopTest;
import com.youngforcoding.service.TransferService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

    /**
     * JavaSE以纯注解的形式启动Spring容器
     */
    @Before
    public void prepare(){
        applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
    }


    @Test
    public void test04(){
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao);
    }

    @Test
    public void test05(){
        //  获取工厂对象生成的对象
        Object accountBean = applicationContext.getBean("accountBean");
        //  获取工厂对象
        Object accountBeanFactory = applicationContext.getBean("&accountBean");
        System.out.println(accountBean);
        System.out.println(accountBeanFactory);
    }


    @Test
    public void test06(){
        AopTest aopTest = (AopTest) applicationContext.getBean("aopTest");
        aopTest.sayHello();
    }

    @Test
    public void test07() throws Exception {
        TransferService transferService = (TransferService) applicationContext.getBean("transferService");
        transferService.transfer("6029621011001","6029621011000",100);
    }
}

import com.youngforcoding.SpringConfig;
import com.youngforcoding.annotation.ComponentScan;
import com.youngforcoding.annotation.Configuration;
import org.junit.Test;

import java.lang.annotation.Annotation;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-25 19:34   
 *  *    
 *  
 */
public class FrameTest {

    @Test
    public void test01() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("com.youngforcoding.SpringConfig");
        for (Annotation declaredAnnotation : aClass.getDeclaredAnnotations()) {
            System.out.println(declaredAnnotation);
        }
        System.out.println(SpringConfig.class.getDeclaredAnnotation(Configuration.class));
        System.out.println(SpringConfig.class.getDeclaredAnnotation(ComponentScan.class));
    }
}

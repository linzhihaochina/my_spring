import com.youngforcoding.annotation.Configuration;
import org.junit.Test;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-24 13:32   
 *  *    
 *  
 */
public class ContextTest {

    @Test
    public void test01() {
        Annotation[] annotations = IocConfig.class.getAnnotations();
//        Arrays.stream(annotations).forEach(System.out::println);
        Arrays.stream(annotations).forEach(annotation -> {
            System.out.println(annotation instanceof Configuration);
            System.out.println(annotation.annotationType() == Configuration.class);
        });
//        System.out.println(IocConfig.class.getAnnotation(ContextConfiguration.class));
    }

    @Test
    public void test02(){
        //ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//        File file = new File("com/youngforcoding");
//        System.out.println(file.exists());
//        contextClassLoader.getResourceAsStream("com/youngforcoding")
        String path = Configuration.class.getResource("/").getPath() + "com/youngforcoding";
        File file = new File(path);
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
    }
}

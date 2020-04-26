package com.youngforcoding.util;

import java.io.InputStream;

/**
 *    
 *  *  
 *  * @Description:  获取当前项目classpath下指定文件的输入流   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-24 14:02   
 *  *    
 *  
 */
public class ResourceUtil {

    public static InputStream getResources(String location) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
    }
}

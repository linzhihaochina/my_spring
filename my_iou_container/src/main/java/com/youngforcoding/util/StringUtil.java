package com.youngforcoding.util;

/**
 *    
 *  *  
 *  * @Description:  String的工具类   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-24 12:57   
 *  *    
 *  
 */
public class StringUtil {

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }

    public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
    }
}

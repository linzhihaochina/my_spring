package com.youngforcoding.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-18 21:46   
 *  *    
 *  
 */
public class DITarget2 {

    private List<String> list;

    private int[] intArr;

    private Map<Object,Object> map;

    private Properties properties;

    public DITarget2(List<String> list, int[] intArr, Map<Object,Object> map, Properties properties) {
        this.list = list;
        this.intArr = intArr;
        this.map = map;
        this.properties = properties;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setIntArr(int[] intArr) {
        this.intArr = intArr;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "DITarget2{" +
                "list=" + list +
                ", intArr=" + Arrays.toString(intArr) +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }
}

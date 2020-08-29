package com.kuaizi.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author kuaiziqinshi
 * @create 2020-08-23-19:49
 */

//web层有关的工具集
public class WebUtils {
    //将Map中的值注入到相应的bean当中。
    public static <T> T insertBean(Map value, T bean){
        try {
            System.out.println("注入前的bean"+bean);
            BeanUtils.populate(bean,value);
            System.out.println("注入后的bean"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    //将一个字符串类型转换成整型数据
    public static int parse(String str,Integer integer){
        try {
            if (str!=null) {
                System.out.println("是" + str);
                return Integer.parseInt(str);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return integer;
    }

}

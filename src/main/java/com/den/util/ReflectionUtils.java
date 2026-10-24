package com.den.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Denis on 04.05.2015.
 */
public class ReflectionUtils {

    public static  <T,O>  void modify(T wrapper, O needModify){
        Class oClass = wrapper.getClass();
        Class bClass = needModify.getClass();
        Method[] methods = oClass.getDeclaredMethods();

        for (Method method : methods) {
            try {
                Object o = method.invoke(wrapper);
                if (o == null) {
                    continue;
                }


                bClass.getMethod("set" + method.getName().substring(3), method.getReturnType()).invoke(needModify, o);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}

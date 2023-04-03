package com.bjqg.document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: lbj
 * @date: 2023/3/1 14:06
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        /**
         *
         * @param: args
         * @return :
         * @author : lbj
         * @description: <获取 TargetObject 类的 Class 对象并且创建 TargetObject 类实例>
         * @date : 2023/3/21 15:57
         */
        Class<?> targetClass = Class.forName("com.bjqg.document.TargetObject");
        TargetObject targetObject = (TargetObject) targetClass.newInstance();

        /**
         *
         * @param: args
         * @return :
         * @author : lbj
         * @description: <获取targetClass中的所有方法>
         * @date : 2023/3/21 16:00
         */
        Method[] methods = targetClass.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method.getName());
        }

        /**
         *
         * @param: args
         * @return :
         * @author : lbj
         * @description: <获取指定方法并调用>
         * @date : 2023/3/21 16:03
         */
        Method publicMethod = targetClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(targetObject,"LQG");
        /**
         *
         * @param: args
         * @return :
         * @author : lbj
         * @description: <获取指定参数并对参数进行修改>
         * @date : 2023/3/21 16:05
         */
        Field value = targetClass.getDeclaredField("value");
        //为了对类中的参数进行修改我们取消安全检查
        value.setAccessible(true);
        value.set(targetObject,"LQG");
        /**
         *
         * @param: args
         * @return :
         * @author : lbj
         * @description: <调用private方法>
         * @date : 2023/3/21 16:07
         */
        Method privateMethod = targetClass.getDeclaredMethod("privateMethod");
        //为了调用private方法，取消安全检查
        privateMethod.setAccessible(true);
        privateMethod.invoke(targetObject);

    }

}

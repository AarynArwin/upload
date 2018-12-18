package com.chengnanhuakai.upload.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @ClassName TestOne
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/12/18 11:01
 * @Version 1.0
 */
public class TestOne {

    public static void main(String[] args) throws Exception {

//        Class aClass = Class.forName("com.chengnanhuakai.upload.reflect.Student");
//
//
//        Constructor constructor = aClass.getConstructor(String.class);
//
//        System.out.println(constructor);

        ArrayList<String> stringArrayList = new ArrayList<String>();

        stringArrayList.add("hello");

        Class aClass = Class.forName("java.util.ArrayList");

        Method add = aClass.getMethod("add",Object.class);


        Object invoke = add.invoke(stringArrayList,1);

        for (Object s : stringArrayList) {
            System.out.println(s);
        }

    }
}

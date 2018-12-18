package com.chengnanhuakai.upload.reflect;

import lombok.Data;

/**
 * @ClassName Student
 * @Description TODO
 * @Author Aaryn
 * @Date 2018/12/18 10:06
 * @Version 1.0
 */
@Data
public class Student {

    private String name;

    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public Student(String name) {
        System.out.println("用户姓名为" + name);
        this.name = name;
    }
}

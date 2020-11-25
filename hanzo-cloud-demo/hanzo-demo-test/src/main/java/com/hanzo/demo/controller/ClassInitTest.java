package com.hanzo.demo.controller;

/**
 * @Author thy
 * @Date 2020/11/24 16:42
 * @Description:
 */
public class ClassInitTest {
    private static int num = 1;

    static {
        num = 2;
        number = 20;
    }

    private static int number = 10;

    public static void main(String[] args) {
        System.out.println(ClassInitTest.num);
        System.out.println(ClassInitTest.number);
    }
}

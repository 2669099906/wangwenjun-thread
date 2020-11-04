package com.tuyrk.classloader.chapter01;

/**
 * @author zhaoxiangrui
 * @date 2020/11/4 0:09
 */

/**
 * 类加载的方式
 * 1.本地磁盘直接加载
 * 2.内存中直接加载
 * 3.通过网络加载class
 * 4.从zip、jar等归档文件中加载。class文件
 * 5.数据库中提取。class文件
 * 6.动态编译
 */
public class LoaderClass {
    public static void main(String[] args) {
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        MyObject myObject3 = new MyObject();
        MyObject myObject4 = new MyObject();
        System.out.println(myObject1.getClass() == myObject2.getClass());
        System.out.println(myObject2.getClass() == myObject3.getClass());
        System.out.println(myObject3.getClass() == myObject4.getClass());
        System.out.println(myObject4.getClass() == myObject1.getClass());
        /**
         * true
         * true
         * true
         * true
         */
    }
}

class MyObject {

}

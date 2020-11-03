package com.tuyrk.classloader.chapter01;

/**
 * @author zhaoxiangrui
 * @date 2020/11/3 23:46
 */
public class Singleton {

    private static  Singleton instance = new Singleton();

    /**
     * private static  Singleton instance = new Singleton();
     * 在第一行时
     * instance = null
     * int x = 0
     * int y = 0
     * instance = new Singleton()
     * x++ => x=1
     * y++ => y=1
     *
     * x = 0
     */

    public static int x = 0;

    public static int y;

//    private static  Singleton instance = new Singleton();

    /**
     * private static  Singleton instance = new Singleton();
     * 在第三行时
     * int x = 0
     * int y = 0
     * instance = null
     *
     * instance = new Singleton()
     * x++ => x=1
     * y++ => y=1
     */

    private Singleton(){
        x++;
        y++;
    }

    public static Singleton getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}

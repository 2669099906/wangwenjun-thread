package com.tuyrk.classloader.chapter01;

import java.util.Random;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/3 21:07
 */
public class ClassActiveUse {

	public static void main(String[] args) throws ClassNotFoundException {
//		new Obj();
//		System.out.println(I.a);
//		System.out.println(Obj.c);
//		Obj.printC();
//		Class.forName("com.tuyrk.classloader.chapter01.Child");
//		System.out.println(Child.age);
//		Child.printC();
//		Obj[] objs = new Obj[10];
//		System.out.println(Obj.s);
//		System.out.println(Obj.x);
	}
}

class Obj{
	public static long c = 100000;

	public static final long s = 100000;

	public static final int x = new Random().nextInt(1000);

	static {
		System.out.println("Obj 初始化");
	}

	public static void printC(){
		System.out.println(c);
	}
}

interface I{
	int a = 10;
}

class Child extends Obj{
	public static int age = 23;

	static {
		System.out.println("child 初始化");
	}
}
/**
 * 主动加载的分类
 * 1.new 直接使用
 * 2.访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
 * 3.调用静态方法
 * 4.反射某个类
 * 5.初始化一个子类,父类也会初始化
 * 6启动类
 *
 * 除了上述六种以外，其余都是被动使用，不会导致类的初始化
 */

/**
 * 访问某个类或者接口的静态变量，或者对该静态变量进行复制操作
 * 1.对某个类的静态变量进行读写
 * 2.对接口中静态变量进行读取
 */

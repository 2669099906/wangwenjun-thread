package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 15:13
 */
public class ActiveObjectClient {

	public static void main(String[] args) {
		ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
		new MakerStringClientThread("A", activeObject, '#').start();
		new MakerStringClientThread("B", activeObject, '*').start();

		new DisplayClientThread("C", activeObject).start();
		new DisplayClientThread("D", activeObject).start();
	}
}

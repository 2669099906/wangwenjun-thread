package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 12:08
 */
class Servant implements ActiveObject {
	@Override
	public Result makeString(int count, char fillChar) {
		char[] buf = new char[count];
		for(int i=0; i<buf.length; i++){
			buf[i] = fillChar;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return new RealResult(new String(buf));
	}

	@Override
	public void displayString(String text) {
		System.out.println("Display: " + text);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

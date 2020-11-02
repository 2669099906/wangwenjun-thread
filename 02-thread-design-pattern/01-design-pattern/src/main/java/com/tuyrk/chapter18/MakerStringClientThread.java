package com.tuyrk.chapter18;

import java.util.Random;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 15:04
 */
public class MakerStringClientThread extends Thread {
	private final ActiveObject activeObject;

	private final char fillChar;


	private final static Random random = new Random(System.currentTimeMillis());

	public MakerStringClientThread(String name, ActiveObject activeObject, char fillChar) {
		super(name);
		this.activeObject = activeObject;
		this.fillChar = fillChar;
	}

	@Override
	public void run() {
		for(int i=0; true; i++){
			int count = random.nextInt(10) + 1;
			Result result = activeObject.makeString(count, this.fillChar);
			Object resultValue = result.getResultValue();
			System.out.println("MakerString: " + Thread.currentThread().getName() + "=>" +resultValue);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 14:59
 */
public class DisplayClientThread extends Thread {

	private final ActiveObject activeObject;


	public DisplayClientThread(String name, ActiveObject activeObject) {
		super(name);
		this.activeObject = activeObject;
	}

	@Override
	public void run() {
		for(int i=0; true; i++){
			String text = Thread.currentThread().getName() + "=>" + i;
			activeObject.displayString(text);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 12:18
 */
public class FutureResult implements Result {

	private Object resultValue;

	private boolean ready = false;

	public synchronized void setResultValue(Object resultValue){
		if(!ready){
			this.resultValue = resultValue;
			this.ready = true;
		}
		this.notifyAll();
	}

	@Override
	public synchronized Object getResultValue() {
		while (!ready){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notifyAll();
		return this.resultValue;
	}
}

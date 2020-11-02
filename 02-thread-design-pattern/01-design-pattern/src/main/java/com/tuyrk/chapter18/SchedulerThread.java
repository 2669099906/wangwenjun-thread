package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 12:46
 */
public class SchedulerThread extends Thread {

	private final ActivationQueue activationQueue;


	public SchedulerThread(ActivationQueue activationQueue) {
		this.activationQueue = activationQueue;
	}

	public void invoke(MessageRequest request){
		this.activationQueue.put(request);
	}

	@Override
	public void run() {
		while (true){
			activationQueue.take().execute();
		}
	}
}

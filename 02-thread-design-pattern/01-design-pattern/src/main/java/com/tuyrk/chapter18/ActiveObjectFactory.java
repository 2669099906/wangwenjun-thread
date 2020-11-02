package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 14:54
 */
public final class ActiveObjectFactory {

	private ActiveObjectFactory(){

	}

	public static ActiveObject createActiveObject(){
		Servant servant = new Servant();
		ActivationQueue queue = new ActivationQueue();
		SchedulerThread schedulerThread = new SchedulerThread(queue);
		ActiveObjectProxy activeObjectProxy = new ActiveObjectProxy(servant, schedulerThread);
		schedulerThread.start();
		return activeObjectProxy;
	}
}

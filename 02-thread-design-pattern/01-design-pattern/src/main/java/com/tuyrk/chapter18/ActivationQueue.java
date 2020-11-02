package com.tuyrk.chapter18;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 12:33
 */
public class ActivationQueue {

	public static final int MAX_QUEUE_SIZE = 100;

	private final Deque<MessageRequest> methodQueue ;

	public ActivationQueue() {
		this.methodQueue = new LinkedList<>();
	}

	public synchronized void put(MessageRequest request){
		while (methodQueue.size() >= MAX_QUEUE_SIZE){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.methodQueue.addLast(request);
		this.notifyAll();
	}

	public synchronized MessageRequest take(){
		while (methodQueue.isEmpty()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		MessageRequest messageRequest = methodQueue.removeFirst();
		this.notifyAll();
		return messageRequest;
	}
}

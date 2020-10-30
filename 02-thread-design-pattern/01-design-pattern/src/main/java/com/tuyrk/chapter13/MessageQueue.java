package com.tuyrk.chapter13;



import java.util.LinkedList;

/**
 * @Author zhaoxiangrui
 * @create 2020/10/30 20:24
 */
public class MessageQueue {

	private final LinkedList<Message> queue;

	private final static int DEFAULT_MAX_LIMIT = 100;

	private final int limit;

	public MessageQueue(){
		this(DEFAULT_MAX_LIMIT);
	}

	public MessageQueue(int limit) {
		this.limit = limit;
		this.queue = new LinkedList<>();
	}

	public void put(final Message message) throws InterruptedException {
		synchronized (queue){
			while (queue.size() > limit){
				queue.wait();
			}
			queue.addLast(message);
			System.out.println(Thread.currentThread().getName()+" put "+message.getData());
			queue.notifyAll();
		}

	}

	public Message take() throws InterruptedException {
		Message message = null;
		synchronized (queue){
			while (queue.size() == 0){
				queue.wait();
			}
			message = queue.removeFirst();
			System.out.println(Thread.currentThread().getName() + " take " + message.getData());
			queue.notifyAll();
		}

		return message;
	}

	public int getMaxLimit(){
		return this.limit;
	}

	public int getCurrentQueueSize(){
		synchronized (queue){
			return queue.size();
		}
	}
}

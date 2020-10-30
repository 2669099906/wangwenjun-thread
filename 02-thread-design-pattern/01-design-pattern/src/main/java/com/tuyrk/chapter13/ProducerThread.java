package com.tuyrk.chapter13;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhaoxiangrui
 * @create 2020/10/30 20:47
 */
public class ProducerThread extends Thread {

	private final MessageQueue messageQueue;

	private final static Random random = new Random(System.currentTimeMillis());

	private final static AtomicInteger counter = new AtomicInteger(0);

	public ProducerThread(MessageQueue messageQueue, int seq) {
		super("PRODUCER-"+seq);
		this.messageQueue = messageQueue;
	}

	@Override
	public void run() {
		while (true){
			try {
				Message message = new Message("Message:"+ counter.incrementAndGet());
				messageQueue.put(message);
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

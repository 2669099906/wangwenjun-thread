package com.tuyrk.chapter13;

import java.util.Random;

/**
 * @Author zhaoxiangrui
 * @create 2020/10/30 20:56
 */
public class ConsumerThread extends  Thread{

	private final MessageQueue queue;

	private final Random random = new Random(System.currentTimeMillis());

	public ConsumerThread(MessageQueue queue, int seq) {
		super("CONSUMER-" + seq);
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true){
				Message message = queue.take();
				Thread.sleep(random.nextInt(1000));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

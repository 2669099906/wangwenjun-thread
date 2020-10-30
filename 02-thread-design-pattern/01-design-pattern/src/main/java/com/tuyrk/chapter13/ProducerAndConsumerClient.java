package com.tuyrk.chapter13;

import java.util.stream.IntStream;

/**
 * @Author zhaoxiangrui
 * @create 2020/10/30 21:03
 */
public class ProducerAndConsumerClient {

	public static void main(String[] args) {

		final MessageQueue queue = new MessageQueue(10);

		new ConsumerThread(queue, 1).start();
		new ConsumerThread(queue, 2).start();
		new ConsumerThread(queue, 3).start();
		new ProducerThread(queue, 1).start();
		new ProducerThread(queue, 2).start();
		new ProducerThread(queue, 3).start();
	}
}

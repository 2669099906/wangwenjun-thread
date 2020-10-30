package com.tuyrk.chapter13;

import java.util.LinkedList;

/**
 * @Author zhaoxiangrui
 * @create 2020/10/30 19:45
 */
/**
 * 封装任务类
 */
public class WorkTask extends Thread {

	private LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

	public WorkTask(ThreadGroup group, String name) {
		super(group, name);
	}

	static enum TaskState {
		FREE, RUNNING, BLOCKED, DEAD
	}

	private volatile TaskState taskState = TaskState.FREE;

	public TaskState getTaskState() {
		return this.taskState;
	}


	@Override
	public void run() {
		OUTER:
		while (this.taskState != TaskState.DEAD) {
			Runnable runnable;
			synchronized (TASK_QUEUE) {
				// 任务队列为空，则进入阻塞状态
				while (TASK_QUEUE.isEmpty()) {
					try {
						this.taskState = TaskState.BLOCKED;
						TASK_QUEUE.wait();
					} catch (InterruptedException e) {
						System.out.println("Closed.");
						break OUTER;
					}
				}
				// 任务队列不为空，取出任务
				runnable = TASK_QUEUE.removeFirst();
			}
			// 任务不为空，则执行任务
			if (runnable != null) {
				this.taskState = TaskState.RUNNING;
				runnable.run();
				this.taskState = TaskState.FREE;
			}
		}
	}

	public void close() {
		this.taskState = TaskState.DEAD;
	}

}

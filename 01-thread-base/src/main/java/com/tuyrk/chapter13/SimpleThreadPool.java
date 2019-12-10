package com.tuyrk.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 自定义简单线程池
 * 任务队列、拒绝策略
 *
 * @author tuyrk
 */
public class SimpleThreadPool {
    /**
     * 传参线程池大小
     */
    private final int size;

    /**
     * 线程队列的大小
     */
    private final int queueSize;

    /**
     * 拒绝策略
     */
    private final DiscardPolicy discardPolicy;

    /**
     * 默认线程池大小
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * 默认线程队列的大小
     */
    private static final int DEFAULT_TASK_QUEUE_SIZE = 2000;

    /**
     * 默认拒绝策略
     */
    public static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("");
    };

    /**
     * 线程名自增序号
     */
    private static AtomicInteger sequence = new AtomicInteger();

    /**
     * 线程名前缀
     */
    private static final String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    /**
     * 线程组
     */
    private static final ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    /**
     * 任务队列
     */
    private static final LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    /**
     * 线程队列
     */
    private static final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int size, int queueSize, DiscardPolicy discardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }

    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (sequence.getAndIncrement()));
        task.start();
        THREAD_QUEUE.add(task);
    }

    /**
     * 提交任务
     *
     * @param runnable 任务
     */
    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    /**
     * 封装任务类
     */
    private static class WorkerTask extends Thread {
        private volatile TaskState taskState = TaskState.FREE;

        public TaskState getTaskState() {
            return this.taskState;
        }

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
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

    /**
     * 线程状态
     */
    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }


    /**
     * 拒绝策略
     */
    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    /**
     * 拒绝策略异常
     */
    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        /*SimpleThreadPool threadPool = new SimpleThreadPool();*/
        SimpleThreadPool threadPool = new SimpleThreadPool(6, 10, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        IntStream.rangeClosed(1, 40).forEach(i -> {
            threadPool.submit(() -> {
                System.out.printf("The runnable %d be serviced by %s start.\n", i, Thread.currentThread().getName());
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("The runnable %d be serviced by %s finished.\n", i, Thread.currentThread().getName());
            });
        });
    }
}
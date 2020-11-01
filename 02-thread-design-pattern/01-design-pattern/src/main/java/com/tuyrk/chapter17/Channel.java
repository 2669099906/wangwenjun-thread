package com.tuyrk.chapter17;

import java.util.Arrays;

/**
 * @author zhaoxiangrui
 * @date 2020/11/1 19:05
 */
public class Channel {

    private  final static int MAX_REQUEST = 50;

    private final Request[] requestQueue;

    private int head;

    private int tail;

    private int count;

    private final  WorkerThread[] workerPool;

    public Channel(int workers){
        this.requestQueue = new Request[MAX_REQUEST];
        this.workerPool = new WorkerThread[workers];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.init();
    }

    private void init() {
        for (int i=0; i<workerPool.length; i++){
            workerPool[i] = new WorkerThread(this, "thread-worker-"+i);
        }
    }

    public void startWorker(){
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    public synchronized void put(Request request){
        while (count>=requestQueue.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.requestQueue[tail] = request;
        this.tail = (tail + 1) % requestQueue.length;
        this.count++;
        this.notifyAll();
    }

    public synchronized Request take(){
        while(count<=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Request request = this.requestQueue[head];
        this.head = (head+1) % requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;

    }
}

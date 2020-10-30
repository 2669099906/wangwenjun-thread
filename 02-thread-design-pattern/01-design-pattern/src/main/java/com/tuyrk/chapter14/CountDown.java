package com.tuyrk.chapter14;

/**
 * @author zhaoxiangrui
 * @date 2020/10/31 0:02
 */
public class CountDown {

    private final int total;
    private int counter = 0;

    public CountDown(int total) {
        this.total = total;
    }

    public void down(){
        synchronized (this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this){
            while (total != counter){
                this.wait();
            }
        }
    }
}

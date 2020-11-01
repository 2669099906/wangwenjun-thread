package com.tuyrk.chapter17;

/**
 * @author zhaoxiangrui
 * @date 2020/11/1 19:07
 */
public class Request {

    private final String name;

    private final int number;


    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName() + "execute " + this.toString());
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}

package com.tuyrk.chapter18;

/**
 * @author zhaoxiangrui
 * @date 2020/11/1 23:32
 */


/**
 * 接受异步消息的主动方法
 *
 */
public interface ActiveObject {

    Result makeString(int count, char fillChar);

    void displayString(String text);
}

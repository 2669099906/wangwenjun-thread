package com.tuyrk.chapter18;

/**
 * @author zhaoxiangrui
 * @date 2020/11/1 23:41
 */

/**
 * 对应ActiveObject 的每个方法
 */
public abstract class MessageRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    protected MessageRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}

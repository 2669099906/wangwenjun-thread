package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 14:44
 */
class ActiveObjectProxy implements ActiveObject{

	private final Servant servant;

	private final SchedulerThread schedulerThread;

	public ActiveObjectProxy(Servant servant, SchedulerThread schedulerThread) {
		this.servant = servant;
		this.schedulerThread = schedulerThread;
	}


	@Override
	public Result makeString(int count, char fillChar) {
		FutureResult futureResult = new FutureResult();
		this.schedulerThread.invoke(new MakeStringRequest(servant, futureResult, count, fillChar));
		return futureResult;
	}

	@Override
	public void displayString(String text) {
		this.schedulerThread.invoke(new DisplayStringRequest(servant, text));
	}
}

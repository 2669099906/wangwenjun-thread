package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 12:14
 */
public class RealResult implements Result {

	private final Object result;

	public RealResult(Object result) {
		this.result = result;
	}

	@Override
	public Object getResultValue() {
		return this.result;
	}
}

package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 12:26
 */
public class MakeStringRequest extends MessageRequest {

	private final int count;

	private final char fillChar;

	protected MakeStringRequest(Servant servant, FutureResult futureResult, int count, char fillChar) {
		super(servant, futureResult);
		this.count = count;
		this.fillChar = fillChar;
	}

	@Override
	public void execute() {
		Result result = servant.makeString(count, fillChar);
		futureResult.setResultValue(result.getResultValue());
	}
}

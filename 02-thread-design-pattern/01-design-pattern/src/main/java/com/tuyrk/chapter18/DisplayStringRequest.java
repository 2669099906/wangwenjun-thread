package com.tuyrk.chapter18;

/**
 * @Author zhaoxiangrui
 * @create 2020/11/2 12:30
 */
public class DisplayStringRequest extends MessageRequest {

	private final String text;

	public DisplayStringRequest(Servant servant, String text) {
		super(servant, null);
		this.text = text;
	}

	@Override
	public void execute() {
		this.servant.displayString(this.text);
	}
}

package com.tuyrk.chapter13;

/**
 * @Author zhaoxiangrui
 * @create 2020/10/30 19:59
 */
public interface DiscardPolicy {
	void discard() throws DiscardException;

	public static final DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
		throw new DiscardException("Discard This Task.");
	};
}

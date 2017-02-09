package com.juc.lzy.ch9;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-06-18
 */
public class ComputationTask<V> extends FutureTask<V> {

	public ComputationTask(Callable<V> callable) {
		super(callable);
	}

	@Override
	protected void done() {
		super.done();
	}
}

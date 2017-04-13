package ds;

import java.util.concurrent.BlockingQueue;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-01
 */
public class ThreadPool {
	private int minThread;
	private int maxThread;
	private int idleTimeout;

	private BlockingQueue<Runnable> jobs;
}

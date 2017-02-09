/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-02-08
 */
public class DeadLock {
	public static void main(String[] args) {
		try {
			Thread.sleep(1000 * 300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Object lock1 = new Object();
		Object lock2 = new Object();

		new Thread(() -> {
			synchronized (lock1) {
				System.out.println("Lock1");
				try {
					Thread.sleep(1000 * 3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("try to lock2");

				synchronized (lock2) {
					System.out.println("Lock2 get");
				}
			}
		}).start();

		new Thread(() -> {
			synchronized (lock2) {
				System.out.println("Lock1");
				try {
					Thread.sleep(1000 * 3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("try to lock2");
				synchronized (lock1) {
					System.out.println("get lock1");
				}
			}
		}).start();

	}
}

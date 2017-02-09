/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-28
 */
public class MathTest {
	private int a;
	private volatile int b;
	public static void main(String[] args) {
		new MathTest().test();
		new MathTest().testSync();
	}
	public void test() {
		a = 1;
		b = 2;
	}

	public synchronized void  testSync() {
//		synchronized (this) {
			a = 3;
//		}
	}
}

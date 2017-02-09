import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-03
 */
public class TestAssembley {
	public static void main(String[] args) throws InterruptedException {
		int a = 0;
		List<Integer> list = new ArrayList<Integer>();
//		for (int i = 0; i < 10001000; i++) {
//			list.add(i);
//		}
//		for (int i = 0; i < list.size(); i++) {
//			Thread.sleep(1000);
//			a++;
//		}
		System.out.println(a);
		Runnable r = new Runnable() {
			public void run() {
				try {
					Field field = TestAssembley.class.getDeclaredField("field");
					field.setAccessible(true);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
		};

		Runnable r2 = new Runnable() {
			public void run() {
				Field field = null;
				try {
					Thread.sleep(1000);
					field = TestAssembley.class.getDeclaredField("field");
					System.out.println(field.isAccessible());
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		new Thread(r).run();
		new Thread(r2).run();
	}
	private String field;
}

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-03
 */
public class Dispatch {
	class Base {
		public void foo(double value) {
			System.out.println("Base " + value);
		}
	}

	class DerivedBase extends Base {
		public void foo(int value) {
			System.out.println("Derived");
		}
	}


	public void use(Base base) {
		base.foo(2);
	}

	public void run() {
		use(new Base());
		use(new DerivedBase());
	}

	public void useDynamic(Base base) throws Throwable {
		MethodHandles.Lookup lookup = MethodHandles.lookup();
		MethodHandle foo = lookup.findVirtual(this.getClass(), "foo", MethodType.methodType(void.class, double.class));
		Object invoke = foo.invoke(2.0);
		System.out.println(invoke);
	}
	public static void main(String[] args) throws Throwable {
		// polymorphism
		new Dispatch().run();
//		new Dispatch().useDynamic();
	}
}

package com.juc.lzy.ch9;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-10-31
 */
public class ConstructorTest extends Base{

	private boolean valid = false;
	{
		System.out.println("init block" + valid);
	}
	public ConstructorTest() {
		super();
		System.out.println("after super call");
		System.out.println("constructor init " + valid);
		this.valid = true;
	}

	{
		System.out.println("init block after");
	}

	public void say() {
		if (valid == false) {
			valid = true;
		}
		System.out.println(valid);
	}


	public static void main(String[] args) {
		new ConstructorTest();
	}
}
class Base {
	protected void say() {

	}

	Base() {
		this.say();
	}
}

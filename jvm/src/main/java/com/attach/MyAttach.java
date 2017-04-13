package com.attach;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-04-06
 */
public class MyAttach {
	public static void main(String[] args) {
		try {
			String pid = "52144";
			VirtualMachine virtualMachine = VirtualMachine.attach(pid);
		} catch (AttachNotSupportedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

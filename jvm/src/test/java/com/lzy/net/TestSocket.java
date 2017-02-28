package com.lzy.net;

import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-02-27
 */
public class TestSocket {

	@Test
	public void test() {
		try {
			Socket socket = new Socket("111.206.223.205", 80);
			System.out.println("Connected " + socket.isConnected());
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

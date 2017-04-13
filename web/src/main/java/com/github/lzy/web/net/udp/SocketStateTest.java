package com.github.lzy.web.net.udp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Date;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-04-01
 */
public class SocketStateTest {

	public static void main(String[] args) {
		try {
//			Socket socket = new Socket("localhost", 8099);
			ServerSocket serverSocket = new ServerSocket(3919);
//			socket.setSoTimeout(1000);
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
			Class<?> aClass = Class.forName("com.github.lzy.web.net.udp.UDPServer");
			Socket accept = serverSocket.accept();
			InputStream inputStream = accept.getInputStream();
			int read = inputStream.read();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

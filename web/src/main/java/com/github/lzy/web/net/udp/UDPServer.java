package com.github.lzy.web.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-30
 */
public class UDPServer {
	public void start() {
		try {
			DatagramChannel datagramChannel = DatagramChannel.open();
			datagramChannel.bind(new InetSocketAddress(8089));
			while(true) {
				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
				SocketAddress receive = datagramChannel.receive(byteBuffer);
				byteBuffer.flip();
				System.out.println("Receive from " + receive + " " + byteBuffer.toString());
				ByteBuffer returnBuffer = ByteBuffer.allocate(byteBuffer.limit());
				while(byteBuffer.hasRemaining()) {
					returnBuffer.put(byteBuffer.get());
				}
				returnBuffer.flip();
				datagramChannel.send(returnBuffer, receive);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new UDPServer().start();
	}
}

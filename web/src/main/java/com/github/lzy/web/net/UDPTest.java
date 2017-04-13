package com.github.lzy.web.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2017-03-30
 */
public class UDPTest {
	public static void main(String[] args) {
		InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8089);
		InetSocketAddress localAddress = new InetSocketAddress(10093);
		try {
			DatagramSocket datagramSocket = new DatagramSocket(localAddress);
			DatagramPacket datagramPacket = new DatagramPacket("hello".getBytes("UTF-8"), 0, 5, inetSocketAddress);
			datagramSocket.send(datagramPacket);
			datagramSocket.receive(datagramPacket);
			byte[] data = datagramPacket.getData();
			System.out.println(new String(data, "UTF-8"));
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

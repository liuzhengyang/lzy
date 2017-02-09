package common;

import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-08-19
 */
public class IpToLong {
	private static final Logger LOGGER = LoggerFactory.getLogger(IpToLong.class);

	public static long ipToLong(String ip) {
		List<String> strings = Splitter.on(".").splitToList(ip);
		long ipLong = 0;
		for (String each : strings) {
			Integer value = Integer.parseInt(each);
			ipLong += ipLong * 256 + value;
		}
		return ipLong;
	}

	public int testFinally() {
		int i;
		try {
			i = 0;
			return i;
		} finally {
			i = 2;
			System.out.println("你好");
		}
	}

	public static String longToIp(long ipLong) {
		StringBuilder ipBuilder = new StringBuilder();
		long l = ipLong & 0xf000;
		ipBuilder.delete(ipBuilder.length() - 1, ipBuilder.length());
		return ipBuilder.toString();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println(new IpToLong().testFinally());
//		Thread t1 = new Thread(new Runnable() {
//			public void run() {
//				ServerSocket ss = null;
//				try {
//					ss = new ServerSocket(8090);
//					final ServerSocket finalSs = ss;
//					while(true) {
//						Socket accept = finalSs.accept();
//						new Thread(new Runnable() {
//							public void run() {
//								Socket accept = null;
//								try {
//									accept.close();
//								} catch (IOException e) {
//									e.printStackTrace();
//								}
//							}
//						});
//					}
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		Thread t2 = new Thread(new Runnable() {
//			public void run() {
//				try {
		for (int i = 0; i < 1; i++) {
			try {
				long start = System.currentTimeMillis();
				Socket socket = new Socket("10.4.242.114", 8090, InetAddress.getLocalHost(), i + 11024);
				socket.setReuseAddress(true);
				long end = System.currentTimeMillis();
				if ((end -start) > 100) {
					System.out.println("cost " + (end - start));
				}
			} catch (Exception e) {
				// ignore
				e.printStackTrace();
			}
		}
//		socket.close();
//		socket.getInputStream().read();
//		socket.shutdownOutput();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		t1.start();
		;
//		t2.start();
//		t1.join();
//		t2.join();
		Thread.sleep(1000);
	}
}

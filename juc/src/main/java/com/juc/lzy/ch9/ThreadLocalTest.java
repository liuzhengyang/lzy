package com.juc.lzy.ch9;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-06
 */
public class ThreadLocalTest {

	private static final AtomicInteger totalSuccessCounter = new AtomicInteger(0);
	private static int parallelThreadCount = 10;
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(parallelThreadCount, new Runnable() {
		public void run() {
			System.out.println("barrier go");
		}
	});

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch stopLatch = new CountDownLatch(parallelThreadCount);
		ExecutorService executorService = Executors.newCachedThreadPool();
		runParallelJobs(startLatch, stopLatch, executorService);
		startLatch.countDown();
		stopLatch.await();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("totalSuccessCounter " + totalSuccessCounter.get());

		clearEmptyBlanks();
	}

	private static void runParallelJobs(final CountDownLatch startLatch, final CountDownLatch stopLatch, ExecutorService executorService) {
		for (int i = 0; i < parallelThreadCount; i++) {
			executorService.submit(MyRunnable.createMyRunnable(startLatch, stopLatch));
		}
	}

	private static void clearEmptyBlanks() {
		String str = "01 0D 60 00 06 00 00 60  32 00 32 00 02 02 00 70\n" +
				"24 06 C0 20 C0 9A 11 16  62 14 83 01 17 45 14 67\n" +
				"00 00 00 00 00 00 00 00  01 00 00 52 24 03 05 10\n" +
				"00 00 00 12 37 62 14 83  01 17 45 14 67 D2 40 32\n" +
				"20 11 96 30 07 59 63 10  31 30 30 30 38 30 36 30\n" +
				"38 39 38 31 30 30 30 30  30 30 30 30 30 30 31 31\n" +
				"35 36 58 75 82 A5 26 26  33 0E 26 00 00 00 00 00\n" +
				"00 00 01 38 9F 26 08 AC  F9 58 C8 0F F7 44 03 9F\n" +
				"27 01 80 9F 10 13 07 01  01 03 A0 A8 02 01 0A 01\n" +
				"00 00 00 00 00 32 8E 7B  DA 9F 37 04 5F EA 9A F8\n" +
				"9F 36 02 01 96 95 05 00  80 04 E0 00 9A 03 16 07\n" +
				"26 9C 01 00 9F 02 06 00  00 00 00 00 01 5F 2A 02\n" +
				"01 56 82 02 7C 00 9F 1A  02 01 56 9F 33 03 E0 F0\n" +
				"C8 9F 34 03 42 03 00 9F  35 01 22 84 08 A0 00 00\n" +
				"03 33 01 01 01 9F 09 02  00 30 9F 1E 08 35 30 37\n" +
				"30 33 36 36 35 9F 03 06  00 00 00 00 00 00 00 14\n" +
				"22 00 00 06 00 06 01 44  32 46 31 42 46 42 32   ";

		str = "01 04 60 00 06 00 00 60  32 00 32 00 02 02 00 70\n" +
				"24 06 80 20 C0 8A 11 16  62 14 83 01 17 45 14 67\n" +
				"00 00 00 00 00 00 00 00  03 00 01 94 24 03 05 20\n" +
				"00 00 00 37 62 14 83 01  17 45 14 67 D2 40 32 20\n" +
				"11 96 30 07 59 63 10 31  30 30 30 38 30 36 30 38\n" +
				"39 38 31 30 30 30 30 30  30 30 30 30 30 31 31 35\n" +
				"36 06 00 00 00 00 00 00  00 01 38 9F 26 08 DD 1D\n" +
				"4E 10 70 A4 8A 34 9F 27  01 80 9F 10 13 07 01 01\n" +
				"03 A0 A8 02 01 0A 01 00  00 00 00 00 73 24 F9 80\n" +
				"9F 37 04 13 08 DC 58 9F  36 02 03 21 95 05 00 80\n" +
				"08 E0 00 9A 03 16 10 27  9C 01 00 9F 02 06 00 00\n" +
				"00 00 00 03 5F 2A 02 01  56 82 02 7C 00 9F 1A 02\n" +
				"01 56 9F 33 03 E0 F0 C8  9F 34 03 1E 03 00 9F 35\n" +
				"01 22 84 08 A0 00 00 03  33 01 01 01 9F 09 02 00\n" +
				"30 9F 1E 08 35 30 37 30  33 36 37 34 9F 03 06 00\n" +
				"00 00 00 00 00 00 14 22  00 00 07 00 06 01 42 30\n" +
				"36 43 43 38 39 44                               ";
		List<String> strings = Splitter.onPattern("\\W").splitToList(str);
		String join = Joiner.on("").join(strings);
		System.out.println(join);
	}

	private static void sendPackage() {
		for (int i = 0; i < 10; i++) {
//			System.out.println(i);
			sendPackage2();
			try {
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void sendPackage2() {
		Socket socket = null;
		if (socket == null) {
			return;
		}
		try {
			OutputStream outputStream = socket.getOutputStream();
			InputStream inputStream = socket.getInputStream();
//			String hex = "010D60000600006032003200020200702406C020C09A111662148301174514670000000000000000010000522403051000000012376214830117451467D2403220119630075963103130303038303630383938313030303030303030303031313536587582A52626330E260000000000000001389F2608ACF958C80FF744039F2701809F101307010103A0A802010A010000000000328E7BDA9F37045FEA9AF89F360201969505008004E0009A031607269C01009F02060000000000015F2A02015682027C009F1A0201569F3303E0F0C89F34034203009F3501228408A0000003330101019F090200309F1E0835303730333636359F03060000000000000014220000060006014432463142464232";
			String hex = "0104600006000060320032000202007024068020C08A1116621483011745146700000000000000000300019424030520000000376214830117451467D2403220119630075963103130303038303630383938313030303030303030303031313536060000000000000001389F2608DD1D4E1070A48A349F2701809F101307010103A0A802010A0100000000007324F9809F37041308DC589F360203219505008008E0009A031610279C01009F02060000000000035F2A02015682027C009F1A0201569F3303E0F0C89F34031E03009F3501228408A0000003330101019F090200309F1E0835303730333637349F03060000000000000014220000070006014230364343383944";
			System.out.println(socket.isConnected());
			int hexLength = hex.length();
			int byteLength = hexLength / 2;
			byte[] sendBytes = new byte[byteLength];
			hexToBinaryString(sendBytes, hex);
			outputStream.write(sendBytes);
			outputStream.flush();
			int read = -1;
			int total = 0;
			byte[] buffer = new byte[1024];
			while ((read = inputStream.read(buffer)) != -1) {
				total += read;
				if (read < 1024) {
					break;
				}
			}
//			System.out.println("read " + total);
			totalSuccessCounter.incrementAndGet();
		} catch (Exception e) {
			System.out.println(socket.getLocalPort());
			System.out.println(socket.getLocalAddress());
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static StringBuilder hexToBinaryString(byte[] sendBytes, String hex) {
		StringBuilder bytesStringBuilder = new StringBuilder();
		for (int i = 0; i < hex.length() / 2; i++) {
			String hexStr = hex.substring(i * 2, (i + 1) * 2);
			int par = Integer.parseInt(hexStr, 16);
			sendBytes[i] = Integer.valueOf(par).byteValue();
		}
		return bytesStringBuilder;
	}

	static class MyRunnable implements Runnable {
		private final CountDownLatch startLatch;
		private final CountDownLatch stopLatch;

		MyRunnable(CountDownLatch startLatch, CountDownLatch stopLatch) {
			this.startLatch = startLatch;
			this.stopLatch = stopLatch;
		}

		public static MyRunnable createMyRunnable(CountDownLatch startLatch, CountDownLatch stopLatch) {
			return new MyRunnableBuilder().setStartLatch(startLatch).setStopLatch(stopLatch).createMyRunnable();
		}

		public void run() {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				sendPackage();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stopLatch.countDown();
			}
		}
	}
}

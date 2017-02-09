import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-10-28
 */
public class StressTest {

	private static AtomicLong timeCount = new AtomicLong(0);
	private static AtomicLong resultCount = new AtomicLong(0);
	private static AtomicLong successCount = new AtomicLong(0);
	private static AtomicLong submitCount = new AtomicLong(0);
	private static AtomicLong exceptionCount = new AtomicLong(0);

	private static int concurrentSize = 50;

	private static ExecutorService executorService = Executors.newFixedThreadPool(concurrentSize);
	public static void main(String[] args) throws IOException, InterruptedException {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 2000; i++) {
			int finalI = i;
			executorService.submit(() -> {
				try {
					String outTradeNo = System.currentTimeMillis() + String.valueOf(finalI);
					test(outTradeNo);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			});
		}
		executorService.shutdown();
		ThreadPoolExecutor executorService = (ThreadPoolExecutor) StressTest.executorService;
		System.out.println(executorService.getCompletedTaskCount());
		StressTest.executorService.awaitTermination(1, TimeUnit.HOURS);
		System.out.println("total Cost" + timeCount.get());
		System.out.println("Count " + resultCount.get());
		System.out.println("submit " + submitCount.get());
		System.out.println("exception " + exceptionCount.get());
		System.out.println("success " + successCount.get());
		System.out.println("Total Count " + (System.currentTimeMillis() - start));
	}

	public static void test(String outTradeNo) throws IOException {
		long start = System.currentTimeMillis();

		try {
			OkHttpClient client = new OkHttpClient();
			submitCount.incrementAndGet();

			MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
			RequestBody body = RequestBody.create(mediaType, "totalFee=1&orderIp=127.0.0.1&authCode=281234567890123457&merchantId=1111&subject=%E7%BB%8F%E5%85%B8%E8%82%89%E5%A4%B9%E9%A6%8D&outTradeNo=" + outTradeNo);
			Request request = new Request.Builder()
					.post(body)
					.addHeader("content-type", "application/x-www-form-urlencoded")
					.addHeader("cache-control", "no-cache")
					.addHeader("postman-token", "265ebc76-86a5-cb3b-3e5e-0e87aa03e68f")
					.build();

			Response response = client.newCall(request).execute();
//		System.out.println(response);
			boolean successful = response.isSuccessful();
			if (successful) {
				String string = response.body().string();
				successCount.incrementAndGet();
//			System.out.println(string);
			}
		} catch (Exception e) {
			exceptionCount.incrementAndGet();
			e.printStackTrace();
		} finally {
			submitCount.incrementAndGet();
		}

		long end = System.currentTimeMillis();
//		System.out.println("Cost " + (end - start));
		timeCount.addAndGet((end - start));
		resultCount.incrementAndGet();
	}
}

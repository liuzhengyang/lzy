import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-11-02gggggggggggggggggggggg
 */
public class GifCrawler {

	public static void crawlIndex() throws IOException {
		Document document = Jsoup.connect("http://soogif.com/").timeout(5000).get();
//		document.select()
	}

	public static void crawlSearchDetail() throws IOException {
		Path gifs = Paths.get("gifs");
		boolean mkdirs = gifs.toFile().mkdirs();
		System.out.println(mkdirs);
		Document document = Jsoup.connect("http://soogif.com/search/%E7%AC%91").timeout(5000).get();
		System.out.println(document);

		Elements select = document.select(".grid .grid-item");
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		OkHttpClient build = builder.build();

		int count = 0;
		for (Element element : select) {
			String replace = element.select("img").attr("src").replace("_jpg", "_s200x0");
		Request.Builder requestBuilder = new Request.Builder();
			Request request = requestBuilder.url(replace).get().build();
			Response execute = build.newCall(request).execute();
			byte[] bytes = execute.body().bytes();
			Path gif = Files.write(Paths.get("gifs/test_" + count++ + ".gif"), bytes);
			System.out.println(gif.toFile().getAbsolutePath());
		}
	}

	public static void main2(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(300);

		OkHttpClient client = new OkHttpClient();
		AtomicLong total = new AtomicLong(0);
		int requestCount = 10000;
		long totalStart = System.currentTimeMillis();
		for (int i = 0; i < requestCount; i ++) {
			int finalI = i;
			executorService.submit(() -> {
				try {
					long start = System.currentTimeMillis();
					String outTradeNo = "presstest" + finalI + start;
					MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
					RequestBody body = RequestBody.create(mediaType, "totalFee=1&orderIp=127.0.0.1&authCode=281234567890123456&merchantId=11112200&subject=%E7%BB%8F%E5%85%B8%E8%82%89%E5%A4%B9%E9%A6%8D&outTradeNo=" + outTradeNo + "&body=body");
					Request request = new Request.Builder()
							.post(body)
							.addHeader("content-type", "application/x-www-form-urlencoded")
							.addHeader("cache-control", "no-cache")
							.addHeader("postman-token", "1103a61d-fa90-c254-339b-4ad3dd69bf7b")
							.build();

					Response response = client.newCall(request).execute();
					long end = System.currentTimeMillis();
					System.out.println(response.body().string() +  " cost " + (end -start));
					response.close();

					total.addAndGet((end - start));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		}
		executorService.shutdown();
		try {
			executorService.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long totalEnd = System.currentTimeMillis();

		System.out.println("avereage " + total.get() / requestCount);
		System.out.println("totalCost " + (totalEnd - totalStart));
	}

	public static void main(String[] args) throws IOException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "{\n \"device_sn\":\"50703666111\",\n \"device_model\":\"APOS A8\",\n \"os\":\"android\",\n \"os_version\":\"5.6.3\",\n \"sdk_version\":\"1.0.0\",\n \"sign\":\"\",  \n \"data\" : {  \n   \"batchno\":\"2016062701\",\n   \"traceno\":\"10038\",\n   \"paytype\":\"barcode_alipay\",\n   \"auth_code\":\"test\",\n   \"total_fee\":\"1\"\n }\n}");
		Request request = new Request.Builder().post(body).url("http://ipos.meituan.com/trade/pospay").build();

		Response response = client.newCall(request).execute();
		System.out.println("Protocol: " + response.protocol());
		System.out.println(response.body().string());
	}
}

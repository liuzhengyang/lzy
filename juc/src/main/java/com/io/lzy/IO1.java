package com.io.lzy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Description.:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-06
 */
public class IO1 {
	public static class Server {
		private volatile boolean stop = false;
		public void listen(int port) throws IOException {
			ServerSocket ss = new ServerSocket(port);
			while(!stop) {
				Socket accept = ss.accept();
				handle(accept);
			}
		}

		private void handle(Socket accept) throws IOException {
			InputStream inputStream = accept.getInputStream();
			OutputStream outputStream = accept.getOutputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			BufferedWriter br = new BufferedWriter(new OutputStreamWriter(outputStream));
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				br.write(line);
				br.flush();
			}
			accept.close();
		}
	}

	public static class Client {

		public void sendMessage(String host, int port) throws IOException {
			Socket socket = new Socket(host, port);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			BufferedWriter br = new BufferedWriter(new OutputStreamWriter(outputStream));
			for (int i = 0; i < 20; i++) {
				br.write("hello");
				br.write("\n");
				br.flush();
			}
			outputStream.close();
		}
	}

	public static void main(String[] args) throws IOException {
//		Executors.newCachedThreadPool().submit(new Runnable() {
//			public void run() {
//				Server server = new Server();
//				try {
//					server.listen(9999);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		Executors.newCachedThreadPool().submit(new Runnable() {
//			public void run() {
//				Client client = new Client();
//				try {
//					client.sendMessage("localhost", 9999);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}
}

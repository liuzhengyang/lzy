package com;

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
 * Description:
 *
 * @author liuzhengyang
 * @version 1.0
 * @since 2016-07-18
 */
public class LocalServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			while(true) {
				Socket accept = serverSocket.accept();
				accept.setSoLinger(true, 0);
				InputStream inputStream = accept.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

				OutputStream outputStream = accept.getOutputStream();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
				String line = null;
				while( (line = br.readLine()) != null) {
					bw.write("hello " + line);
					bw.flush();
					System.out.println(line);
					if (line.length() != 5) {
						accept.close();
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

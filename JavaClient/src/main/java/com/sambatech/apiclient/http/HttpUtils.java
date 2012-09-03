package com.sambatech.apiclient.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

	private static final int BUFFER_SIZE = 1024*1024; //1mb
	private static final int TIMEOUT = 60000; //1mb
	
	public static HttpResponse get(String url) {
		return get(url, TIMEOUT);
	}
	
	public static HttpResponse get(String url, int timeout) {
		URL urlObject = null;
		HttpURLConnection conn = null;
		
		try {
			urlObject = new URL(url);

			conn = (HttpURLConnection) urlObject.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(timeout);
			conn.setRequestProperty("Content-Type", "application/xml");
			
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		return execute(conn);
	}
	
	private static HttpResponse execute(HttpURLConnection connection) {
		HttpResponse httpResponse = new HttpResponse();

		InputStream is = null;
		try {
			connection.connect();
		
			httpResponse.setCode(connection.getResponseCode());
			httpResponse.setMessage(connection.getResponseMessage());
			httpResponse.setUrl(connection.getURL().toExternalForm());
			httpResponse.setHeaders(connection.getHeaderFields());
			
			is = connection.getInputStream();
		
			StringBuffer strbuf = new StringBuffer();
			byte[] buffer = new byte[BUFFER_SIZE];
			int n = 0;
			while (-1 != (n = is.read(buffer))) {
				strbuf.append(new String(buffer, 0, n));
			}
			is.close();

			httpResponse.setResponseBody(strbuf.toString());
			
			return httpResponse;
			
		} catch (IOException e) {
			is = connection.getErrorStream();
			
			StringBuffer strbuf = new StringBuffer();
			byte[] buffer = new byte[BUFFER_SIZE];
			int n = 0;
			try {
				while (-1 != (n = is.read(buffer))) {
					strbuf.append(new String(buffer, 0, n));
				}
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			httpResponse.setResponseBody(strbuf.toString());
			
			return httpResponse;
		}
	}
}

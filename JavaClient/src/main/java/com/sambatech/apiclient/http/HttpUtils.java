package com.sambatech.apiclient.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.sambatech.apiclient.exception.RequestException;

public class HttpUtils {

	private static final int BUFFER_SIZE = 1024*1024; //1mb
	private static final int TIMEOUT = 60000; //1mb
	
	public static HttpRequest get(String url) throws RequestException {
		return get(url, TIMEOUT);
	}
	
	public static HttpRequest get(String url, int timeout) throws RequestException {
		URL urlObject = null;
		HttpURLConnection conn = null;
		
		try {
			urlObject = new URL(url);

			conn = (HttpURLConnection) urlObject.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(timeout);
			conn.setRequestProperty("Content-Type", "application/xml");
			
		} catch (MalformedURLException e) {
			throw new RequestException(e, null);
		} catch (IOException e) {
			throw new RequestException(e, null);
		}
		
		return execute(conn);
	}

	public static HttpRequest put(String url, String body) throws RequestException {
		return put(url, body, TIMEOUT);
	}
	
	public static HttpRequest put(String url, String body, int timeout) throws RequestException {
		URL urlObject = null;
		HttpURLConnection conn = null;
		
		try {
			urlObject = new URL(url);

			conn = (HttpURLConnection) urlObject.openConnection();
			conn.setRequestMethod("PUT");
			conn.setConnectTimeout(timeout);
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setDoOutput(true);
			
		    OutputStreamWriter wsr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		    wsr.write(body);
		    wsr.flush();
			
		} catch (MalformedURLException e) {
			throw new RequestException(e, null);
		} catch (IOException e) {
			throw new RequestException(e, null);
		}
		
		try {
			HttpRequest httpRequest = execute(conn);
			httpRequest.setRequestBody(body);
			return httpRequest;
			
		} catch (RequestException e) {
			e.getHttpRequest().setRequestBody(body);
			throw e;
		}
	}

	public static HttpRequest post(String url, String body) throws RequestException {
		return post(url, body, TIMEOUT);
	}
	
	public static HttpRequest post(String url, String body, int timeout) throws RequestException {
		URL urlObject = null;
		HttpURLConnection conn = null;
		
		try {
			urlObject = new URL(url);

			conn = (HttpURLConnection) urlObject.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(timeout);
			conn.setRequestProperty("Content-Type", "application/xml");
			conn.setDoOutput(true);
			
		    OutputStreamWriter wsr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		    wsr.write(body);
		    wsr.flush();
			
		} catch (MalformedURLException e) {
			throw new RequestException(e, null);
		} catch (IOException e) {
			throw new RequestException(e, null);
		}
		
		try {
			HttpRequest httpRequest = execute(conn);
			httpRequest.setRequestBody(body);
			return httpRequest;
			
		} catch (RequestException e) {
			e.getHttpRequest().setRequestBody(body);
			throw e;
		}
	}
	
	private static HttpRequest execute(HttpURLConnection connection) throws RequestException {
		HttpRequest httpRequest = new HttpRequest();

		InputStream is = null;
		try {
			connection.connect();
		
			httpRequest.setResponseCode(connection.getResponseCode());
			httpRequest.setResponseMessage(connection.getResponseMessage());
			httpRequest.setUrl(connection.getURL().toExternalForm());
			httpRequest.setResponseHeaders(connection.getHeaderFields());
			
			is = connection.getInputStream();
		
			StringBuffer strbuf = new StringBuffer();
			byte[] buffer = new byte[BUFFER_SIZE];
			int n = 0;
			while (-1 != (n = is.read(buffer))) {
				strbuf.append(new String(buffer, 0, n));
			}
			is.close();

			httpRequest.setResponseBody(strbuf.toString());
			
			return httpRequest;
			
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
			
			httpRequest.setResponseBody(strbuf.toString());
			
			throw new RequestException(e, httpRequest);
		}
	}
	
	public static HttpRequest delete(String url) throws RequestException {
		return delete(url, TIMEOUT);
	}
	
	public static HttpRequest delete(String url, int timeout) throws RequestException {
		System.out.println(url);
		
		URL urlObject = null;
		HttpURLConnection conn = null;
		
		try {
			urlObject = new URL(url);

			conn = (HttpURLConnection) urlObject.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setConnectTimeout(timeout);
			conn.setRequestProperty("Content-Type", "application/xml");
			
		} catch (MalformedURLException e) {
			throw new RequestException(e, null);
		} catch (IOException e) {
			throw new RequestException(e, null);
		}
		
		return execute(conn);
	}
}

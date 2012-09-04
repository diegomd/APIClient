package com.sambatech.apiclient.http;

import java.util.Map;

public class HttpResponse {

	private int code;
	private String message;
	private String url;
	private Map headers;
	private String responseBody;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map getHeaders() {
		return headers;
	}
	public void setHeaders(Map headers) {
		this.headers = headers;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	public String getResponseBody() {
		return responseBody;
	}
}

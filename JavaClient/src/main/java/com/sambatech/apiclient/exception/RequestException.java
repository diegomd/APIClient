package com.sambatech.apiclient.exception;

import com.sambatech.apiclient.http.HttpRequest;

public class RequestException extends Exception {
	private static final long serialVersionUID = 8267175534048869168L;
	
	protected HttpRequest httpRequest;

	public RequestException(HttpRequest httpRequest) {
		super();
		this.httpRequest = httpRequest;
	}

	public RequestException(Throwable t, HttpRequest httpRequest) {
		super(t);
		this.httpRequest = httpRequest;
	}

	public RequestException(String cause, HttpRequest httpRequest) {
		super(cause);
		this.httpRequest = httpRequest;
	}

	public RequestException(String cause, Throwable t, HttpRequest httpRequest) {
		super(cause, t);
		this.httpRequest = httpRequest;
	}
	
	public HttpRequest getHttpRequest() {
		return httpRequest ;
	}
}

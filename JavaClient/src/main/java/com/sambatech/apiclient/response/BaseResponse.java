package com.sambatech.apiclient.response;

import javax.xml.bind.annotation.XmlTransient;

import com.sambatech.apiclient.http.HttpRequest;

public class BaseResponse {

	private HttpRequest httpRequest;

	public void setHttpRequest(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	@XmlTransient
	public HttpRequest getHttpRequest() {
		return httpRequest;
	}	
}

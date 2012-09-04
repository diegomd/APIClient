package com.sambatech.apiclient.response;

import com.sambatech.apiclient.http.HttpResponse;

public class BaseResponse {

	private HttpResponse httpResponse;

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}
	
}

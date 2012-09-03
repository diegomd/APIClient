package com.sambatech.apiclient;

import com.sambatech.apiclient.http.HttpResponse;
import com.sambatech.apiclient.http.HttpUtils;

public class LiquidAPIClient {

	private static final String MEDIAS_ENDPOINT = "/medias"; 
	
	private String apiBaseUrl = "http://fast.api.liquidplatform.com/2.0";
	private int timeout = 300;
	private String apiKey;
	
	/****************************************************************************************************
	 **** Constructors **********************************************************************************
	 ****************************************************************************************************/
	public LiquidAPIClient(String apiKey) {
		this.setApiKey(apiKey);
	}
	
	public LiquidAPIClient(String apiKey, String apiBaseUrl, int timeout) {
		this.setApiKey(apiKey);
		this.setApiBaseUrl(apiBaseUrl);
		this.setTimeout(timeout);	
	}
	
	/****************************************************************************************************
	 **** API Methods ***********************************************************************************
	 ****************************************************************************************************/
	public void getMedias() {
		String url = apiBaseUrl + "/" + MEDIAS_ENDPOINT + "?key="+ apiKey +"&limit=2";
		
		HttpResponse httpResponse = HttpUtils.get(url);
		
		System.out.println(httpResponse.getResponseBody());
		
	}
	
	/****************************************************************************************************
	 **** Getters and Setters ***************************************************************************
	 ****************************************************************************************************/
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
	}

	public String getApiBaseUrl() {
		return apiBaseUrl;
	}
}

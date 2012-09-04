package com.sambatech.apiclient;

import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.filter.APIFilterParams;
import com.sambatech.apiclient.http.HttpResponse;
import com.sambatech.apiclient.http.HttpUtils;
import com.sambatech.apiclient.response.Medias;

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
	public Medias getMedias(APIFilter apiFilter) {
		String parameters = getParameters(apiFilter, APIFilterParams.FIRST,
													APIFilterParams.LIMIT, 
													APIFilterParams.SEARCH,
													APIFilterParams.RECURSIVE_CHANNEL,
													APIFilterParams.FILTER,
													APIFilterParams.ORDERBY,
													APIFilterParams.SORT);
		
		String url = apiBaseUrl + "/" + MEDIAS_ENDPOINT + "?key="+ apiKey + parameters;
		HttpResponse httpResponse = HttpUtils.get(url);
		
		Medias medias = new Medias();
		medias.setHttpResponse(httpResponse);
		
		return medias;
	}
	
	/****************************************************************************************************
	 **** Build parameters ******************************************************************************
	 ****************************************************************************************************/
	private String getParameters(APIFilter apiFilter, APIFilterParams ... params) {
		StringBuilder parameters = new StringBuilder();
		
		for( APIFilterParams param : params ) {
			switch(param) {
				case FIRST:
					parameters.append( buildParam( APIFilterParams.FIRST, apiFilter.getFirst()) );
					break;
				case LIMIT:
					parameters.append( buildParam( APIFilterParams.LIMIT, apiFilter.getLimit()) );
					break;
				case SEARCH:
					parameters.append( buildParam( APIFilterParams.SEARCH, apiFilter.getSearch()) );
					break;
				case RECURSIVE_CHANNEL:
					parameters.append( buildParam( APIFilterParams.RECURSIVE_CHANNEL, apiFilter.getRecursiveChannel()) );
					break;
				case FILTER:
					parameters.append( buildParam( APIFilterParams.FILTER, apiFilter.getFilter()) );
					break;
				case ORDERBY:
					parameters.append( buildParam( APIFilterParams.ORDERBY, apiFilter.getOrderBy()) );
					break;
				case SORT:
					parameters.append( buildParam( APIFilterParams.SORT, apiFilter.getSort()) );
					break;
			}
		}
		
		return parameters.toString();
	}
	
	private String buildParam(Object name, Object value) {
		if(value == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("&");
		sb.append(name);
		sb.append("=");
		sb.append(value);
		return sb.toString();
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

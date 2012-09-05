package com.sambatech.apiclient;

import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.filter.APIFilterParams;
import com.sambatech.apiclient.http.HttpRequest;
import com.sambatech.apiclient.http.HttpUtils;
import com.sambatech.apiclient.model.Medias;
import com.sambatech.apiclient.model.SimpleResult;
import com.sambatech.apiclient.parser.JAXBParser;

public class LiquidAPIClient {

	private static final String MEDIAS_ENDPOINT = "medias";
	private static final String MEDIAS_COUNT_ENDPOINT = "medias/count";
	
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
	
	/**
	 * /medias 
	 */
	public Medias getMedias(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = getMediasRequest(apiFilter, true);
		
		// Serialize object
		Medias medias = JAXBParser.stringToObject(httpRequest.getResponseBody(), Medias.class);
		
		// Http Response info
		medias.setHttpRequest(httpRequest);
		
		return medias;
	}
	
	/**
	 * Build Request to /media
	 */
	public HttpRequest getMediasRequest(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT);
		String parameters = getParameters(apiFilter, APIFilterParams.FIRST,
													APIFilterParams.LIMIT, 
													APIFilterParams.SEARCH,
													APIFilterParams.RECURSIVE_CHANNEL,
													APIFilterParams.FILTER,
													APIFilterParams.ORDERBY,
													APIFilterParams.SORT);
		
		String url = baseUrl + parameters;
		
		if(makeRequest) {
			return HttpUtils.get(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;
		
	}
	
	/**
	 * /medias/count 
	 */
	public int getMediasCount() throws RequestException, ParserException {
		HttpRequest httpRequest = getMediasCountRequest(true);
		
		// Serialize object
		SimpleResult result = JAXBParser.stringToObject(httpRequest.getResponseBody(), SimpleResult.class);
		
		if(result == null || result.getValue() == null) {
			return 0;
		}
		
		return Integer.valueOf(result.getValue());
	}
	
	/**
	 * Build request to /medias/count
	 */
	public HttpRequest getMediasCountRequest(boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(MEDIAS_COUNT_ENDPOINT);
		
		if(makeRequest) {
			return HttpUtils.get(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;
	}
	
	/****************************************************************************************************
	 **** Build functions *******************************************************************************
	 ****************************************************************************************************/
	private String buildBaseUrl(String ... paths) {
		StringBuilder sb = new StringBuilder(apiBaseUrl);
		
		for(String path: paths) {
			sb.append("/").append(path);
		}
		sb.append("?key=").append(apiKey);
		
		return sb.toString();
	}
	
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

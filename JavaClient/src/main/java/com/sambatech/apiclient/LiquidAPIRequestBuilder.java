package com.sambatech.apiclient;

import com.sambatech.apiclient.body.MediaUpdate;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.filter.APIFilterParams;
import com.sambatech.apiclient.http.HttpRequest;
import com.sambatech.apiclient.http.HttpUtils;
import com.sambatech.apiclient.model.Channel;
import com.sambatech.apiclient.parser.JAXBParser;

public class LiquidAPIRequestBuilder {

	private static final String MEDIAS_ENDPOINT 		= "medias";
	private static final String MEDIAS_COUNT_ENDPOINT 	= "medias/count";
	private static final String MEDIAS_URLS_ENDPOINT 	= "medias/urls";
	private static final String CHANNELS_ENDPOINT 		= "channels";
	
	private String apiBaseUrl = "http://fast.api.liquidplatform.com/2.0";
	private int timeout = 300;
	private String apiKey;
	
	/****************************************************************************************************
	 **** Constructors **********************************************************************************
	 ****************************************************************************************************/
	public LiquidAPIRequestBuilder(String apiKey) {
		this.setApiKey(apiKey);
	}
	
	public LiquidAPIRequestBuilder(String apiKey, String apiBaseUrl, int timeout) {
		this.setApiKey(apiKey);
		this.setApiBaseUrl(apiBaseUrl);
		this.setTimeout(timeout);	
	}
	
	
	/****************************************************************************************************
	 **** API Request Builders **************************************************************************
	 ****************************************************************************************************/
	/**
	 * Build Request to GET /medias
	 */
	public HttpRequest getMedias(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
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
	 * Build request to GET /medias/count
	 */
	public HttpRequest getMediasCount(boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(MEDIAS_COUNT_ENDPOINT);
		
		if(makeRequest) {
			return HttpUtils.get(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;
	}
	
	/**
	 * Build Request to GET /medias/{id}
	 */
	public HttpRequest getMediaId(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId());
		String parameters = getParameters(apiFilter,APIFilterParams.FILTER);
		String url = baseUrl + parameters;

		if(makeRequest) {
			return HttpUtils.get(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;	
	}
	
	/**
	 * Build Request to PUT /medias/{id}
	 */
	public HttpRequest updateMediaId(APIFilter apiFilter, MediaUpdate mediaUpdate, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId());
		String parameters = getParameters(apiFilter,APIFilterParams.FILTER);
		String url = baseUrl + parameters;

		String body = JAXBParser.objectToString(mediaUpdate, MediaUpdate.class);
		
		if(makeRequest) {
			return HttpUtils.put(url, body);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		httpRequest.setRequestBody(body);
		return httpRequest;
	}
	
	/**
	 * Build Request to DELETE /medias/{id}
	 */
	public HttpRequest deleteMediaId(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId());
		String parameters = getParameters(apiFilter,APIFilterParams.FILTER);
		String url = baseUrl + parameters;

		
		if(makeRequest) {
			return HttpUtils.delete(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;
	}

	
	/**
	 * Build Request to GET /medias/urls/{id}
	 */
	public HttpRequest getMediaIdUrls(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_URLS_ENDPOINT, apiFilter.getMediaId());
		String parameters = getParameters(apiFilter);
		String url = baseUrl + parameters;

		if(makeRequest) {
			return HttpUtils.get(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;	
	}
	
	/**
	 * Build Request to GET /channels
	 */
	public HttpRequest getChannels(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(CHANNELS_ENDPOINT);
		String parameters = getParameters(apiFilter, APIFilterParams.FIRST,
													APIFilterParams.LIMIT,
													APIFilterParams.FILTER);
		String url = baseUrl + parameters;

		if(makeRequest) {
			return HttpUtils.get(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;	
	}
	
	/**
	 * Build Request to PUT /channels
	 */
	public HttpRequest addChannel(APIFilter apiFilter, Channel channel, boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(CHANNELS_ENDPOINT);
		String body = JAXBParser.objectToString(channel, Channel.class);
		
		if(makeRequest) {
			return HttpUtils.post(url, body);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		httpRequest.setRequestBody(body);
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

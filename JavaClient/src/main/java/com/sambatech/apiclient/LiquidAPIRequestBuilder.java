package com.sambatech.apiclient;

import java.util.ArrayList;
import java.util.List;

import com.sambatech.apiclient.body.MediaUpdate;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.filter.APIFilterParams;
import com.sambatech.apiclient.http.Cookie;
import com.sambatech.apiclient.http.HttpRequest;
import com.sambatech.apiclient.http.HttpUtils;
import com.sambatech.apiclient.model.Channel;
import com.sambatech.apiclient.model.View;
import com.sambatech.apiclient.parser.JAXBParser;

public class LiquidAPIRequestBuilder {

	private static final String MEDIAS_ENDPOINT 	= "medias";
	private static final String COUNT_ENDPOINT 		= "count";
	private static final String RATINGS_ENDPOINT 	= "ratings";
	private static final String RATING_ENDPOINT 	= "rating";
	private static final String VIEWS_ENDPOINT 		= "views";
	private static final String URLS_ENDPOINT 		= "urls";
	private static final String THUMBS_ENDPOINT 	= "thumbs";
	private static final String RELATED_ENDPOINT 	= "related";
	private static final String CHANNELS_ENDPOINT	= "channels";
	
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
	
		return doGet(url, makeRequest);
	}
	
	/**
	 * Build request to GET /medias/count
	 */
	public HttpRequest getMediasCount(boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(MEDIAS_ENDPOINT, COUNT_ENDPOINT);
		
		return doGet(url, makeRequest);
	}
	
	/**
	 * Build Request to GET /medias/ratings
	 */
	public HttpRequest getMediasRatings(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, RATINGS_ENDPOINT);
		String parameters = getParameters(apiFilter, APIFilterParams.FIRST,
													APIFilterParams.LIMIT,
													APIFilterParams.LAST_MODIFIED,
													APIFilterParams.FILTER);
		
		String url = baseUrl + parameters;
		
		return doGet(url, makeRequest);
	}
	
	
	/**
	 * Build Request to GET /medias/views
	 */
	public HttpRequest getMediasViews(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, VIEWS_ENDPOINT);
		String parameters = getParameters(apiFilter, APIFilterParams.FIRST,
													APIFilterParams.LIMIT,
													APIFilterParams.LAST_MODIFIED);
		
		String url = baseUrl + parameters;
		
		return doGet(url, makeRequest);
	}
	
	
	/**
	 * Build Request to GET /medias/{id}
	 */
	public HttpRequest getMediaId(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId());
		String parameters = getParameters(apiFilter,APIFilterParams.FILTER);
		String url = baseUrl + parameters;

		return doGet(url, makeRequest);	
	}
	
	/**
	 * Build Request to PUT /medias/{id}
	 */
	public HttpRequest updateMediaId(APIFilter apiFilter, MediaUpdate mediaUpdate, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId());
		String parameters = getParameters(apiFilter,APIFilterParams.FILTER);
		String url = baseUrl + parameters;

		String body = JAXBParser.objectToString(mediaUpdate, MediaUpdate.class);
		
		return doPut(url, body, makeRequest);
	}
	
	/**
	 * Build Request to DELETE /medias/{id}
	 */
	public HttpRequest deleteMediaId(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId());
		String parameters = getParameters(apiFilter,APIFilterParams.FILTER);
		String url = baseUrl + parameters;

		
		return doDelete(url, makeRequest);
	}

	
	/**
	 * Build Request to GET /medias/urls/{id}
	 */
	public HttpRequest getMediaIdUrls(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(MEDIAS_ENDPOINT, URLS_ENDPOINT, apiFilter.getMediaId());

		return doGet(url, makeRequest);	
	}
	
	/**
	 * Build Request to POST /medias/views/{id}
	 */
	public HttpRequest addMediaIdViews(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, VIEWS_ENDPOINT, apiFilter.getMediaId());
		String parameters = getParameters(apiFilter, APIFilterParams.QUARTER);
		String url = baseUrl + parameters;

		List<Cookie> cookies = getCookies(apiFilter);
		return doPost(url, "", cookies, makeRequest);	
	}

	
	/**
	 * Build Request to GET /medias/{id}/rating
	 */
	public HttpRequest getMediaIdRating(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId(), RATING_ENDPOINT);

		return doGet(url, makeRequest);	
	}
	
	
	/**
	 * Build Request to GET /medias/{id}/related
	 */
	public HttpRequest getMediaIdRelated(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId(), RELATED_ENDPOINT);
		String parameters = getParameters(apiFilter, APIFilterParams.FIRST,
													APIFilterParams.LIMIT,
													APIFilterParams.FILTER);
		
		String url = baseUrl + parameters;
		
		return doGet(url, makeRequest);	
	}
	

	/**
	 * Build Request to GET /medias/{id}/thumbs
	 */
	public HttpRequest getMediaIdThumbs(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId(), THUMBS_ENDPOINT);
		String parameters = getParameters(apiFilter,APIFilterParams.FILTER);
		
		String url = baseUrl + parameters;
		
		return doGet(url, makeRequest);	
	}
	
	
	/**
	 * Build Request to GET /medias/{id}/views
	 */
	public HttpRequest getMediaIdViews(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId(), VIEWS_ENDPOINT);

		return doGet(url, makeRequest);	
	}
	
	/**
	 * Build Request to GET /medias/urls/{mediaId}/{outputName}
	 */
	public HttpRequest getMediaIdOutputUrls(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(MEDIAS_ENDPOINT, URLS_ENDPOINT, apiFilter.getMediaId(), apiFilter.getOutputName());

		return doGet(url, makeRequest);	
	}
	
	
	/**
	 * Build Request to POST /medias/{mediaId}/views/{outputName}
	 */
	public HttpRequest addMediaIdOutputViews(APIFilter apiFilter, boolean makeRequest) throws RequestException, ParserException {
		String baseUrl = buildBaseUrl(MEDIAS_ENDPOINT, apiFilter.getMediaId(), VIEWS_ENDPOINT, apiFilter.getOutputName());
		String parameters = getParameters(apiFilter, APIFilterParams.QUARTER);
		String url = baseUrl + parameters;

		List<Cookie> cookies = getCookies(apiFilter);
		return doPost(url, "", cookies, makeRequest);	
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

		return doGet(url, makeRequest);	
	}
	
	/**
	 * Build Request to PUT /channels
	 */
	public HttpRequest addChannel(APIFilter apiFilter, Channel channel, boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(CHANNELS_ENDPOINT);
		String body = JAXBParser.objectToString(channel, Channel.class);
		
		return doPost(url, body, null, makeRequest);
	}
	
	/**
	 * Build request to GET /channels/count
	 */
	public HttpRequest getChannelsCount(boolean makeRequest) throws RequestException, ParserException {
		String url = buildBaseUrl(CHANNELS_ENDPOINT, COUNT_ENDPOINT);
		
		return doGet(url, makeRequest);
	}
	
	
	/****************************************************************************************************
	 **** Auxiliary Functions ***************************************************************************
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
				case LAST_MODIFIED:
					parameters.append( buildParam( APIFilterParams.LAST_MODIFIED, apiFilter.getLastModified()) );
					break;
				case SESSION_ID:
					parameters.append( buildParam( APIFilterParams.SESSION_ID, apiFilter.getSessionId()) );
					break;
				case QUARTER:
					parameters.append( buildParam( APIFilterParams.QUARTER, apiFilter.getQuarter()) );
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
	
	private List<Cookie> getCookies(APIFilter apiFilter) {
		if(apiFilter != null && apiFilter.getSessionId() != null && !apiFilter.getSessionId().equals("")) {
			Cookie cookie = new Cookie();
			cookie.setName(APIFilterParams.SESSION_ID.toString());
			cookie.setValue(apiFilter.getSessionId());
			
			List<Cookie> cookies = new ArrayList<Cookie>();
			cookies.add(cookie);
			
			return cookies;
		}
		
		return null;
	}
	
	private HttpRequest doGet(String url, boolean makeRequest) throws RequestException {
		if(makeRequest) {
			return HttpUtils.get(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;
	}
	
	private HttpRequest doPut(String url, String body, boolean makeRequest) throws RequestException {
		if(makeRequest) {
			return HttpUtils.put(url, body);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		httpRequest.setRequestBody(body);
		return httpRequest;
	}
	
	private HttpRequest doDelete(String url, boolean makeRequest) throws RequestException {
		if(makeRequest) {
			return HttpUtils.delete(url);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		return httpRequest;
	}
	
	private HttpRequest doPost(String url, String body, List<Cookie> cookies, boolean makeRequest) throws RequestException {
		if(makeRequest) {
			return HttpUtils.post(url, body, cookies);
		}
		
		HttpRequest httpRequest = new HttpRequest();
		httpRequest.setUrl(url);
		httpRequest.setRequestBody(body);
		return httpRequest;
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

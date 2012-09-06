package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import shared.Utils;
import views.html.playground;

import com.sambatech.apiclient.LiquidAPIRequestBuilder;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.filter.OrderBy;
import com.sambatech.apiclient.filter.Sort;
import com.sambatech.apiclient.http.HttpRequest;

import controllers.enums.Endpoint;
import controllers.response.PlaygroundResponse;

public class Playground extends Controller {
	
	/****************************
	 * Endpoint methods
	 ****************************/
	public static Result index() {
		APIRequest request = getForm(APIRequest.class);
		
		APIFilter apiFilter = getAPIFilter(request);
		
		PlaygroundResponse playgroundResponse = getPlaygroundResponse(request.apikey, request.endpoint, request.apiBaseUrl, request.timeout, apiFilter);
		
		return ok(playground.render(playgroundResponse));
	}
	
	/****************************
	 * Methods switch
	 * @param timeout 
	 * @param apiBaseUrl 
	 ****************************/
	private static PlaygroundResponse getPlaygroundResponse(String apikey, Endpoint endpoint, String apiBaseUrl, Integer timeout, APIFilter apiFilter) {
		PlaygroundResponse playgroundResponse = new PlaygroundResponse();
		playgroundResponse.endpoint = endpoint;
		
		// GET Request
		if (apikey == null) {
			playgroundResponse.url = "...";
			playgroundResponse.responseBody = "...";
			return playgroundResponse;
		}
		
		// Init API Client
		String myApiBaseUrl = (apiBaseUrl != null && !apiBaseUrl.equals("")) ? apiBaseUrl : null;
		LiquidAPIRequestBuilder requestBuilder = null;
		if(myApiBaseUrl != null && timeout != null) {
			requestBuilder = new LiquidAPIRequestBuilder(apikey, myApiBaseUrl, timeout);
		} else {
			requestBuilder = new LiquidAPIRequestBuilder(apikey);
		}
		
		HttpRequest httpRequest = null;
		
		// Choose method
		try {
			
			switch(endpoint) {
				case MEDIAS:
					httpRequest = requestBuilder.getMedias(apiFilter, true);
					break;
				case MEDIAS_COUNT:
					httpRequest = requestBuilder.getMediasCount(true);
					break;
			}
			
			if( endpoint.equals("/medias") ) {
			}
			
			String responseBody = Utils.transformXML( httpRequest.getResponseBody() );
			
			playgroundResponse.url = httpRequest.getUrl();
			playgroundResponse.responseBody = responseBody;
			
		} catch (RequestException e) {
			playgroundResponse.url = e.getHttpRequest().getUrl();
			playgroundResponse.responseBody = e.getHttpRequest().getResponseBody();
		} catch (ParserException e) {
			playgroundResponse.url = "...";
			playgroundResponse.responseBody = "Sorry, an error has ocurred while trying to get response.";
		}
		
		return playgroundResponse;
	}

	/****************************
	 * Request maps 
	 ****************************/
	public static class APIRequest{
		public Endpoint endpoint;
		public String apikey;
		
		public String apiBaseUrl;
		public Integer timeout;
		
		public Integer first;
		public Integer limit;
		public String search;
		public Boolean recursiveChannel;
		public String filter;
		public OrderBy orderBy;
		public Sort sort;
		
		@Override
		public String toString() {
			return "APIRequest [endpoint=" + endpoint + ", apikey=" + apikey
					+ ", first=" + first + ", limit=" + limit + ", search="
					+ search + ", recursiveChannel=" + recursiveChannel
					+ ", filter=" + filter + ", orderBy=" + orderBy + ", sort="
					+ sort + "]";
		}
		
	}

	

	/****************************
	 * Helper methods
	 ****************************/
	static <C> C getForm(Class<C> c) {
		
		Form<C> formResult = form(c);
		C result = formResult.bindFromRequest().get();

		return result;		
	}
	
	private static APIFilter getAPIFilter(APIRequest request) {
		
		System.out.println(request.toString());
		
		APIFilter apiFilter = new APIFilter();

		if (request.first != null)
			apiFilter.setFirst(request.first);
		
		if (request.limit != null)
			apiFilter.setLimit(request.limit);
		
		if (request.recursiveChannel != null)
			apiFilter.setRecursiveChannel(request.recursiveChannel);
		
		if (request.filter != null && request.filter.length() > 0) 
			apiFilter.setFilter(request.filter);
		
		if (request.search != null && request.search.length() > 0)
			apiFilter.setSearch(request.search);
		
		
		if (request.orderBy != null)
			apiFilter.setOrderBy(request.orderBy);
		
		
		if (request.sort != null)
			apiFilter.setSort(request.sort);
		
		
		return apiFilter;
	}

}

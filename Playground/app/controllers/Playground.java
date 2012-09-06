package controllers;

import java.util.Calendar;

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
import com.sambatech.apiclient.body.MediaUpdate;

import controllers.enums.Endpoint;
import controllers.enums.Method;
import controllers.response.PlaygroundResponse;

public class Playground extends Controller {
	
	/****************************
	 * Endpoint methods
	 ****************************/
	public static Result index() {
		APIRequest request = getForm(APIRequest.class);
		
		APIFilter apiFilter = getAPIFilter(request);
		
		PlaygroundResponse playgroundResponse = getPlaygroundResponse(request, apiFilter);
		
		return ok(playground.render(playgroundResponse));
	}
	
	/****************************
	 * Methods switch
	 * @param timeout 
	 * @param apiBaseUrl 
	 ****************************/
	private static PlaygroundResponse getPlaygroundResponse(APIRequest request, APIFilter apiFilter) {
		PlaygroundResponse playgroundResponse = new PlaygroundResponse();
		playgroundResponse.endpoint = request.endpoint;
		
		// GET Request
		if (request.apikey == null) {
			playgroundResponse.url = "...";
			playgroundResponse.responseBody = "...";
			return playgroundResponse;
		}
		
		// Init API Client
		String myApiBaseUrl = (request.apiBaseUrl != null && !request.apiBaseUrl.equals("")) ? request.apiBaseUrl : null;
		LiquidAPIRequestBuilder requestBuilder = null;
		if(myApiBaseUrl != null && request.timeout != null) {
			requestBuilder = new LiquidAPIRequestBuilder(request.apikey, myApiBaseUrl, request.timeout);
		} else {
			requestBuilder = new LiquidAPIRequestBuilder(request.apikey);
		}
		
		HttpRequest httpRequest = null;
		
		// Choose method
		try {
			
			switch(request.endpoint) {
				case MEDIAS:
					httpRequest = requestBuilder.getMedias(apiFilter, true);
					break;
				case MEDIAS_COUNT:
					httpRequest = requestBuilder.getMediasCount(true);
					break;
				case MEDIAS_RATINGS:
					httpRequest = requestBuilder.getMediasRatings(apiFilter, true);
					break;
				case MEDIAS_VIEWS:
					httpRequest = requestBuilder.getMediasViews(apiFilter, true);
					break;
				case MEDIAS_MEDIAID:
					switch(request.method) {
						case PUT:
							MediaUpdate mediaUpdate = getMediaUpdate(request);
							httpRequest = requestBuilder.updateMediaId(apiFilter, mediaUpdate, true);
							break;
						case DELETE:
							httpRequest = requestBuilder.deleteMediaId(apiFilter, true);
							break;
						default:	
							httpRequest = requestBuilder.getMediaId(apiFilter, true);
							break;
					}
					break;
				case MEDIAS_URLS_MEDIAFILEID:
					httpRequest = requestBuilder.getMediaIdUrls(apiFilter, true);
					break;
				case MEDIAS_MEDIAID_RATING:
					httpRequest = requestBuilder.getMediaIdRating(apiFilter, true);
					break;
				case MEDIAS_MEDIAID_VIEWS:
					httpRequest = requestBuilder.getMediaIdViews(apiFilter, true);
					break;
				case CHANNELS:
					httpRequest = requestBuilder.getChannels(apiFilter, true);
					break;
				
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
		public Method method;
		
		public String apiBaseUrl;
		public Integer timeout;
		
		public Integer first;
		public Integer limit;
		public String search;
		public Boolean recursiveChannel;
		public String filter;
		public OrderBy orderBy;
		public Sort sort;
		public String mediaId;
		public String mediaFileId;
		
		//MediaUpdate
		public String postTitle;
		public Integer postChannelId;
		public String postDescription;
		public String postShortDescription;
		public Boolean postRestrict;
		public Boolean postHighlighted;
		public String postSecondURL;
		public String postTags;
		public Calendar postPublishDate;
		public Calendar postUnPublishDate;
		
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
		
		if (request.mediaId != null && request.mediaId.length() > 0) 
			apiFilter.setMediaId(request.mediaId);
	
		if (request.mediaFileId != null && request.mediaFileId.length() > 0)
			// TODO: Mudar para setMediaFileId
			apiFilter.setMediaId(request.mediaFileId);
		
		return apiFilter;
	}
	
	private static MediaUpdate getMediaUpdate(APIRequest request) {
		MediaUpdate mediaUpdate = new MediaUpdate();
		
		if (request.postTitle != null && request.postTitle.length() > 0)
			mediaUpdate.setTitle(request.postTitle);
		
		if (request.postChannelId != null)
			mediaUpdate.setChannelId(request.postChannelId);
		
		return mediaUpdate;
	}

}

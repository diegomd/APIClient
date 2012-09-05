package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import shared.Utils;

import com.sambatech.apiclient.LiquidAPIClient;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.filter.OrderBy;
import com.sambatech.apiclient.filter.Sort;
import com.sambatech.apiclient.http.HttpRequest;
import views.html.playground;

public class Playground extends Controller {
	
	/****************************
	 * Endpoint methods
	 ****************************/
	public static Result index() {
		
		APIRequest apiRequest = getForm(APIRequest.class);
		
		if (apiRequest.apikey == null) {
			return ok(playground.render("...","..."));
		}
		
		APIFilter apiFilter = getAPIFilter(apiRequest);

		LiquidAPIClient liquidAPIClient = new LiquidAPIClient(apiRequest.apikey);
		HttpRequest httpRequest;
		try {
			httpRequest = liquidAPIClient.getMediasRequest(apiFilter, true);
			String responseBody = httpRequest.getResponseBody();
			String requestedUrl = httpRequest.getUrl();
			responseBody = Utils.transformXML(responseBody);
			System.out.println(responseBody);
			return ok(playground.render(requestedUrl, responseBody));

		} catch (RequestException e) {
			return ok(playground.render(e.getHttpRequest().getUrl(),e.getHttpRequest().getResponseBody()));
		} catch (ParserException e) {
			return ok(playground.render("...", "Sorry, an error has ocurred while trying to get response."));
		}		
	}
	
	public static void main (String [] args) {
		/*
		System.out.println(Utils.transformXML("<teste><object>bla</object></teste>"));		
		
		APIRequest request = new APIRequest();
		request.apikey = "18a3dcacfff63aca5dd93594eb658098";
		request.limit = 1;
		
		APIFilter apiFilter = getAPIFilter(request);

		LiquidAPIClient liquidAPIClient = new LiquidAPIClient(request.apikey);
		Medias medias = liquidAPIClient.getMedias(apiFilter);
		
		String responseBody = medias.getHttpResponse().getResponseBody();		
		System.out.println(Utils.transformXML(responseBody));
		*/
	}
	

	/****************************
	 * Request maps 
	 ****************************/
	public static class APIRequest{
		public String endpoint;
		public String apikey;
		
		public Integer first;
		public Integer limit;
		public String search;
		public Boolean recursiveChannel;
		public String filter;
		public String orderBy;
		public String sort;
		
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
		
		
		if (request.orderBy != null && request.orderBy.length() > 0)
			apiFilter.setOrderBy(OrderBy.valueOf(request.orderBy));
		
		
		if (request.sort != null && request.sort.length() > 0)
			apiFilter.setSort(Sort.ASC);
		
		
		return apiFilter;
	}

}

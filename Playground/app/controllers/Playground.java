package controllers;

import java.util.HashMap;
import java.util.Map;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import shared.HTTPUtils;
import shared.Utils;
import views.html.playground;

public class Playground extends Controller {


	public static Result index() {
		/*
		LiquidAPIClient apiClient = new LiquidAPIClient();
		apiClient.setTeste("ILUYILY");
		*/
		
		APIRequest request = getForm(APIRequest.class);
		
		if (request == null || request.apikey == null) {
			return ok(playground.render("test","..."));
		}
		else {
			
			//TODO use the 'request' object 
			
			Map<String, String> queryParameters = new HashMap<String, String>();
			queryParameters.put("key", "18a3dcacfff63aca5dd93594eb658098");
			queryParameters.put("first", "0");
			queryParameters.put("limit", "30");
			queryParameters.put("orderBy", "lastModified");
			queryParameters.put("sort", "desc");
			String response = HTTPUtils.getStringResponse("http://fast.api.liquidplatform.com/2.0/medias/", queryParameters);
			
			return ok(playground.render("test",Utils.transformXML(response)));			
		}
		
	}

	
	static <C> C getForm(Class<C> c) {
		
		Form<C> formResult = form(c);
		C result = formResult.bindFromRequest().get();

		return result;		
	}
	
	public static class APIRequest{
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
			return "APIRequest [APIKey=" + apikey + ", first=" + first
					+ ", limit=" + limit + ", search=" + search
					+ ", recursiveChannel=" + recursiveChannel + ", filter="
					+ filter + ", orderBy=" + orderBy + ", sort=" + sort + "]";
		}
		
	}
}

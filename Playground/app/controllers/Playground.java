package controllers;

import com.sambatech.apiclient.LiquidAPIClient;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.playground;

public class Playground extends Controller {

	public static Result index() {
		LiquidAPIClient apiClient = new LiquidAPIClient();
		apiClient.setTeste("ILUYILY");
		
		//int limit = getForm("limit")
		
		return ok(playground.render(apiClient.getTeste()));
	}
	
}

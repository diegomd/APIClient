package com.sambatech;

import com.sambatech.apiclient.LiquidAPIClient;

public class Main {

	public static void main(String[] args) {
		
		LiquidAPIClient liquidAPIClient = new LiquidAPIClient("ed6eeb4aaea614c7ece8d558e31d3bb5");
		
		liquidAPIClient.getMedias();
		
	}
}

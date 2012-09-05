package com.sambatech;

import com.sambatech.apiclient.LiquidAPIClient;
import com.sambatech.apiclient.body.MediaUpdate;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.model.Media;

public class Main {

	public static void main(String[] args) {
		APIFilter apiFilter = new APIFilter();

		apiFilter.setMediaId("830989fa34211c1c40cfe30b14d70315");

		MediaUpdate mediaUpdate = new MediaUpdate();
		mediaUpdate.setTitle("Novo Título 4");
		
		try {
			LiquidAPIClient liquidAPIClient = new LiquidAPIClient("3c7de826b1e566a1f2a282a1e7088495");
			
			Media media = liquidAPIClient.getMediaId(apiFilter);
			System.out.println(media.getId());
			
		} catch (RequestException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}		
	}
}

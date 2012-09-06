package com.sambatech;

import com.sambatech.apiclient.LiquidAPIClient;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.model.Channel;
import com.sambatech.apiclient.model.Status;
import com.sambatech.apiclient.model.Thumbnail;
import com.sambatech.apiclient.model.Thumbnails;
import com.sambatech.apiclient.model.View;

/*
DONE		/medias
DONE		/medias/count
DONE		/medias/ratings
DONE		/medias/views
DONE		/medias/{mediaId}				GET
DONE		/medias/{mediaId}				PUT
DONE		/medias/{mediaId}				DELETE
DONE		/medias/urls/{mediaFileId}
DONE		/medias/views/{mediaFileId}
DONE		/medias/{mediaId}/rating
DONE		/medias/{mediaId}/related
DONE		/medias/{mediaId}/thumbs
DONE		/medias/{mediaId}/views
DONE		/medias/urls/{mediaId}/{outputName}
DONE		/medias/{mediaId}/views/{outputName}
DONE		/channels
			/channels/count
			/channels/{channelId}
			/outputs/count
			/outputs
			/report/detail/traffic
			/report/detail/medias
			/report/detail/views
			/report/detail/storage
*/

public class Main {

	public static void main(String[] args) {
		APIFilter apiFilter = new APIFilter();
//		apiFilter.setMediaId("7982432c9020b6370f33fffb44d85d9b");
//		apiFilter.setSessionId("7982432c9020b6370f33fffb44d85d9b");
//		apiFilter.setQuarter(25);
//		apiFilter.setOutputName("_RAW");
		apiFilter.setChannelId("4");
		
		try {
			// EAD
			//LiquidAPIClient liquidAPIClient = new LiquidAPIClient("3c7de826b1e566a1f2a282a1e7088495");
			// Startup
			//LiquidAPIClient liquidAPIClient = new LiquidAPIClient("7725de9b61038815f8cd3f16d3367cef");
			// Relatorio GA
			LiquidAPIClient liquidAPIClient = new LiquidAPIClient("e25e572478af8d4255cb9f7989dbe491");
			
			Channel channel = liquidAPIClient.getChannelId(apiFilter);
			
		} catch (RequestException e) {
			System.out.println("REQUEST ERROR");
			System.out.println(e.getHttpRequest().getUrl());
			System.out.println(e.getHttpRequest().getResponseCode());
			System.out.println(e.getHttpRequest().getResponseBody());
		} catch (ParserException e) {
			e.printStackTrace();
		}		
	}
}

package com.sambatech;

import com.sambatech.apiclient.LiquidAPIClient;
import com.sambatech.apiclient.body.MediaUpdate;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.model.Channel;
import com.sambatech.apiclient.model.Media;
/*
DONE		/medias
DONE		/medias/count
USELESS		/medias/ratings
USELESS		/medias/views
DONE		/medias/{mediaId}
DONE		/medias/urls/{mediaFileId}
			/medias/views/{mediaFileId}
			/medias/{mediaId}/rating
			/medias/{mediaId}/related
			/medias/{mediaId}/thumbs
			/medias/{mediaId}/views
			/medias/urls/{mediaId}/{outputName}
			/medias/{mediaId}/views/{outputName}
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

		apiFilter.setMediaId("830989fa34211c1c40cfe30b14d70315");
		//apiFilter.setMediaId("811b312f78ade066511597a77df2c36f");

		Channel channel = new Channel();
		channel.setName("Categoria Teste 4");
		channel.setParent(96l);
		
		try {
			// EAD
			LiquidAPIClient liquidAPIClient = new LiquidAPIClient("3c7de826b1e566a1f2a282a1e7088495");
			// Startup
			//LiquidAPIClient liquidAPIClient = new LiquidAPIClient("7725de9b61038815f8cd3f16d3367cef");
			
			liquidAPIClient.getChannels(apiFilter);			
			
		} catch (RequestException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}		
	}
}

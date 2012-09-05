package com.sambatech;

import com.sambatech.apiclient.LiquidAPIClient;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.model.Media;
import com.sambatech.apiclient.model.Medias;

public class Main {

	public static void main(String[] args) {
		APIFilter apiFilter = new APIFilter();

		apiFilter.setFirst(new Integer(0));
		apiFilter.setLimit(new Integer(2));
		//apiFilter.setSearch("gg");
		//apiFilter.setRecursiveChannel(true);
		//apiFilter.setFilter("filt");
		//apiFilter.setOrderBy(OrderBy.TITLE);
		//apiFilter.setSort(Sort.ASC);

		try {
			LiquidAPIClient liquidAPIClient = new LiquidAPIClient("ed6eeb4aaea614c7ece8d558e31d3bb5");
			//int count = liquidAPIClient.getMediasCount();
			//System.out.println(count);
			
			Medias medias = liquidAPIClient.getMedias(apiFilter);
			for(Media media : medias.getMediaList()) {
				System.out.println(media.getId());
			}
			
		} catch (RequestException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}		
	}
}

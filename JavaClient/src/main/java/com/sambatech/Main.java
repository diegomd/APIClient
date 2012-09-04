package com.sambatech;

import com.sambatech.apiclient.LiquidAPIClient;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.filter.OrderBy;
import com.sambatech.apiclient.filter.Sort;
import com.sambatech.apiclient.response.Medias;

public class Main {

	public static void main(String[] args) {
		APIFilter apiFilter = new APIFilter();

		apiFilter.setFirst(new Integer(5));
		apiFilter.setLimit(new Integer(2));
		//apiFilter.setSearch("gg");
		apiFilter.setRecursiveChannel(true);
		//apiFilter.setFilter("filt");
		apiFilter.setOrderBy(OrderBy.TITLE);
		apiFilter.setSort(Sort.ASC);
		
		LiquidAPIClient liquidAPIClient = new LiquidAPIClient("ed6eeb4aaea614c7ece8d558e31d3bb5");
		Medias medias = liquidAPIClient.getMedias(apiFilter);
		
		System.out.println(medias.getHttpResponse().getUrl());
		System.out.println(medias.getHttpResponse().getCode());
		System.out.println(medias.getHttpResponse().getResponseBody());		
	}
}

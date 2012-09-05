package com.sambatech.apiclient;

import com.sambatech.apiclient.body.MediaUpdate;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.http.HttpRequest;
import com.sambatech.apiclient.model.Media;
import com.sambatech.apiclient.model.Medias;
import com.sambatech.apiclient.model.SimpleResult;
import com.sambatech.apiclient.model.Status;
import com.sambatech.apiclient.parser.JAXBParser;

public class LiquidAPIClient {

	private LiquidAPIRequestBuilder requestBuilder;
	
	/****************************************************************************************************
	 **** Constructors **********************************************************************************
	 ****************************************************************************************************/
	public LiquidAPIClient(String apiKey) {
		this.requestBuilder = new LiquidAPIRequestBuilder(apiKey);
	}
	
	public LiquidAPIClient(String apiKey, String apiBaseUrl, int timeout) {
		this.requestBuilder = new LiquidAPIRequestBuilder(apiKey, apiBaseUrl, timeout);	
	}
	
	/****************************************************************************************************
	 **** API Methods ***********************************************************************************
	 ****************************************************************************************************/
	
	/**
	 * GET /medias 
	 */
	public Medias getMedias(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMedias(apiFilter, true);
		
		// Serialize object
		Medias medias = JAXBParser.stringToObject(httpRequest.getResponseBody(), Medias.class);
		
		// Http Response info
		medias.setHttpRequest(httpRequest);
		
		return medias;
	}

	
	/**
	 * GET /medias/count 
	 */
	public int getMediasCount() throws RequestException, ParserException {
		HttpRequest httpRequest = requestBuilder.getMediasCount(true);
		
		// Serialize object
		SimpleResult result = JAXBParser.stringToObject(httpRequest.getResponseBody(), SimpleResult.class);
		
		if(result == null || result.getValue() == null) {
			return 0;
		}
		
		return Integer.valueOf(result.getValue());
	}
	
	
	/**
	 * GET /medias/{id} 
	 */
	public Media getMediaId(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMediaId(apiFilter, true);
		
		// Serialize object
		Media media = JAXBParser.stringToObject(httpRequest.getResponseBody(), Media.class);
		
		return media;
	}
	
	/**
	 * PUT /medias/{id} 
	 * @param mediaUpdate 
	 */
	public Status updateMediaId(APIFilter apiFilter, MediaUpdate mediaUpdate) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.updateMediaId(apiFilter, mediaUpdate, true);
		
		// Serialize object
		Status status = JAXBParser.stringToObject(httpRequest.getResponseBody(), Status.class);
		
		return status;
	}
	
	/**
	 * DELETE /medias/{id} 
	 * @param mediaUpdate 
	 */
	public Status deleteMediaId(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.deleteMediaId(apiFilter, true);
		
		// Serialize object
		Status status = JAXBParser.stringToObject(httpRequest.getResponseBody(), Status.class);
		
		return status;
	}
}

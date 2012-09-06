package com.sambatech.apiclient;

import com.sambatech.apiclient.body.MediaUpdate;
import com.sambatech.apiclient.exception.ParserException;
import com.sambatech.apiclient.exception.RequestException;
import com.sambatech.apiclient.filter.APIFilter;
import com.sambatech.apiclient.http.HttpRequest;
import com.sambatech.apiclient.model.Channel;
import com.sambatech.apiclient.model.Channels;
import com.sambatech.apiclient.model.Media;
import com.sambatech.apiclient.model.Medias;
import com.sambatech.apiclient.model.RatingSummary;
import com.sambatech.apiclient.model.RatingSummarys;
import com.sambatech.apiclient.model.SimpleResult;
import com.sambatech.apiclient.model.Status;
import com.sambatech.apiclient.model.Thumbnails;
import com.sambatech.apiclient.model.URLs;
import com.sambatech.apiclient.model.View;
import com.sambatech.apiclient.model.Views;
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
	 * GET /medias/ratings 
	 */
	public RatingSummarys getMediasRatings(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMediasRatings(apiFilter, true);
		
		// Serialize object
		RatingSummarys ratingSummarys = JAXBParser.stringToObject(httpRequest.getResponseBody(), RatingSummarys.class);
		
		return ratingSummarys;
	}
	
	
	/**
	 * GET /medias/views 
	 */
	public Views getMediasViews(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMediasViews(apiFilter, true);
		
		// Serialize object
		Views views = JAXBParser.stringToObject(httpRequest.getResponseBody(), Views.class);
		
		return views;
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
	
	
	/**
	 * GET /medias/urls/{id} 
	 */
	public URLs getMediaIdUrls(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMediaIdUrls(apiFilter, true);
		
		// Serialize object
		URLs urls = JAXBParser.stringToObject(httpRequest.getResponseBody(), URLs.class);
		
		return urls;
	}
	
	
	/**
	 * POST /medias/views/{id} 
	 */
	public Status addMediaIdViews(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.addMediaIdViews(apiFilter, true);
		
		Status status = JAXBParser.stringToObject(httpRequest.getResponseBody(), Status.class);
		
		return status;
	}
	
	
	/**
	 * GET /medias/{id}/rating 
	 */
	public RatingSummary getMediaIdRating(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMediaIdRating(apiFilter, true);
		
		// Serialize object
		RatingSummary ratingSummary = JAXBParser.stringToObject(httpRequest.getResponseBody(), RatingSummary.class);
		
		return ratingSummary;
	}
	
	/**
	 * GET /medias/{id}/related 
	 */
	public Medias getMediaIdRelated(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMediaIdRelated(apiFilter, true);
		
		// Serialize object
		Medias medias = JAXBParser.stringToObject(httpRequest.getResponseBody(), Medias.class);
		
		// Http Response info
		medias.setHttpRequest(httpRequest);
		
		return medias;
		
	}
	
	
	/**
	 * GET /medias/{id}/thumbs
	 */
	public Thumbnails getMediaIdThumbs(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMediaIdThumbs(apiFilter, true);
		
		// Serialize object
		Thumbnails thumbnails = JAXBParser.stringToObject(httpRequest.getResponseBody(), Thumbnails.class);
		
		return thumbnails;
	}
	
	
	/**
	 * GET /medias/{id}/views
	 */
	public View getMediaIdViews(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getMediaIdViews(apiFilter, true);
		
		// Serialize object
		View view = JAXBParser.stringToObject(httpRequest.getResponseBody(), View.class);
		
		return view;
	}
	
	
	/**
	 * Build Request to GET /medias/urls/{mediaId}/{outputName}
	 */
	public URLs getMediaIdOutputUrls(APIFilter apiFilter) throws RequestException, ParserException {
		HttpRequest httpRequest = requestBuilder.getMediaIdOutputUrls(apiFilter, true);
		
		// Serialize object
		URLs urls = JAXBParser.stringToObject(httpRequest.getResponseBody(), URLs.class);
		
		return urls;
	}
	
	/**
	 * POST /medias/{mediaId}/views/{outputName} 
	 */
	public Status addMediaIdOutputViews(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.addMediaIdOutputViews(apiFilter, true);
		
		System.out.println(httpRequest.getUrl());
		System.out.println(httpRequest.getResponseBody());
		
		Status status = JAXBParser.stringToObject(httpRequest.getResponseBody(), Status.class);
		
		System.out.println(status.getResult());
		System.out.println(status.getCode());
		
		return status;
	}
	
	
	/**
	 * GET /channels 
	 */
	public Channels getChannels(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getChannels(apiFilter, true);
		
		// Serialize object
		Channels channels = JAXBParser.stringToObject(httpRequest.getResponseBody(), Channels.class);
		
		return channels;
	}
	
	/**
	 * POST /channels 
	 * @param mediaUpdate 
	 */
	public Channel addChannel(APIFilter apiFilter, Channel channel) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.addChannel(apiFilter, channel, true);
		
		// Serialize object
		Channel newChannel = JAXBParser.stringToObject(httpRequest.getResponseBody(), Channel.class);
		
		return newChannel;
	}
	
	/**
	 * GET /channels/count 
	 */
	public int getChannelsCount() throws RequestException, ParserException {
		HttpRequest httpRequest = requestBuilder.getChannelsCount(true);
		
		System.out.println(httpRequest.getUrl());
		
		// Serialize object
		SimpleResult result = JAXBParser.stringToObject(httpRequest.getResponseBody(), SimpleResult.class);
		
		if(result == null || result.getValue() == null) {
			return 0;
		}
		
		return Integer.valueOf(result.getValue());
	}
	
	/**
	 * GET /channels/{channelId} 
	 */
	public Channel getChannelId(APIFilter apiFilter) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.getChannelId(apiFilter, true);
		
		// Serialize object
		Channel channel = JAXBParser.stringToObject(httpRequest.getResponseBody(), Channel.class);
		
		return channel;
	}
	
	/**
	 * PUT /channels/{channelId} 
	 * @param mediaUpdate 
	 */
	public Status updateChannelId(APIFilter apiFilter, Channel channel) throws RequestException, ParserException {
		// Http Request
		HttpRequest httpRequest = requestBuilder.updateChannelId(apiFilter, channel, true);
		
		// Serialize object
		Status status = JAXBParser.stringToObject(httpRequest.getResponseBody(), Status.class);
		
		return status;
	}
}

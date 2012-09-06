package com.sambatech.apiclient.filter;


public enum APIFilterParams {
	FIRST("first"),
	LIMIT("limit"), 
	SEARCH("search"),
	RECURSIVE_CHANNEL("recursiveChannel"),
	FILTER("filter"),
	ORDERBY("orderBy"),
	SORT("sort"),
	LAST_MODIFIED("lastModified"),
	SESSION_ID("sessionId"),
	QUARTER("quarter"),
	BEGIN("begin"),
	END("end"),
	OUTPUT_NAME("outputName"),
	CHANNEL_ID("channelId"),
	MEDIA_ID("mediaId"),
	MEDIAFILE_ID("mediaFileId");
	
	private final String value;

	private APIFilterParams(final String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}

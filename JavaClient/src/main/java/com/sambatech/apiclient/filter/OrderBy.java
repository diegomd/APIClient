package com.sambatech.apiclient.filter;

public enum OrderBy {
	POST_DATE("postDate"),
	LAST_MODIFIED("lastModified"),
	TITLE("title"),
	PUBLISH_DATE("publishDate"),
	COMMENTS("numberOfComments"),
	RATINGS("numberOfRatings"),
	VIEWS("numberOfViews")
	;
	
	private final String value;

	private OrderBy(final String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}

package com.sambatech.apiclient.filter;

import java.util.Calendar;

public class APIFilter {
	
	private String mediaId;
	private Integer first;
	private Integer limit;
	private String search;
	private Boolean recursiveChannel;
	private String filter;
	private OrderBy orderBy;
	private Sort sort;
	private Calendar lastModified;
	private String sessionId;
	private Integer quarter;
	
	public Integer getFirst() {
		return first;
	}
	public void setFirst(Integer first) {
		this.first = first;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Boolean getRecursiveChannel() {
		return recursiveChannel;
	}
	public void setRecursiveChannel(Boolean recursiveChannel) {
		this.recursiveChannel = recursiveChannel;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public OrderBy getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(OrderBy orderBy) {
		this.orderBy = orderBy;
	}
	public Sort getSort() {
		return sort;
	}
	public void setSort(Sort sort) {
		this.sort = sort;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setLastModified(Calendar lastModified) {
		this.lastModified = lastModified;
	}
	public Calendar getLastModified() {
		return lastModified;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	public Integer getQuarter() {
		return quarter;
	}
}

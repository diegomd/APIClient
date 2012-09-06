package com.sambatech.apiclient.filter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	private Calendar begin;
	private Calendar end;
	private String outputName;
	private String sessionId;
	private String quarter;
	private String channelId;
	private String mediaFileId;
	
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
	public void setLastModified(String lastModified, SimpleDateFormat simpleDateFormat) throws ParseException {
		this.lastModified = getCalendarFromString(lastModified, simpleDateFormat);
	}
	public Calendar getLastModified() {
		return lastModified;
	}
	public Calendar getBegin() {
		return begin;
	}
	public void setBegin(Calendar begin) {
		this.begin = begin;
	}
	public void setBegin(String lastModified, SimpleDateFormat simpleDateFormat) throws ParseException {
		this.begin = getCalendarFromString(lastModified, simpleDateFormat);
	}
	public Calendar getEnd() {
		return end;
	}
	public void setEnd(Calendar end) {
		this.end = end;
	}
	public void setEnd(String lastModified, SimpleDateFormat simpleDateFormat) throws ParseException {
		this.end = getCalendarFromString(lastModified, simpleDateFormat);
	}
	public String getOutputName() {
		return outputName;
	}
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getMediaFileId() {
		return mediaFileId;
	}
	public void setMediaFileId(String mediaFileId) {
		this.mediaFileId = mediaFileId;
	}
	
	private static Calendar getCalendarFromString(String stringDate, SimpleDateFormat dateFormat) throws ParseException {
		Calendar calendar = Calendar.getInstance();

		dateFormat.setLenient(false);
		calendar.setTime(dateFormat.parse(stringDate));
		return calendar;
	}
	
	
}

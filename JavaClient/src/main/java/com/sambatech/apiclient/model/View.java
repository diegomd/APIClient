package com.sambatech.apiclient.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="View")
public class View {

	private Long views;
	private String mediaId;
	private Calendar lastUpdate;

	@XmlElement
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@XmlElement
	public Long getViews() {
		return views;
	}
	public void setViews(Long views) {
		this.views = views;
	}

	@XmlElement
	public Calendar getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Calendar lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}

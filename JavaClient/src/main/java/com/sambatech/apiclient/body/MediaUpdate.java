package com.sambatech.apiclient.body;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Media")
public class MediaUpdate {
	
	private Integer channelId;
	private String title;
	private String description;
	private String shortDescription;
	private Boolean restrict;
	private Boolean highlighted;
	private String secondURL;
	private String tags;
	private Calendar publishDate;
	private Calendar unPublishDate;

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Boolean getRestrict() {
		return restrict;
	}

	public void setRestrict(Boolean restrict) {
		this.restrict = restrict;
	}

	public Boolean getHighlighted() {
		return highlighted;
	}

	public void setHighlighted(Boolean highlighted) {
		this.highlighted = highlighted;
	}

	public String getSecondURL() {
		return secondURL;
	}

	public void setSecondURL(String secondURL) {
		this.secondURL = secondURL;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setPublishDate(Calendar publishDate) {
		this.publishDate = publishDate;
	}

	public Calendar getPublishDate() {
		return publishDate;
	}

	public void setUnPublishDate(Calendar unPublishDate) {
		this.unPublishDate = unPublishDate;
	}

	public Calendar getUnPublishDate() {
		return unPublishDate;
	}
}

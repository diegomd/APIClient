package com.sambatech.apiclient.model;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RatingSummary")
public class RatingSummary {

	private String mediaId;
	private Long rating;// average of votes
	private Long numberOfRatings; // total number of votes
	private Long thumbsUp; // number of votes:5
	private Long thumbsDown; // number of votes:1
	private Calendar lastModified; // last time that one vote was done

	@XmlElement
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	@XmlElement
	public Long getRating() {
		return rating;
	}
	public void setRating(Long rating) {
		this.rating = rating;
	}
	@XmlElement
	public Long getNumberOfRatings() {
		return numberOfRatings;
	}
	public void setNumberOfRatings(Long numberOfRatings) {
		this.numberOfRatings = numberOfRatings;
	}
	@XmlElement
	public Long getThumbsUp() {
		return thumbsUp;
	}
	public void setThumbsUp(Long ratingsUp) {
		this.thumbsUp = ratingsUp;
	}
	@XmlElement
	public Long getThumbsDown() {
		return thumbsDown;
	}
	public void setThumbsDown(Long ratingsDown) {
		this.thumbsDown = ratingsDown;
	}
	@XmlElement
	public Calendar getLastModified() {
		return lastModified;
	}
	public void setLastModified(Calendar lastModified) {
		this.lastModified = lastModified;
	}
}
package com.sambatech.apiclient.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RatingSummarys")
public class RatingSummarys {

	private List<RatingSummary> ratingSummarieList;

	@XmlElement(name="RatingSummary")
	public void setRatingSummarieList(List<RatingSummary> ratingSummarieList) {
		this.ratingSummarieList = ratingSummarieList;
	}

	public List<RatingSummary> getRatingSummarieList() {
		return ratingSummarieList;
	}
}

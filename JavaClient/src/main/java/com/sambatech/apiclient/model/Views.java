package com.sambatech.apiclient.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Views")
public class Views {

	private List<View> viewsList;

	public void setViewsList(List<View> viewsList) {
		this.viewsList = viewsList;
	}

	@XmlElement(name="View")
	public List<View> getViewsList() {
		return viewsList;
	}
	
}

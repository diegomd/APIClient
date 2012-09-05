package com.sambatech.apiclient.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Media")
public class Media {

	private String id;

	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(name="id")
	public String getId() {
		return id;
	}
	
}

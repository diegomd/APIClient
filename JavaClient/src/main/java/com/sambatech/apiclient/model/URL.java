package com.sambatech.apiclient.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="URL")
public class URL {

	private String uri;
	private String uriType;
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	@XmlElement(name="uri")
	public String getUri() {
		return uri;
	}
	public void setUriType(String uriType) {
		this.uriType = uriType;
	}
	@XmlElement(name="UriType")
	public String getUriType() {
		return uriType;
	}

}

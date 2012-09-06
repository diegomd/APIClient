package com.sambatech.apiclient.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="URLs")
public class URLs {
	
	private List<URL> urlList;

	public void setUrlList(List<URL> urlList) {
		this.urlList = urlList;
	}

	@XmlElement(name="URL")
	public List<URL> getUrlList() {
		return urlList;
	}

}

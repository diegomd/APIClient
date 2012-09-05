package com.sambatech.apiclient.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Thumbnail")
public class Thumbnail {
	
	private Integer width;
	private Integer height;
	private String url;
	private Long size;

	@XmlElement
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@XmlElement
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@XmlElement
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@XmlElement
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
}

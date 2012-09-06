package com.sambatech.apiclient.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Thumbnails")
public class Thumbnails {

	private List<Thumbnail> thumbnailsList;

	public void setThumbnailsList(List<Thumbnail> thumbnailsList) {
		this.thumbnailsList = thumbnailsList;
	}

	@XmlElement(name="Thumbnail")
	public List<Thumbnail> getThumbnailsList() {
		return thumbnailsList;
	}
}

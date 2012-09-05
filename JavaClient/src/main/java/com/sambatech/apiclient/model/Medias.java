package com.sambatech.apiclient.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sambatech.apiclient.response.BaseResponse;

@XmlRootElement(name="Medias")
public class Medias extends BaseResponse {
	
	private List<Media> mediaList;

	public void setMediaList(List<Media> mediaList) {
		this.mediaList = mediaList;
	}

	@XmlElement(name="Media")
	public List<Media> getMediaList() {
		return mediaList;
	}

}

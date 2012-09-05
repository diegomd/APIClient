package com.sambatech.apiclient.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Channels")
public class Channels {

	private List<Channel> channelList;

	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
	}

	@XmlElement(name="Channel")
	public List<Channel> getChannelList() {
		return channelList;
	}
}
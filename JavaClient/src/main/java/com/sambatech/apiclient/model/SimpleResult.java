package com.sambatech.apiclient.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SimpleResult")
public class SimpleResult {
	
	private String value;

	public void setValue(String value) {
		this.value = value;
	}

	@XmlElement(name="value")
	public String getValue() {
		return value;
	}
	
	
}

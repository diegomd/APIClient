package com.sambatech.apiclient.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Status")
public class Status {
	private Long code;
	private String result;
	
	
	public Long getCode() {
		return code;
	}
	@XmlElement(name="code")
	public void setCode(Long code) {
		this.code = code;
	}
	public String getResult() {
		return result;
	}
	@XmlElement(name="result")
	public void setResult(String result) {
		this.result = result;
	}
}

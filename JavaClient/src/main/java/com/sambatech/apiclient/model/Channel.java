package com.sambatech.apiclient.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="Channels")
@XmlRootElement(name="Channel")
public class Channel {

	private Long id;
    private String name;
    private String description;
    private Long parent;    
    private Thumbnail thumb;
    private Boolean restricted;
    private Boolean hidden;

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@XmlElement
	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}
	@XmlElement
	public Thumbnail getThumb() {
		return thumb;
	}

	public void setThumb(Thumbnail thumb) {
		this.thumb = thumb;
	}
	@XmlElement
	public Boolean getRestricted() {
		return restricted;
	}

	public void setRestricted(Boolean restricted) {
		this.restricted = restricted;
	}
	@XmlElement
	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}	
}
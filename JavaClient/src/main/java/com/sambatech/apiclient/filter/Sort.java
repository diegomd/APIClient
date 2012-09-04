package com.sambatech.apiclient.filter;

public enum Sort {
	DESC("desc"),
	ASC("asc")
	;
	
	private final String value;

	private Sort(final String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}

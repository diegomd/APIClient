package com.sambatech.apiclient.exception;


public class ParserException extends Exception {
	private static final long serialVersionUID = -8351369200391825092L;

	public ParserException(Throwable t) {
		super(t);
	}

	public ParserException(String cause) {
		super(cause);
	}

	public ParserException(String cause, Throwable t) {
		super(cause, t);
	}
}

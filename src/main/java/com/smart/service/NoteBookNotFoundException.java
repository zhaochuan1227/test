package com.smart.service;

public class NoteBookNotFoundException extends RuntimeException {

	public NoteBookNotFoundException() {
	}

	public NoteBookNotFoundException(String message) {
		super(message);
	}

	public NoteBookNotFoundException(Throwable cause) {
		super(cause);
	}

	public NoteBookNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoteBookNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

package com.smart.service;

public class NameOrPasswordException extends RuntimeException {
	public static final int NAME = 1;
	public static final int PASSWORD = 2;

	private static final long serialVersionUID = -6820006535189908560L;
	private int filed;

	public NameOrPasswordException() {
	}

	public NameOrPasswordException(int filed, String message) {
		super(message);
		this.filed = filed;
	}

	public NameOrPasswordException(Throwable cause) {
		super(cause);
	}

	public NameOrPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public NameOrPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public int getField() {
		return filed;
	}
}

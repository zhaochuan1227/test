package com.smart.util;

/**
 * 用与封装服务器到客户端的JSON返回值
 */
public class JsonResult<T> {
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	private int state;
	private String message = "";
	private T data;

	public JsonResult() {
		this.state = SUCCESS;
	}

	// 为了方便，重载多个构造器
	public JsonResult(int state, String message, T data) {
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public JsonResult(String error) {
		this(ERROR, error, null);
	}

	public JsonResult(T data) {
		this(SUCCESS, "", data);
	}

	public JsonResult(int state) {
		this(state, "", null);
	}

	public JsonResult(Throwable e) {
		this(ERROR, e.getMessage(), null);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getdata() {
		return data;
	}

	public void setdata(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "state=" + state + ", message=" + message + ", data=" + data;
	}

	
}

package com.johnkuper.epam.rest.mapper;

public class OwnJSONStatus {

	public int statusCode;
	public String message;

	public OwnJSONStatus(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public OwnJSONStatus() {
		this.statusCode = -1;
		this.message = "Everything is fine";
	}

}

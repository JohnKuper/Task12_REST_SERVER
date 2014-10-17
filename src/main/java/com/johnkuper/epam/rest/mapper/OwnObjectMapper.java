package com.johnkuper.epam.rest.mapper;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OwnObjectMapper extends ObjectMapper {

	private final static String STATUS_WITH_NO_ERROR_BASE = ",\"status\":";
	private final static String STATUS_WITH_ERROR_BASE = "{\"status\":";

	final static Logger logger = LoggerFactory.getLogger(OwnObjectMapper.class);

	public String jsonFromSpecialMessage(int statusCode, String message) {

		OwnJSONStatus ownStatus = new OwnJSONStatus(statusCode, message);
		String str = null;
		try {
			str = STATUS_WITH_ERROR_BASE + writeValueAsString(ownStatus) + "}";
		} catch (IOException e) {
			logger.error("IOExcepiton during 'jsonFromSpecialMessage': ", e);
		}

		return str;
	}

	public String jsonWithNoErrors() {

		OwnJSONStatus ownStatus = new OwnJSONStatus();
		String str = "";
		try {
			str = STATUS_WITH_NO_ERROR_BASE + writeValueAsString(ownStatus)
					+ "}";
		} catch (IOException e) {
			logger.error("IOExcepiton during 'jsonWithNoErrors': ", e);
		}

		return str;
	}

}

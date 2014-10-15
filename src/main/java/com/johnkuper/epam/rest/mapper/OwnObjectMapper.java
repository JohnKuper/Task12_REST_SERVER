package com.johnkuper.epam.rest.mapper;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OwnObjectMapper extends ObjectMapper {
	
	private final static String STATUS_WITH_NO_ERROR_BASE = ",\"status\":";
	private final static String STATUS_WITH_ERROR_BASE = "{\"status\":";

	final static Logger logger = LoggerFactory.getLogger(OwnObjectMapper.class);

	public String jsonFromErrorMessage(int statusCode, String message)
			throws JsonGenerationException, JsonMappingException, IOException {

		OwnJSONStatus ownStatus = new OwnJSONStatus(statusCode, message);
		String str = STATUS_WITH_ERROR_BASE + writeValueAsString(ownStatus) + "}";

		return str;
	}

	public String jsonWithNoErrors() {

		OwnJSONStatus ownStatus = new OwnJSONStatus();
		String str = "";
		try {
			str = STATUS_WITH_NO_ERROR_BASE + writeValueAsString(ownStatus) + "}";
		} catch (JsonGenerationException e) {
			logger.error("Error during JSON writing: ", e);
		} catch (JsonMappingException e) {
			logger.error("Error during JSON mapping: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}

		return str;
	}

}

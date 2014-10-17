package com.johnkuper.epam.rest.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.jpaservice.JPAServiceImpl;
import com.johnkuper.epam.jpaservice.model.MerchantWeb;
import com.johnkuper.epam.rest.mapper.OwnObjectMapper;

@Path("merchants")
@Produces("application/json")
public class MerchantResource {

	OwnObjectMapper mapper = new OwnObjectMapper();
	JPAServiceImpl service = new JPAServiceImpl();
	private final static String MERCHANT_JSON_START = "{\"merchants\":";
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response createMerchant(String merchantJSON) {

		MerchantWeb merchant = null;
		String restStatus = "";
		try {
			merchant = mapper.readValue(merchantJSON, MerchantWeb.class);
			logger.debug("Merchant from JSON: {}", merchant);
			String serviceStatus = service.createMerchant(merchant);
			restStatus = mapper.jsonFromSpecialMessage(200, serviceStatus);
		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during parse JSON. Please fix it");
			logger.error("IOException during 'createMerchant': ", e);
			return Response.status(400).entity(error).build();

		}

		return Response.ok(restStatus).build();
	}

	@GET
	@Path("/findOne/{id: [0-9]*}")
	public Response findOne(@PathParam("id") int id) {

		if (id <= 0) {
			return Response.status(400).entity("ID should be 1 or more.")
					.build();

		}

		String merchantJSON = MERCHANT_JSON_START;
		try {
			MerchantWeb merchant = service.findMerchant(id);
			if (merchant == null) {
				String jsonError = mapper.jsonFromSpecialMessage(204,
						"Merchant with ID = " + id + " not exists in database");
				return Response.ok(jsonError).build();
			}

			merchantJSON += mapper.writeValueAsString(merchant);
			merchantJSON += mapper.jsonWithNoErrors();

		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during proccess request. Please fix it");
			logger.error("IOException during 'findOne (merchant): ", e);
			return Response.status(400).entity(error).build();

		}

		return Response.ok(merchantJSON).build();
	}

}

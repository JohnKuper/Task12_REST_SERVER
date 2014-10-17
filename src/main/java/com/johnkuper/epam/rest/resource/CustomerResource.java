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
import com.johnkuper.epam.jpaservice.model.CustomerWeb;
import com.johnkuper.epam.rest.mapper.OwnObjectMapper;

@Path("customers")
@Produces("application/json")
public class CustomerResource {

	OwnObjectMapper mapper = new OwnObjectMapper();
	JPAServiceImpl service = new JPAServiceImpl();
	private final static String CUSTOMER_JSON_START = "{\"customers\":";
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response createCustomer(String customerJSON) {

		CustomerWeb customer = null;
		String restStatus = "";
		try {
			customer = mapper.readValue(customerJSON, CustomerWeb.class);
			logger.debug("Customer from JSON: {}", customer);
			String serviceStatus = service.createCustomer(customer);
			restStatus = mapper.jsonFromSpecialMessage(200, serviceStatus);
		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during parse JSON. Please fix it");
			logger.error("IOException during 'createCustomer': ", e);
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

		String customerJSON = CUSTOMER_JSON_START;
		try {
			CustomerWeb customer = service.findCustomer(id);
			if (customer == null) {
				String jsonError = mapper.jsonFromSpecialMessage(204,
						"Customer with ID = " + id + " not exists in database");
				return Response.ok(jsonError).build();
			}

			customerJSON += mapper.writeValueAsString(customer);
			customerJSON += mapper.jsonWithNoErrors();

		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during proccess request. Please fix it");
			logger.error("IOException during 'findOne' (customer): ", e);
			return Response.status(400).entity(error).build();

		}

		return Response.ok(customerJSON).build();
	}

}

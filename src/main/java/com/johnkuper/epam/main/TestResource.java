package com.johnkuper.epam.main;

import java.io.IOException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.service.JPAServiceImpl;
import com.johnkuper.epam.servicemodel.CarWeb;

@Path("/johnkuper")
public class TestResource {

	ObjectMapper mapper = new ObjectMapper();
	JPAServiceImpl service = new JPAServiceImpl();
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@GET
	@Produces("text/plain")
	@Path("/method1")
	public String getValue(
			@DefaultValue("just normal") @QueryParam("value") String value) {
		return "JohnKuper is a very " + value + " man";
	}

	@GET
	@Produces("text/plain")
	@Path("/method2")
	public String getOtherValue(@QueryParam("value") String value) {
		return "Other value JohnKuper is a very " + value + " man";
	}

	@GET
	@Path("json1")
	@Produces("application/json")
	public String getCarInJson() {

		String carToJSON = null;

		try {
			Car car = new Car();
			car.setId(1);
			car.setModel("R8");
			car.setName("Audi");

			carToJSON = mapper.writeValueAsString(car);

		} catch (JsonGenerationException e) {
			logger.error("Error", e);
		} catch (JsonMappingException e) {
			logger.error("Error", e);
		} catch (IOException e) {
			logger.error("Error", e);
		}

		return carToJSON;
	}

	@GET
	@Produces("application/json")
	@Path("findOne")
	public Response getCarByID(@QueryParam("id") int id) {

		if (id <= 0) {
			return Response.status(400).entity("ID should be 1 or more")
					.build();

		}

		String carJSON = null;
		CarWeb car = null;
		try {

			car = service.findCar(id);
			carJSON = mapper.writeValueAsString(car);

		} catch (JsonGenerationException e) {
			logger.error("Error", e);
		} catch (JsonMappingException e) {
			logger.error("Error", e);
		} catch (IOException e) {
			logger.error("Error", e);
		}

		return Response.ok(carJSON).build();
	}

}

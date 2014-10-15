package com.johnkuper.epam.rest.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.jpaservice.JPAServiceImpl;
import com.johnkuper.epam.jpaservice.model.CarWeb;
import com.johnkuper.epam.rest.mapper.OwnObjectMapper;

@Path("cars")
@Produces("application/json")
public class CarResource {

	OwnObjectMapper mapper = new OwnObjectMapper();
	JPAServiceImpl service = new JPAServiceImpl();
	final static Logger logger = LoggerFactory.getLogger(CarResource.class);

	@GET
	@Path("/findCarByName/{name}")
	public Response getCarByName(@PathParam("name") String name) {

		List<CarWeb> cars = service.findCarByName(name);
		String carJSON = "{\"cars\":";
		try {
			if (cars.size() == 1) {
				carJSON += mapper.writeValueAsString(cars.get(0));
			} else if (cars.size() > 1) {
				carJSON += mapper.writeValueAsString(cars);
			} else {
				String jsonError = mapper.jsonFromErrorMessage(200,
						"Car with name = " + name + " not exists in database");
				return Response.ok(jsonError).build();
			}

		} catch (JsonGenerationException e) {
			logger.error("Error during JSON writing: ", e);
		} catch (JsonMappingException e) {
			logger.error("Error during JSON mapping: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}

		carJSON += mapper.jsonWithNoErrors();
		return Response.ok(carJSON).build();

	}

	@GET
	@Path("/findOne/{id}")
	public Response getCarByID(@PathParam("id") int id) {

		if (id <= 0) {
			return Response.status(400).entity("ID should be 1 or more.")
					.build();

		}

		String carJSON = null;
		CarWeb car = service.findCar(id);
		try {

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

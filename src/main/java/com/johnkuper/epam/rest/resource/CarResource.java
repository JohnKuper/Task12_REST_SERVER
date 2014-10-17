package com.johnkuper.epam.rest.resource;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

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
	private final static String CAR_JSON_START = "{\"cars\":";
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@GET
	@Path("/findByName/{name}")
	public Response findCarByName(@PathParam("name") String name) {

		List<CarWeb> cars = service.findCarByName(name);
		String carJSON = CAR_JSON_START;
		try {
			if (cars.size() == 1) {
				carJSON += mapper.writeValueAsString(cars.get(0));
			} else if (cars.size() > 1) {
				carJSON += mapper.writeValueAsString(cars);
			} else {
				String jsonError = mapper.jsonFromSpecialMessage(204,
						"Car with name = " + name + " not exists in database");
				return Response.ok(jsonError).build();
			}

		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during proccess request. Please fix it");
			logger.error("IOException during 'findCarByName': ", e);
			return Response.status(400).entity(error).build();

		}

		carJSON += mapper.jsonWithNoErrors();
		return Response.ok(carJSON).build();

	}

	@GET
	@Path("/findOne/{id: [0-9]*}")
	public Response findOne(@PathParam("id") int id) {

		if (id <= 0) {
			return Response.status(400).entity("ID should be 1 or more.")
					.build();

		}

		String carJSON = CAR_JSON_START;
		try {
			CarWeb car = service.findCar(id);
			if (car == null) {
				String jsonError = mapper.jsonFromSpecialMessage(204,
						"Car with ID = " + id + " not exists in database");
				return Response.ok(jsonError).build();
			}

			carJSON += mapper.writeValueAsString(car);
			carJSON += mapper.jsonWithNoErrors();

		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during proccess request. Please fix it");
			logger.error("IOException during 'findOne' (car): ", e);
			return Response.status(400).entity(error).build();

		}
		return Response.ok(carJSON).build();
	}

	@POST
	@Path("/create")
	@Consumes("application/json")
	public Response createCar(String carJSON) {

		CarWeb car = null;
		String restStatus = "";
		try {
			car = mapper.readValue(carJSON, CarWeb.class);
			logger.debug("Car from JSON: {}", car);
			String serviceStatus = service.createCar(car);
			restStatus = mapper.jsonFromSpecialMessage(200, serviceStatus);

		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during parse JSON. Please fix it");
			logger.error("IOException during 'createCar': ", e);
			return Response.status(400).entity(error).build();

		}

		return Response.ok(restStatus).build();
	}

	@POST
	@Path("/update")
	@Consumes("application/json")
	public Response updateCar(String carJSON) {

		CarWeb car = null;
		String restStatus = "";
		try {
			car = mapper.readValue(carJSON, CarWeb.class);
			logger.debug("Car from JSON: {}", car);
			String serviceStatus = service.updateCar(car);
			restStatus = mapper.jsonFromSpecialMessage(200, serviceStatus);
		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during parse JSON. Please fix it");
			logger.error("IOException during 'updateCar': ", e);
			return Response.status(400).entity(error).build();
		}

		return Response.ok(restStatus).build();

	}

	@GET
	@Path("/findByMotorPower")
	public Response findByMotorPower(
			@DefaultValue("0") @QueryParam("min") int minPower,
			@DefaultValue("1000") @QueryParam("max") int maxPower) {

		List<CarWeb> cars = service.findCarsByMotorPower(minPower, maxPower);
		String carJSON = CAR_JSON_START;
		try {
			if (cars.size() == 1) {
				carJSON += mapper.writeValueAsString(cars.get(0));
			} else if (cars.size() > 1) {
				carJSON += mapper.writeValueAsString(cars);
			} else {
				String jsonError = mapper.jsonFromSpecialMessage(204,
						"No cars between motorpower " + minPower + " and "
								+ maxPower + " in database");
				return Response.ok(jsonError).build();
			}

		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during proccess request. Please fix it");
			logger.error("IOException during 'findByMotorPower': ", e);
			return Response.status(400).entity(error).build();
		}

		carJSON += mapper.jsonWithNoErrors();
		return Response.ok(carJSON).build();

	}

}

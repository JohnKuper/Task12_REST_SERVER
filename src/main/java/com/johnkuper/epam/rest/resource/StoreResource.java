package com.johnkuper.epam.rest.resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.jpaservice.JPAServiceImpl;
import com.johnkuper.epam.jpaservice.model.StoreWeb;
import com.johnkuper.epam.rest.mapper.OwnObjectMapper;

@Path("store")
@Produces("application/json")
public class StoreResource {

	OwnObjectMapper mapper = new OwnObjectMapper();
	JPAServiceImpl service = new JPAServiceImpl();
	private final static String STORE_JSON_START = "{\"stores\":";
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@GET
	@Path("/all")
	public Response getAll() {

		List<StoreWeb> stores = service.getAllCarsFromStore();
		String carJSON = STORE_JSON_START;
		try {
			if (stores.size() == 1) {
				carJSON += mapper.writeValueAsString(stores.get(0));
			} else if (stores.size() > 1) {
				carJSON += mapper.writeValueAsString(stores);
			} else {
				String jsonError = mapper.jsonFromSpecialMessage(204,
						"No cars in store");
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
	@Path("/findByPrice")
	public Response findByPrice(
			@DefaultValue("0") @QueryParam("min") BigDecimal minPrice,
			@DefaultValue("1000000") @QueryParam("max") BigDecimal maxPrice) {

		List<StoreWeb> store = service
				.findCarsBetweenPrices(minPrice, maxPrice);
		String storeJSON = STORE_JSON_START;
		try {
			if (store.size() == 1) {
				storeJSON += mapper.writeValueAsString(store.get(0));
			} else if (store.size() > 1) {
				storeJSON += mapper.writeValueAsString(store);
			} else {
				String jsonError = mapper.jsonFromSpecialMessage(204,
						"No cars between prices " + minPrice + " and "
								+ maxPrice + " in database");
				return Response.ok(jsonError).build();
			}

		} catch (JsonGenerationException e) {
			logger.error("Error during JSON writing: ", e);
		} catch (JsonMappingException e) {
			logger.error("Error during JSON mapping: ", e);
		} catch (IOException e) {
			logger.error("IOException: ", e);
		}

		storeJSON += mapper.jsonWithNoErrors();
		return Response.ok(storeJSON).build();

	}

}

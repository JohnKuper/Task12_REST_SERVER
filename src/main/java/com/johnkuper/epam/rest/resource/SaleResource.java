package com.johnkuper.epam.rest.resource;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.jpaservice.JPAServiceImpl;
import com.johnkuper.epam.jpaservice.model.CarWeb;
import com.johnkuper.epam.jpaservice.model.CustomerWeb;
import com.johnkuper.epam.jpaservice.model.MerchantWeb;
import com.johnkuper.epam.jpaservice.model.StoreWeb;
import com.johnkuper.epam.rest.mapper.OwnObjectMapper;

@Path("sales")
@Produces("application/json")
public class SaleResource {

	OwnObjectMapper mapper = new OwnObjectMapper();
	JPAServiceImpl service = new JPAServiceImpl();
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@POST
	@Path("/buyCar")
	@Consumes("application/json")
	public Response buyCar(String saleJSON) {

		int carId;
		int customerId;
		int merchantId;
		String statusJSON = null;
		CarWeb car;
		CustomerWeb customer;
		MerchantWeb merchant;
		StoreWeb store;
		BigDecimal price;
		JsonNode fullDocument;

		try {
			// Read all data from json
			fullDocument = mapper.readTree(saleJSON);
			carId = fullDocument.get("carID").getIntValue();
			customerId = fullDocument.get("customerID").getIntValue();
			merchantId = fullDocument.get("merchantID").getIntValue();
			logger.debug(
					"Values from json: carID = {}, customerID = {}, merchantID = {}",
					carId, customerId, merchantId);
			// Find objects in database
			car = service.findCar(carId);
			customer = service.findCustomer(customerId);
			merchant = service.findMerchant(merchantId);
			store = service.findStoreByCarID(carId);
			price = store.getCarPrice();
			// Create new sale
			String jpaServiceStatus;
			// Decrement amount of specific car by 1 and persist updates
			if (store.getAmount() > 0) {
				jpaServiceStatus = service.buyCar(car, customer, merchant,
						price);
				store.setAmount(store.getAmount() - 1);
				service.updateStore(store);
				statusJSON = mapper.jsonFromSpecialMessage(202,
						jpaServiceStatus);
			} else {
				jpaServiceStatus = String.format(
						"No more cars with id_car = %s in store", carId);
				statusJSON = mapper.jsonFromSpecialMessage(406,
						jpaServiceStatus);
				return Response.ok(statusJSON).build();
			}

		} catch (IOException e) {
			String error = mapper.jsonFromSpecialMessage(400,
					"Error during parse JSON. Please fix it");
			logger.error("IOException during 'buyCar': ", e);
			return Response.status(400).entity(error).build();

		}

		return Response.ok(statusJSON).build();
	}
}

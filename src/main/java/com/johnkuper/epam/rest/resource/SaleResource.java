package com.johnkuper.epam.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.jpaservice.JPAServiceImpl;
import com.johnkuper.epam.rest.mapper.OwnObjectMapper;

@Path("sales")
@Produces("application/json")
public class SaleResource {

	OwnObjectMapper mapper = new OwnObjectMapper();
	JPAServiceImpl service = new JPAServiceImpl();
	private final static String SALE_JSON_START = "{\"sales\":";
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	@POST
	@Path("/buyCar")
	@Consumes("application/json")
	public Response buyCar(String saleJSON) {
		return null;

	}
}

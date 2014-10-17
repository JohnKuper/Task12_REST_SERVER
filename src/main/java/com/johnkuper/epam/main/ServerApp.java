package com.johnkuper.epam.main;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johnkuper.epam.rest.resource.CarResource;
import com.johnkuper.epam.rest.resource.CustomerResource;
import com.johnkuper.epam.rest.resource.MerchantResource;
import com.johnkuper.epam.rest.resource.SaleResource;
import com.johnkuper.epam.rest.resource.StoreResource;

public class ServerApp {

	private static final URI BASE_URI = URI
			.create("http://localhost:8080/base/");
	public static final String ROOT_PATH = "store/all";
	final static Logger logger = LoggerFactory.getLogger("JohnKuper");

	public static void main(String[] args) {

		try {

			System.out.println("--- 'Auto Show' REST service ---");

			final ResourceConfig resourceConfig = new ResourceConfig(
					CarResource.class, StoreResource.class,
					CustomerResource.class, MerchantResource.class,
					SaleResource.class);
			final HttpServer server = GrizzlyHttpServerFactory
					.createHttpServer(BASE_URI, resourceConfig);

			System.out
					.println(String
							.format("Application started.\nTry out - %s%s\nHit enter to stop it...",
									BASE_URI, ROOT_PATH));
			System.in.read();
			server.shutdownNow();
		} catch (IOException ex) {
			logger.error("IOException: ", ex);
		}

	}
}

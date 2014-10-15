package com.johnkuper.epam.main;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App {

	private static final URI BASE_URI = URI
			.create("http://localhost:8080/base/");
	public static final String ROOT_PATH = "helloworld";

	public static void main(String[] args) {

		try {
			System.out.println("\"Hello World\" Jersey Example App");

			final ResourceConfig resourceConfig = new ResourceConfig(
					HelloWorldResource.class, TestResource.class);
			final HttpServer server = GrizzlyHttpServerFactory
					.createHttpServer(BASE_URI, resourceConfig);

			System.out
					.println(String
							.format("Application started.\nTry out %s%s\nHit enter to stop it...",
									BASE_URI, ROOT_PATH));
			System.in.read();
			server.shutdownNow();
		} catch (IOException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}

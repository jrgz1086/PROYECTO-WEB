package com.biblioteca.ws.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class RestServiceApplication extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public RestServiceApplication() {
		singletons.add(new CiudadRestService());
	}

	public Set<Object> getSingletons() {
		return singletons;
	}

	public Set<Class<?>> getEmpty() {
		return empty;
	}
}

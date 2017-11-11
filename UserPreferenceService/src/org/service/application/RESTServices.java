package org.service.application;

import javax.servlet.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.glassfish.jersey.server.*;

import org.service.object.*;

@ApplicationPath("rs")
public class RESTServices extends ResourceConfig {
    public RESTServices(@Context ServletContext servletContext) {
		servletContext.setAttribute("shared.storage", new GlobalSet());

		packages("com.fasterxml.jackson.jaxrs.json");
        packages("org.service.resource");
    }
}

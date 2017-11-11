package org.service.resource;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.logging.log4j.core.util.FileUtils;
import org.service.config.*;
import org.service.object.*;
import org.service.support.*;

import elsu.support.*;

@Path("preferences")
public class PreferenceServiceResource extends BaseConfigManager {
	private static String[] _RESULTKEYS = new String[] { "status", "message", "result" };

	public PreferenceServiceResource() {
	}

	@Override
	protected void initializeService() throws Exception {
		super.initializeService();
	}

	@POST
	@Path("search/{id}/{key}")
	//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response searchPreference(@PathParam("id") String pId, @PathParam("key") String pKey, String pContent) {
		String result = "";
		int status = 200;

		try {
			initializeService();

			PreferenceType preference = (PreferenceType) JsonXMLUtils.JSon2Object(pContent, PreferenceType.class);		
			result = PSSupportStore.readPreference(resources, config, pId, pKey, preference);
		} catch (Exception ex) {
			status = 400;
			result = PSSupportStore.array2JSON(_RESULTKEYS,
					new String[] { "ERROR", "params (" + pId + ", " + pKey + ")",
							"searchPreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace() });
			config.logError(result);
		}

		return Response.status(status).type(MediaType.APPLICATION_JSON)
				//.header("Access-Control-Allow-Origin", "*")
				//.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				//.header("Access-Control-Allow-Credentials", "true")
				//.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				//.header("Access-Control-Max-Age", "1209600")
				.entity(result).build();
	}

	@POST
	@Path("store/{id}/{key}")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Response storePreference(@PathParam("id") String pId, @PathParam("key") String pKey, String pContent) {
		String result = "";
		int status = 200;

		try {
			initializeService();

			PreferenceType preference = (PreferenceType) JsonXMLUtils.JSon2Object(pContent, PreferenceType.class);		
			Boolean stored = PSSupportStore.storePreference(resources, config, pId, pKey, preference);
			
			result = PSSupportStore.array2JSON(_RESULTKEYS,
					new String[] { (stored ? "SUCCESS" : "ERROR"), "params (" + pId + ", " + pKey + ")", stored.toString() });
		} catch (Exception ex) {
			status = 400;
			result = PSSupportStore.array2JSON(_RESULTKEYS,
					new String[] { "ERROR", "params (" + pId + ", " + pKey + ")",
							"storePreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace() });
			config.logError(result);
		}

		return Response.status(status).type(MediaType.APPLICATION_JSON)
				//.header("Access-Control-Allow-Origin", "*")
				//.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				//.header("Access-Control-Allow-Credentials", "true")
				//.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				//.header("Access-Control-Max-Age", "1209600")
				.entity(result).build();
	}

	@POST
	@Path("remove/{id}/{key}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response removePreference(@PathParam("id") String pId, @PathParam("key") String pKey, String pContent) {
		String result = "";
		int status = 200;

		try {
			initializeService();
			PreferenceType preference = (PreferenceType) JsonXMLUtils.JSon2Object(pContent, PreferenceType.class);		
			Boolean deleted = PSSupportStore.deletePreference(resources, config, pId, pKey, preference);
			
			result = PSSupportStore.array2JSON(_RESULTKEYS,
					new String[] { (deleted ? "SUCCESS" : "ERROR"), "params (" + pId + ", " + pKey + ")", deleted.toString() });
		} catch (Exception ex) {
			status = 400;
			result = PSSupportStore.array2JSON(_RESULTKEYS,
					new String[] { "ERROR", "params (" + pId + ", " + pKey + ")",
							"removePreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace() });
			config.logError(result);
		}

		return Response.status(status).type(MediaType.APPLICATION_JSON)
				//.header("Access-Control-Allow-Origin", "*")
				//.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				//.header("Access-Control-Allow-Credentials", "true")
				//.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				//.header("Access-Control-Max-Age", "1209600")
				.entity(result).build();
	}
}

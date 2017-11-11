package org.service.resource;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.service.config.*;
import org.service.object.*;
import org.service.support.*;

import elsu.support.ConfigLoader;

@Path("preferences")
public class PreferenceServiceResource extends BaseConfigManager {
	public PreferenceServiceResource() {
	}
	
	@Override
	protected void initializeService() throws Exception {
		super.initializeService();
	}
	
	@GET
	@Path("search/{id}/{key}")
	//@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	public Response searchPreference(@PathParam("id") String pId, @PathParam("key") String pKey) {
		String result = "";
		int status = 200;
		
		try {
			initializeService();
			result = PSSupportStore.readPreference(resources, config, pId, pKey);
		} catch (Exception ex) {
			status = 400;
			result = PSSupportStore.array2JSON(new String[]{"status", "message", "result"}, new String[]{"ERROR", "params (" + pId + ", " + pKey + ")", 
					"searchPreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace()});
			config.logError(result);
		}
		
		return Response.status(status)
				.type(MediaType.APPLICATION_JSON)
				//.header("Access-Control-Allow-Origin", "*")
				//.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				//.header("Access-Control-Allow-Credentials", "true")
				//.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				//.header("Access-Control-Max-Age", "1209600")
				.entity(result).build();
	}
	
	@PUT
	@Path("store/{id}/{key}")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Response storePreference(@PathParam("id") String pId, @PathParam("key") String pKey, String pContent) {
		String result = "";
		int status = 200;
		
		try {
			initializeService();
			Boolean stored = PSSupportStore.storePreference(resources, config, pId, pKey, pContent);
			result = PSSupportStore.array2JSON(new String[]{"status", "message", "result"}, new String[]{"SUCCESS", "params (" + pId + ", " + pKey + ")", stored.toString()});
		} catch (Exception ex) {
			status = 400;
			result = PSSupportStore.array2JSON(new String[]{"status", "message", "result"}, new String[]{"ERROR", "params (" + pId + ", " + pKey + ")", 
					"storePreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace()});
			config.logError(result);
		}
		
		return Response.status(status)
				.type(MediaType.APPLICATION_JSON)
				//.header("Access-Control-Allow-Origin", "*")
				//.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				//.header("Access-Control-Allow-Credentials", "true")
				//.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				//.header("Access-Control-Max-Age", "1209600")
				.entity(result).build();
	}
	
	@POST
	@Path("update/{id}/{key}")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Response updatePreference(@PathParam("id") String pId, @PathParam("key") String pKey, String pContent) {
		String result = "";
		int status = 200;

		try {
			initializeService();
			Boolean stored = PSSupportStore.storePreference(resources, config, pId, pKey, pContent);
			result = PSSupportStore.array2JSON(new String[]{"status", "message", "result"}, new String[]{"SUCCESS", "params (" + pId + ", " + pKey + ")", stored.toString()});
		} catch (Exception ex) {
			status = 400;
			result = PSSupportStore.array2JSON(new String[]{"status", "message", "result"}, new String[]{"ERROR", "params (" + pId + ", " + pKey + ")", 
					"updatePreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace()});
			config.logError(result);
		}
		
		return Response.status(status)
				.type(MediaType.TEXT_PLAIN)
				//.header("Access-Control-Allow-Origin", "*")
				//.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				//.header("Access-Control-Allow-Credentials", "true")
				//.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				//.header("Access-Control-Max-Age", "1209600")
				.entity(result).build();
	}
	
	@DELETE
	@Path("remove/{id}/{key}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response removePreference(@PathParam("id") String pId, @PathParam("key") String pKey) {
		String result = "";
		int status = 200;

		try {
			initializeService();
			Boolean deleted = PSSupportStore.deletePreference(resources, config, pId, pKey);
			result = PSSupportStore.array2JSON(new String[]{"status", "message", "result"}, new String[]{"SUCCESS", "params (" + pId + ", " + pKey + ")", deleted.toString()});
		} catch (Exception ex) {
			status = 400;
			result = PSSupportStore.array2JSON(new String[]{"status", "message", "result"}, new String[]{"ERROR", "params (" + pId + ", " + pKey + ")", 
					"removePreference(), \n" + ex.getMessage() + "\n" + ex.getStackTrace()});
			config.logError(result);
		}
		
		return Response.status(status)
				.type(MediaType.TEXT_PLAIN)
				//.header("Access-Control-Allow-Origin", "*")
				//.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				//.header("Access-Control-Allow-Credentials", "true")
				//.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				//.header("Access-Control-Max-Age", "1209600")
				.entity(result).build();
	}
}

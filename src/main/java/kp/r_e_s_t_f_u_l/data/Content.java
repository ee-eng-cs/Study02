package kp.r_e_s_t_f_u_l.data;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import kp.r_e_s_t_f_u_l.ResearchRestfulHelper;

/**
 * Root resource class <B>Content</B>.
 * 
 */
@Path("content")
public class Content {
	private static final Logger logger = Logger.getLogger(Content.class.getName());

	/**
	 * The resource method using @PathParam and @QueryParam.
	 * 
	 * @param patPar the path parameter
	 * @param quePar the query parameter
	 * @return the response
	 */
	@GET
	@Path("pq/{pat_par}")
	public String getUsingPathAndQuery(@PathParam("pat_par") String patPar, @QueryParam("que_par") String quePar) {

		final String message = String.format("patPar[%s], quePar[%s]", patPar, quePar);
		ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "getUsingPathAndQuery", message);

		final String response = "abc";
		logger.info(String.format("getUsingPathAndQuery(): %s", message));
		return response;
	}

	/**
	 * The resource method using @Context.
	 * 
	 * @param uriInfo the URI info
	 * @return the response
	 */
	@GET
	@Path("c/{pat_par}")
	public String getUsingContext(@Context UriInfo uriInfo) {

		final MultivaluedMap<String, String> queParMap = uriInfo.getQueryParameters();
		final MultivaluedMap<String, String> patParMap = uriInfo.getPathParameters();
		final String message = String.format("patPar%s, quePar%s", patParMap.get("pat_par"), queParMap.get("que_par"));
		ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "getUsingContext", message);

		final String response = "xyz";
		logger.info(String.format("getUsingContext(): %s", message));
		return response;
	}
}
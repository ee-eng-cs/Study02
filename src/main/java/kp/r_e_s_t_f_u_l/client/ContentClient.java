package kp.r_e_s_t_f_u_l.client;

import java.net.URI;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import kp.r_e_s_t_f_u_l.ResearchRestfulHelper;

/**
 * The wrapper of JAX-RS client for the <B>Content</B> objects.
 *
 */
public class ContentClient {
	private static final Logger logger = Logger.getLogger(ContentClient.class.getName());

	private static final URI[] CONTENT_URI_ARR = { /*-*/
			URI.create("http://localhost:8080/content/pq/"), /*-*/
			URI.create("http://localhost:8080/content/c/")/*-*/
	};

	/**
	 * Processes content client.
	 * 
	 */
	public static void process() {

		logger.info("process(): opening client-side communication");
		final Client client = ClientBuilder.newClient();
		for (int i = 0; i < CONTENT_URI_ARR.length; i++) {
			ResearchRestfulHelper.setNumber(i + 1);
			final URI uri = CONTENT_URI_ARR[i];
			if (i > 0) {
				ResearchRestfulHelper.addEmptyLineToReport();
			}
			final String uriMsg = String.format("calling uri[%s]", uri);
			ResearchRestfulHelper.addToReport(ContentClient.class.getSimpleName(), "process", uriMsg);

			final WebTarget target = client.target(uri);
			final Invocation.Builder invocationBuilder = target.path("P-a-t-P-a-r").queryParam("que_par", "Q-u-e-P-a-r")
					.request();
			final Response response = invocationBuilder.get();

			final String statusMsg = String.format("%s", response.getStatusInfo().getReasonPhrase());
			final String entityMsg = String.format("response entity[%s]", response.readEntity(String.class));
			ResearchRestfulHelper.addToReport(ContentClient.class.getSimpleName(), "process", statusMsg, entityMsg);

			logger.info(String.format("process(): %s, response status[%s], %s", uriMsg, statusMsg, entityMsg));
		}
		client.close();
		logger.info("process(): client-side communication closed");
	}
}

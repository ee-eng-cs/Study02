package kp.r_e_s_t_f_u_l.client;

import java.net.URI;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import kp.r_e_s_t_f_u_l.ResearchRestfulHelper;
import kp.r_e_s_t_f_u_l.data.Box;

/**
 * The wrapper of JAX-RS client for the <B>Box</B> objects.
 *
 */
public class BoxesClient {
	private static final Logger logger = Logger.getLogger(BoxesClient.class.getName());

	private static final URI BOXES_URI = URI.create("http://localhost:8080/boxes/");

	/**
	 * Processes boxes client.
	 * 
	 */
	public static void process() {

		logger.info("process(): opening client-side communication");
		final Client client = ClientBuilder.newClient();
		int number = 1;

		ResearchRestfulHelper.setNumber(number++);
		callCreate(client, "A");
		ResearchRestfulHelper.setNumber(number++);
		callCreate(client, "B");
		ResearchRestfulHelper.setNumber(number++);
		callCreate(client, "C");

		ResearchRestfulHelper.setNumber(number++);
		callRead(client, 1);
		ResearchRestfulHelper.setNumber(number++);
		callReadList(client);

		ResearchRestfulHelper.setNumber(number++);
		callUpdate(client, 1, "X");
		ResearchRestfulHelper.setNumber(number++);
		callUpdate(client, 1, "X");

		ResearchRestfulHelper.setNumber(number++);
		callDelete(client, 2);
		ResearchRestfulHelper.setNumber(number++);
		callReadList(client);

		ResearchRestfulHelper.setNumber(number++);
		// lower case letter is not valid pattern in 'Box'
		callCreate(client, "a");
		ResearchRestfulHelper.setNumber(number++);
		// lower case letter is not valid pattern in 'Box'
		callUpdate(client, 1, "a");
		ResearchRestfulHelper.setNumber(number++);
		callUpdate(client, 12345, "A");
		ResearchRestfulHelper.setNumber(number++);
		callDelete(client, 12345);

		client.close();
		logger.info("process(): client-side communication closed");
	}

	/**
	 * Calls entity creation.
	 * 
	 * @param client the client
	 * @param text   the text
	 */
	private static void callCreate(Client client, String text) {

		final WebTarget target = client.target(BOXES_URI);
		final Entity<Box> entity = Entity.entity(new Box(text), MediaType.APPLICATION_JSON);
		final Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildPost(entity);

		final Response response = invocation.invoke();
		final String statusMsg = response.getStatusInfo().getReasonPhrase();
		final String message = String.format("text[%s], location[%s]", text, response.getLocation());
		ResearchRestfulHelper.addToReport(BoxesClient.class.getSimpleName(), "callCreate", statusMsg, message);
		ResearchRestfulHelper.addEmptyLineToReport();
		logger.info(String.format("callCreate(): response status[%s], %s", statusMsg, message));
	}

	/**
	 * Calls entity reading.
	 * 
	 * @param client the client
	 * @param id     the id
	 */
	private static void callRead(Client client, int id) {

		final WebTarget target = client.target(BOXES_URI);
		final Invocation invocation = target.path(String.valueOf(id)).request(MediaType.APPLICATION_JSON).buildGet();

		final Response response = invocation.invoke();
		final String statusMsg = response.getStatusInfo().getReasonPhrase();
		final String message = String.format("entity[%s]", response.readEntity(String.class));
		ResearchRestfulHelper.addToReport(BoxesClient.class.getSimpleName(), "callRead", statusMsg, message);
		ResearchRestfulHelper.addEmptyLineToReport();
		logger.info(String.format("callRead(): response status[%s], %s", statusMsg, message));
	}

	/**
	 * Calls list reading.
	 * 
	 * @param client the client
	 */
	private static void callReadList(Client client) {

		final WebTarget target = client.target(BOXES_URI);
		final Invocation invocation = target.request(MediaType.APPLICATION_JSON).buildGet();

		final Response response = invocation.invoke();
		final String statusMsg = response.getStatusInfo().getReasonPhrase();
		final String message = String.format("entity%s", response.readEntity(String.class));
		ResearchRestfulHelper.addToReport(BoxesClient.class.getSimpleName(), "callReadList", statusMsg, message);
		ResearchRestfulHelper.addEmptyLineToReport();
		logger.info(String.format("callReadList(): response status[%s]%n\t%s", statusMsg, message));
	}

	/**
	 * Calls entity updating.
	 * 
	 * @param client the client
	 * @param id     the id
	 * @param text   the text
	 */
	private static void callUpdate(Client client, int id, String text) {

		final WebTarget target = client.target(BOXES_URI);
		final Entity<Box> entity = Entity.entity(new Box(id, text), MediaType.APPLICATION_JSON);
		final String idStr = String.valueOf(id);
		final Invocation invocation = target.path(idStr).request(MediaType.APPLICATION_JSON).buildPut(entity);

		final Response response = invocation.invoke();
		final String statusMsg = response.getStatusInfo().getReasonPhrase();
		final String message = String.format("id[%d], text[%s]", id, text);
		ResearchRestfulHelper.addToReport(BoxesClient.class.getSimpleName(), "callUpdate", statusMsg, message);
		ResearchRestfulHelper.addEmptyLineToReport();
		logger.info(String.format("callUpdate(): response status[%s], %s", statusMsg, message));
	}

	/**
	 * Calls entity deleting.
	 * 
	 * @param client the client
	 * @param id     the id
	 */
	private static void callDelete(Client client, int id) {

		final WebTarget target = client.target(BOXES_URI);
		final String idStr = String.valueOf(id);
		final Invocation invocation = target.path(idStr).request(MediaType.APPLICATION_JSON).buildDelete();

		final Response response = invocation.invoke();
		final String statusMsg = response.getStatusInfo().getReasonPhrase();
		final String message = String.format("id[%d]", id);
		ResearchRestfulHelper.addToReport(BoxesClient.class.getSimpleName(), "callDelete", statusMsg, message);
		ResearchRestfulHelper.addEmptyLineToReport();
		logger.info(String.format("callDelete(): response status[%s], %s", statusMsg, message));
	}
}
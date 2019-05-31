package kp.r_e_s_t_f_u_l.data;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import kp.r_e_s_t_f_u_l.ResearchRestfulHelper;

/**
 * Root resource class <B>SetOfBoxes</B>.<BR>
 * The set of boxes.<BR>
 * CRUD: create, read, update, and delete boxes.
 * 
 */
@Path("boxes")
public class SetOfBoxes {
	private static final Logger logger = Logger.getLogger(SetOfBoxes.class.getName());

	private static final Map<Integer, Box> boxMap = new TreeMap<>();
	private static int nextId = 1;

	/**
	 * Reseting data store for repeatable runs of the client.
	 * 
	 */
	public static void reset() {
		boxMap.clear();
		nextId = 1;
	}

	/**
	 * <B>CREATE</B> single box (with validation enabled).
	 * 
	 * @param box the box
	 * @return the box
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createBox(@Valid Box box) {

		final int id = nextId++;
		box.setId(id);
		boxMap.put(id, box);
		final URI uri = URI.create(String.format("boxes/%d", id));
		final Response response = Response.created(uri).build();

		final String message = String.format("id[%d], boxMap size[%d]", id, boxMap.size());
		ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "createBox", message);
		logger.info(String.format("createBox(): %s", message));
		return response;
	}

	/**
	 * <B>READ</B> single box.
	 *
	 * @param id the id
	 * @return the box
	 */
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Box readBox(@PathParam("id") int id) {

		final Box box = boxMap.get(id);

		final String message = String.format("id[%d], boxMap size[%d]", id, boxMap.size());
		ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "readBox", message);
		logger.info(String.format("readBox(): %s", message));
		return box;
	}

	/**
	 * <B>READ</B> all boxes.
	 * 
	 * @return the boxList
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Box> readBoxes() {

		final List<Box> boxList = boxMap.values().stream().collect(Collectors.toList());
		final String message = String.format("boxMap size[%d]", boxMap.size());
		ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "readBoxes", message);
		logger.info(String.format("readBoxes(): %s", message));
		return boxList;
	}

	/**
	 * <B>UPDATE</B> single box (with validation enabled).
	 * 
	 * @param id  the id
	 * @param box the box
	 * @return the response
	 */
	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateBox(@PathParam("id") int id, @Valid Box box) {

		if (!boxMap.containsKey(id)) {
			final Response response = Response.status(Status.NOT_FOUND).build();
			final String message = String.format("not updated, bad id[%d], boxMap size[%d]", id, boxMap.size());
			ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "updateBox", message);
			logger.warning(String.format("updateBox(): %s", message));
			return response;
		}
		boxMap.put(id, box);
		final Response response = Response.ok().status(Status.SEE_OTHER).build();
		final String message = String.format("id[%d], boxMap size[%d]", id, boxMap.size());
		ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "updateBox", message);
		logger.info(String.format("updateBox(): %s", message));
		return response;
	}

	/**
	 * <B>DELETE</B> single box.
	 *
	 * @param id the id
	 * @return the response
	 */
	@DELETE
	@Path("{id}")
	public Response deleteBox(@PathParam("id") int id) {

		if (!boxMap.containsKey(id)) {
			final Response response = Response.status(Status.NOT_FOUND).build();
			final String message = String.format("not deleted, bad id[%d], boxMap size[%d]", id, boxMap.size());
			ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "deleteBox", message);
			logger.warning(String.format("deleteBox(): %s", message));
			return response;
		}
		boxMap.remove(id);
		final Response response = Response.noContent().build();
		final String message = String.format("id[%d], boxMap size[%d]", id, boxMap.size());
		ResearchRestfulHelper.addToReport(this.getClass().getSimpleName(), "deleteBox", message);
		logger.info(String.format("deleteBox(): %s", id, boxMap.size()));
		return response;
	}
}
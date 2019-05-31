package kp.r_e_s_t_f_u_l;

import java.io.IOException;
import java.net.URI;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

import kp.r_e_s_t_f_u_l.client.BoxesClient;
import kp.r_e_s_t_f_u_l.client.ContentClient;
import kp.r_e_s_t_f_u_l.data.SetOfBoxes;

/**
 * Research RESTful Web Services using JDK HTTP Server.<br>
 * <a href= "https://jersey.github.io/documentation/latest/user-guide.html"
 * >https://jersey.github.io/documentation/latest/user-guide.html</a>
 * 
 */
public class ResearchRestful {

	private static final URI ROOT_URI = URI.create("http://localhost:8080/");
	private static final ResourceConfig RESOURCE_CONFIG = new ResourceConfig().packages("kp.r_e_s_t_f_u_l.data");

	/**
	 * Runs HTTP servers.
	 *
	 */
	public void process() {
		/*- With 'JdkHttpServerFactory' creates the HttpServer instance configured as a Jersey container */

		HttpServer httpServer;
		LOOP: while (true) {
			int result = ResearchRestfulHelper.showButtons();
			switch (result) {
			case 0:
				httpServer = JdkHttpServerFactory.createHttpServer(ROOT_URI, RESOURCE_CONFIG);
				ResearchRestfulHelper.clearReport();
				ContentClient.process();
				httpServer.stop(1);
				ResearchRestfulHelper.showContentResults();
				break;
			case 1:
				httpServer = JdkHttpServerFactory.createHttpServer(ROOT_URI, RESOURCE_CONFIG);
				SetOfBoxes.reset();
				ResearchRestfulHelper.clearReport();
				BoxesClient.process();
				httpServer.stop(1);
				ResearchRestfulHelper.showBoxesResults();
				break;
			case 2:
				httpServer = JdkHttpServerFactory.createHttpServer(ROOT_URI, RESOURCE_CONFIG);
				SetOfBoxes.reset();
				ResearchRestfulHelper.clearReport();
				ResearchRestfulHelper.setNumber(1);
				pause();
				httpServer.stop(1);
				ResearchRestfulHelper.showBoxesExternalResults();
				break;
			default:
				// Go Back
				break LOOP;
			}
		}
	}

	/**
	 * Pause this application for running 'curl' in external batch.
	 * 
	 */
	private static void pause() {

		System.out.println("\nPress 'Enter' to shutdown server.\n");
		try {
			System.in.read();
		} catch (IOException e) {
			// ignore
		}
	}
}
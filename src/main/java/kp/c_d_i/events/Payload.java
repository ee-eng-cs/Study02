package kp.c_d_i.events;

import java.io.Serializable;

/**
 * Payload.
 *
 */
public class Payload implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;

	/**
	 * Constructor.
	 * 
	 * @param content the content
	 * 
	 */
	public Payload(String content) {
		super();
		this.content = content;
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
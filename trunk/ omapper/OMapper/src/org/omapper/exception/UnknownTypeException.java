/**
 * 
 */
package org.omapper.exception;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class UnknownTypeException.
 *
 * @author Sachin
 */
public class UnknownTypeException extends RuntimeException  {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UnknownTypeException.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new unknown type exception.
	 */
	public UnknownTypeException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new unknown type exception.
	 *
	 * @param message the message
	 */
	public UnknownTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new unknown type exception.
	 *
	 * @param cause the cause
	 */
	public UnknownTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new unknown type exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public UnknownTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}

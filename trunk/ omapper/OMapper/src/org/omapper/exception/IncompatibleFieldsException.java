/**
 * 
 */
package org.omapper.exception;

import org.apache.log4j.Logger;

/**
 * The Class IncompatibleFieldsException.
 * 
 * @author Sachin
 */
public class IncompatibleFieldsException extends RuntimeException {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IncompatibleFieldsException.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new incompatible fields exception.
	 */
	public IncompatibleFieldsException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new incompatible fields exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public IncompatibleFieldsException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new incompatible fields exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public IncompatibleFieldsException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new incompatible fields exception.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public IncompatibleFieldsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

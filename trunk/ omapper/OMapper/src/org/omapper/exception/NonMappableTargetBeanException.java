/**
 * 
 */
package org.omapper.exception;

import org.apache.log4j.Logger;

/**
 * The Class NonMappableTargetBeanException.
 * 
 * @author Sachin
 */
public class NonMappableTargetBeanException extends RuntimeException {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NonMappableTargetBeanException.class);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4494097297587455660L;

	/**
	 * Instantiates a new non mappable target bean exception.
	 */
	public NonMappableTargetBeanException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new non mappable target bean exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public NonMappableTargetBeanException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new non mappable target bean exception.
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public NonMappableTargetBeanException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new non mappable target bean exception.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 */
	public NonMappableTargetBeanException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}

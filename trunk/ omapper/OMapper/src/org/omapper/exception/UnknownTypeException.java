/**
 * 
 */
package org.omapper.exception;

/**
 * @author Sachin
 *
 */
public class UnknownTypeException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UnknownTypeException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public UnknownTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public UnknownTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnknownTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}

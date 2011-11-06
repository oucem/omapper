/**
 * 
 */
package org.omapper.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface Source.
 *
 * @author Sachin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Source {
	
	/**
	 * Type.
	 *
	 * @return the class
	 */
	public Class type();
	
	/**
	 * Property.
	 *
	 * @return the string
	 */
	public String property();
	
	
	
	

}

/**
 * 
 */
package org.omapper.mapper;

import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

/**
 * @author Sachin
 * 
 */

public class CollatingMapper<T>  {

	public CollatingMapper(Class<T> targetClass, Class<? extends Object>... sourceClasses) {

		
	}

	public void mapBean(Object target, Object... source)
			throws UnableToMapException, UnknownPropertyException,
			UnknownTypeException {
		// TODO Auto-generated method stub

	}

}

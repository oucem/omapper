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
public class SimpleMapper<T,S> extends AbstractMapper<T, S>{

	

	@SuppressWarnings("unchecked")
	public SimpleMapper(Class<T> targetClass, Class<S> sourceClass) {
		super(targetClass,sourceClass);
	}

	public void mapBean(T target, S source) throws UnableToMapException, UnknownPropertyException,
	UnknownTypeException  {
		
		
		
	}

	

}

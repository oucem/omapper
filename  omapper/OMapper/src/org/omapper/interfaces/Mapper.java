package org.omapper.interfaces;

import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

public interface Mapper<T,S> {
	
	/**
	 * @param target
	 * @param source
	 * @throws UnableToMapException
	 * @throws UnknownPropertyException
	 * @throws UnknownTypeException
	 */
	public void mapBean(T target, S... source) throws UnableToMapException,UnknownPropertyException,UnknownTypeException;

}

package org.omapper.interfaces;

import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

// TODO: Auto-generated Javadoc
/**
 * The Interface Mapper.
 *
 * @param <T> the generic type
 * @param <S> the generic type
 */
public interface Mapper<T,S> {
	
	/**
	 * Map bean.
	 *
	 * @param target the target
	 * @param source the source
	 * @throws UnableToMapException the unable to map exception
	 * @throws UnknownPropertyException the unknown property exception
	 * @throws UnknownTypeException the unknown type exception
	 */
	public void mapBean(T target, S... source) throws UnableToMapException,UnknownPropertyException,UnknownTypeException;

}

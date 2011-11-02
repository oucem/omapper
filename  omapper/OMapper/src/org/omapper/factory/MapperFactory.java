/**
 * 
 */
package org.omapper.factory;

import org.omapper.enums.MapperType;
import org.omapper.interfaces.Mapper;
import org.omapper.mapper.SimpleMapper;
import org.omapper.mapper.CollatingMapper;

/**
 * @author Sachin
 * 
 */
public class MapperFactory {

	public static <T, S> Mapper<T, S> getMapper(Class<T> targetClass,
			Class<S> sourceClass, MapperType mapperType) {
		Mapper<T, S> mapper = null;
		switch (mapperType) {
		case EAGER:
			mapper = new SimpleMapper<T, S>(targetClass,sourceClass);
			break;
		case LAZY:
			mapper = new CollatingMapper<T, S>(targetClass,sourceClass);
			break;

		default:
			mapper = new CollatingMapper<T, S>(targetClass,sourceClass);
		}
		return mapper;
	}
	
	
	public static <T> Mapper<T,Object> getCollatingMapper(Class<T> targetClass, Class<Object>... sourceClass) 
	{
		
		return null;
	}

}

/**
 * 
 */
package org.omapper.mapper;

import java.lang.reflect.Field;

import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;


/**
 * This mapper maps one bean class to another
 *
 * @param <T> the generic type for target bean
 * @param <S> the generic type for source bean
 * @author Sachin
 */
public class SimpleMapper<T, S> extends AbstractMapper {

	
	/**
	 * Instantiates a new simple mapper.
	 *
	 * @param targetClass the target class
	 * @param sourceClass the source class
	 */
	public SimpleMapper(Class<T> targetClass, Class<S> sourceClass) {
		super(targetClass, sourceClass);
	}

	/**
	 * Map bean.
	 *
	 * @param target the target
	 * @param source the source
	 * @throws UnableToMapException the unable to map exception
	 * @throws UnknownPropertyException the unknown property exception
	 * @throws UnknownTypeException the unknown type exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public void mapBean(T target, S source) throws UnableToMapException,
			UnknownPropertyException, UnknownTypeException,
			IllegalArgumentException, IllegalAccessException {

		Field[] targetFields = target.getClass().getDeclaredFields();
		for (Field targetField : targetFields) {
			targetField.setAccessible(true);
			String fieldName = targetField.getName();
			MapEntry entry = fieldMappingMap.get(fieldName);
			if (entry != null) {
				Field sourceField = fieldMappingMap.get(fieldName)
						.getSourceField();

				targetField.set(target, sourceField.get(source));
			}
		}

	}

}

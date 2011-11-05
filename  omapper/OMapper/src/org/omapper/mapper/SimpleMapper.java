/**
 * 
 */
package org.omapper.mapper;

import java.lang.reflect.Field;

import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

/**
 * @author Sachin
 * 
 */
public class SimpleMapper<T, S> extends AbstractMapper {

	
	public SimpleMapper(Class<T> targetClass, Class<S> sourceClass) {
		super(targetClass, sourceClass);
	}

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

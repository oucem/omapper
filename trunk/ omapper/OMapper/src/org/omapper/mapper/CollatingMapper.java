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

public class CollatingMapper<T> extends AbstractMapper {

	public CollatingMapper(Class<T> targetClass,
			Class<? extends Object>... sourceClasses) {

		super(targetClass, sourceClasses);
	}

	public void mapBean(Object target, Object... source)
			throws UnableToMapException, UnknownPropertyException,
			UnknownTypeException, IllegalArgumentException, IllegalAccessException {
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

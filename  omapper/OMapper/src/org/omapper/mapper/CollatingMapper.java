/**
 * 
 */
package org.omapper.mapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

// TODO: Auto-generated Javadoc
/**
 * This mapper is used to collate data from mutiple beans to one bean
 *
 * @param <T> the generic type
 * @author Sachin
 */

public class CollatingMapper<T> extends AbstractMapper {

	/**
	 * Instantiates a new collating mapper.
	 *
	 * @param targetClass the target class
	 * @param sourceClasses the source classes
	 */
	public CollatingMapper(Class<T> targetClass,
			Class<? extends Object>... sourceClasses) {

		super(targetClass, sourceClasses);
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
	public void mapBean(Object target, Object... source)
			throws UnableToMapException, UnknownPropertyException,
			UnknownTypeException, IllegalArgumentException, IllegalAccessException {
		
		Map<String, Object> sourceObjectMap = new HashMap<String,Object>();

		for (Object sourceObject : source) {
			System.out.println("Source Object:"+sourceObject);
			System.out.println("Source class:"+sourceObject.getClass());
			System.out.println("Source class:"+sourceObject.getClass().getCanonicalName());
			System.out.println("Source class:"+sourceObject.getClass().getName());
			System.out.println("Source class:"+sourceObject.getClass().getClass());
			sourceObjectMap.put(sourceObject.getClass().getCanonicalName(), sourceObject);
		}
		
		Field[] targetFields = target.getClass().getDeclaredFields();
		for (Field targetField : targetFields) {
			targetField.setAccessible(true);
			String fieldName = targetField.getName();
			MapEntry entry = fieldMappingMap.get(fieldName);
			if (entry != null) {
				Field sourceField = fieldMappingMap.get(fieldName)
						.getSourceField();
				Object sourceObject=sourceObjectMap.get(sourceField.getDeclaringClass().getCanonicalName());
				targetField.set(target, sourceField.get(sourceObject));
			}
		}

	}

}

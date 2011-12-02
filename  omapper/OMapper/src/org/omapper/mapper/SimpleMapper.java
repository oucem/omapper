/**
 * 
 */
package org.omapper.mapper;

import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

// TODO: Auto-generated Javadoc
/**
 * This mapper maps one bean class to another.
 *
 * @param <T> the generic type for target bean
 * @param <S> the generic type for source bean
 * @author Sachin
 */
public class SimpleMapper<T, S> extends AbstractMapper {

	/**
	 * Instantiates a new simple mapper.
	 * 
	 * @param targetClass
	 *            the target class
	 * @param sourceClass
	 *            the source class
	 */
	public SimpleMapper(Class<T> targetClass, Class<S> sourceClass) {
		super(targetClass, sourceClass);
	}

	/**
	 * Map bean.
	 *
	 * @param target the target
	 * @param source the source
	 */
	public void mapBean(T target, S source) {
		
		super.mapBean(target, source);
		//mapBeanBasic(target, source);
	}

	/**
	 * Map bean.
	 * 
	 * @param target
	 *            the target
	 * @param source
	 *            the source
	 * @throws UnableToMapException
	 *             the unable to map exception
	 * @throws UnknownPropertyException
	 *             the unknown property exception
	 * @throws UnknownTypeException
	 *             the unknown type exception
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 *//*
	private void mapBeanBasic(Object target, Object source) {

		try {
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
		} catch (IllegalArgumentException e) {
			throw new UnableToMapException(
					"Unable to map beans successfully due to an unexpected error: ",
					e);
		} catch (IllegalAccessException e) {
			throw new UnableToMapException(
					"Unable to map beans successfully due to an unexpected error: ",
					e);
		}

	}*/
	
	
}

package org.omapper.mapper;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.omapper.annotations.Source;
import org.omapper.exception.UnknownTypeException;

/**
 * 
 * @author Sachin
 * 
 */
public abstract class AbstractMapper<T, S> {

	protected Map<String, MapEntry> fieldMappingMap;

	@SuppressWarnings("rawtypes")
	protected void initFieldMaps(Class targetClass, Class... sourceClass)
			throws NoSuchFieldException {

		Map<String, Class> sourceClassMap = new HashMap<String, Class>();

		for (Class source : sourceClass) {
			sourceClassMap.put(source.getCanonicalName(), source);
		}

		Field[] targetFieldsArray = targetClass.getDeclaredFields();
		for (Field targetField : targetFieldsArray) {
			targetField.setAccessible(true);
			if (checkIfAnnotationsPresent(targetField)) {
				Source sourceAnnotation = targetField
						.getAnnotation(Source.class);
				if (null != sourceAnnotation) {
					String sourceFieldName = sourceAnnotation.property();
					Class sourceClassName = sourceAnnotation.type();
					if (!sourceClassMap.containsKey(sourceClassName)) {
						throw new UnknownTypeException();
					}
					Field sourceField = sourceClassName
							.getDeclaredField(sourceFieldName);
					sourceField.setAccessible(true);
					MapEntry entry = new MapEntry(sourceField, targetField);
					fieldMappingMap.put(targetField.getName(), entry);
				}
			}
		}

		System.out.println(fieldMappingMap);

	}

	/**
	 * To be Done
	 */
	private boolean checkIfAnnotationsPresent(Field targetField) {
		// TODO Auto-generated method stub
		return true;
	}

	public AbstractMapper(Class<T> targetClass, Class<S>... sourceClass) {

		try {
			fieldMappingMap = new HashMap<String, MapEntry>();
			initFieldMaps(targetClass, sourceClass);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

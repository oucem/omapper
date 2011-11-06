package org.omapper.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.omapper.annotations.Source;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

/**
 * 
 * @author Sachin
 * 
 */
public abstract class AbstractMapper {

	protected Map<String, MapEntry> fieldMappingMap;

	@SuppressWarnings("rawtypes")
	protected void initFieldMaps(Class targetClass, Class... sourceClass){

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
					if (!sourceClassMap.containsKey(sourceClassName
							.getCanonicalName())) {
						throw new UnknownTypeException(
								"The source class in annotation :"
										+ sourceClassName + " is not valid, valid values for the type: "+Arrays.toString(sourceClass));

					}
					
					try{
					Field sourceField = sourceClassName
							.getDeclaredField(sourceFieldName);
					
					sourceField.setAccessible(true);
					MapEntry entry = new MapEntry(sourceField, targetField);
					fieldMappingMap.put(targetField.getName(), entry);
					}
					catch(NoSuchFieldException e)
					{
						throw new UnknownPropertyException("Property defined in annotation :"+ sourceFieldName+ " is not prsent in source type:"+sourceClassName, e);
					}
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

	public AbstractMapper(Class targetClass, Class... sourceClass) {

		
			fieldMappingMap = new HashMap<String, MapEntry>();
			initFieldMaps(targetClass, sourceClass);
		

	}
}

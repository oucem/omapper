package org.omapper.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.omapper.annotations.Source;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

/**
 * The Class AbstractMapper.
 *
 * @author Sachin
 */
public abstract class AbstractMapper {

	/** The field mapping map. */
	protected Map<String, MapEntry> fieldMappingMap;

	/**
	 * Inits the field maps.
	 *
	 * @param targetClass the target class
	 * @param sourceClass the source class
	 */
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
						throw new UnknownPropertyException("Source Property:"+sourceFieldName+" defined in annotation for field :"+ targetField+ " is not prsent in source type:"+sourceClassName);
					}
				}
			}
		}

		System.out.println(fieldMappingMap);

	}

	/**
	 * To be Done.
	 *
	 * @param targetField the target field
	 * @return true, if successful
	 */
	private boolean checkIfAnnotationsPresent(Field targetField) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * Instantiates a new abstract mapper.
	 *
	 * @param targetClass the target class
	 * @param sourceClass the source class
	 */
	@SuppressWarnings("rawtypes")
	public AbstractMapper(Class targetClass, Class... sourceClass) {

		
			fieldMappingMap = new HashMap<String, MapEntry>();
			initFieldMaps(targetClass, sourceClass);
		

	}
}

/*
 * 
 */
package org.omapper.mapper;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.omapper.annotations.Mappable;
import org.omapper.annotations.Source;
import org.omapper.exception.NonMappableTargetBeanException;
import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;

/**
 * The Class AbstractMapper.
 * 
 * @author Sachin
 */
public abstract class AbstractMapper {

	/** The field mapping map. */
	protected final Map<String, MapEntry> fieldMappingMap;

	/**
	 * Instantiates a new abstract mapper.
	 * 
	 * @param targetClass
	 *            the target class
	 * @param sourceClass
	 *            the source class
	 */
	@SuppressWarnings("rawtypes")
	public AbstractMapper(Class targetClass, Class... sourceClass) {

		fieldMappingMap = new HashMap<String, MapEntry>();
		initFieldMaps(targetClass, sourceClass);

	}

	/**
	 * Inits the field maps.
	 * 
	 * @param targetClass
	 *            the target class
	 * @param sourceClass
	 *            the source class
	 */
	@SuppressWarnings("rawtypes")
	protected void initFieldMaps(Class targetClass, Class... sourceClass) {

		checkIfMappable(targetClass);

		Map<String, Class> sourceClassMap = new HashMap<String, Class>();

		for (Class source : sourceClass) {
			sourceClassMap.put(source.getCanonicalName(), source);
		}

		Field[] targetFieldsArray = targetClass.getDeclaredFields();
		for (Field targetField : targetFieldsArray) {

			targetField.setAccessible(true);
			if (targetField.isAnnotationPresent(Source.class)) {
				Source sourceAnnotation = targetField
						.getAnnotation(Source.class);
				if (null != sourceAnnotation) {
					String sourceFieldName = sourceAnnotation.property();
					Class sourceClassName = sourceAnnotation.type();
					if (!sourceClassMap.containsKey(sourceClassName
							.getCanonicalName())) {
						throw new UnknownTypeException(
								"The source class in annotation :"
										+ sourceClassName
										+ " is not valid, valid values for the type: "
										+ Arrays.toString(sourceClass));

					}

					try {
						Field sourceField = sourceClassName
								.getDeclaredField(sourceFieldName);

						sourceField.setAccessible(true);
						MapEntry entry = new MapEntry(sourceField, targetField);
						fieldMappingMap.put(
								constructFieldMappingKey(targetField), entry);

						if (targetField.getType().isAnnotationPresent(
								Mappable.class)) {
							initFieldMaps(targetField.getType(),
									sourceField.getType());
						}
					} catch (NoSuchFieldException e) {
						throw new UnknownPropertyException("Source Property:"
								+ sourceFieldName
								+ " defined in annotation for field :"
								+ targetField
								+ " is not prsent in source type:"
								+ sourceClassName);
					}
				}
			} else {
				System.out.println("No annotation mapping found for field:"
						+ targetField + " so skipping it");
			}

		}

		System.out.println(fieldMappingMap);

	}

	/**
	 * @param targetField
	 * @return
	 */
	private String constructFieldMappingKey(Field targetField) {

		StringBuilder key = new StringBuilder(targetField.getDeclaringClass()
				.getCanonicalName()).append('.').append(targetField.getName());
		return key.toString();
	}

	/**
	 * Check if mappable.
	 * 
	 * @param annotatedElements
	 *            the annotated elements
	 */
	protected void checkIfMappable(AnnotatedElement... annotatedElements) {

		if (annotatedElements != null) {
			for (AnnotatedElement element : annotatedElements) {
				if (!element.isAnnotationPresent(Mappable.class)) {
					throw new NonMappableTargetBeanException(
							"Target Bean Class:"
									+ element
									+ " is not mappable.\n Please add @Mappable annotation to the beans which needs to managed by OMapper");
				}
			}
		}

	}

	/**
	 * Map bean.
	 * 
	 * @param target
	 *            the target
	 * @param source
	 *            the source
	 * @throws InstantiationException
	 */
	protected void mapBean(Object target, Object... source) {
		try {
			Map<String, Object> sourceObjectMap = new HashMap<String, Object>();

			for (Object sourceObject : source) {
				System.out.println("Source Object:" + sourceObject);
				System.out.println("Source class:" + sourceObject.getClass());
				System.out.println("Source class:"
						+ sourceObject.getClass().getCanonicalName());
				System.out.println("Source class:"
						+ sourceObject.getClass().getName());
				System.out.println("Source class:"
						+ sourceObject.getClass().getClass());
				sourceObjectMap.put(sourceObject.getClass().getCanonicalName(),
						sourceObject);
			}

			Field[] targetFields = target.getClass().getDeclaredFields();
			for (Field targetField : targetFields) {
				targetField.setAccessible(true);
				MapEntry entry = fieldMappingMap
						.get(constructFieldMappingKey(targetField));
				if (entry != null) {
					Field sourceField = entry.getSourceField();
					Object sourceObject = sourceObjectMap.get(sourceField
							.getDeclaringClass().getCanonicalName());
					// recursively map the enclosed beans too
					if (targetField.getType().isAnnotationPresent(
							Mappable.class)) {
						Object targetObject = targetField.getType()
								.newInstance();
						mapBean(targetObject, sourceField.get(sourceObject));
						targetField.set(target, targetObject);
					} else {

						targetField.set(target, sourceField.get(sourceObject));
					}
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
		} catch (InstantiationException e) {
			throw new UnableToMapException(
					"Unable to map beans successfully due to an unexpected error: ",
					e);
		}

	}

}

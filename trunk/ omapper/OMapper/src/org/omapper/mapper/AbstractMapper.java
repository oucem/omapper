/*
 * 
 */
package org.omapper.mapper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.omapper.annotations.Mappable;
import org.omapper.annotations.Sink;
import org.omapper.annotations.Source;
import org.omapper.enums.FieldType;
import org.omapper.enums.MappingType;
import org.omapper.exception.IncompatibleFieldsException;
import org.omapper.exception.UnableToMapException;
import org.omapper.exception.UnknownPropertyException;
import org.omapper.exception.UnknownTypeException;
import org.omapper.util.MapperUtil;

/**
 * The Class AbstractMapper.
 * 
 * @author Sachin
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractMapper {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AbstractMapper.class);

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
	protected void initFieldMaps(Class targetClass, Class... sourceClass) {
		if (logger.isDebugEnabled()) {
			logger.debug("initFieldMaps(Class, Class) - start"); //$NON-NLS-1$
		}

		Map<String, Class> sourceClassMap = new HashMap<String, Class>();
		// cache class objects in map
		for (Class source : sourceClass) {
			sourceClassMap.put(source.getCanonicalName(), source);
		}

		MappingType mappingType = MapperUtil.getMappingType(targetClass,
				sourceClass);

		switch (mappingType) {
		case SOURCE:
			System.out.println("MApping Type:Source");
			initFieldMapFromSource(targetClass, sourceClassMap);
			break;
		case TARGET:
			System.out.println("MApping Type:Target");
			initFieldMapFromTarget(targetClass, sourceClassMap);
			break;
		}

		System.out.println(fieldMappingMap);

		if (logger.isDebugEnabled()) {
			logger.debug("initFieldMaps(Class, Class) - end"); //$NON-NLS-1$
		}
	}

	private void initFieldMapFromSource(Class targetClass,
			Map<String, Class> sourceClassMap) {
		// TODO Auto-generated method stub

		for (Map.Entry<String, Class> entry : sourceClassMap.entrySet()) {
			Field[] sourceFieldArray = entry.getValue().getDeclaredFields();
			for (Field sourceField : sourceFieldArray) {
				String sourceFieldName = sourceField.getName();
				sourceField.setAccessible(true);

				FieldType fieldType = MapperUtil.getFieldType(sourceField);
				System.out.println("Found target Field:" + sourceFieldName
						+ " of type:" + fieldType);

				if (!sourceField.isAnnotationPresent(Sink.class)) {

					System.out.println("No annotation mapping found for field:"
							+ sourceField + " so skipping it");
					continue;
				}

				Sink targetAnnotation = sourceField.getAnnotation(Sink.class);

				if (null == targetAnnotation) {
					System.out.println("No target annotation found for field :"
							+ sourceField + " so skipping it");
					continue;
				}

				String targetFieldName = targetAnnotation.property();
				Class targetClassName = targetAnnotation.type();
				if (!targetClassName.getCanonicalName().equals(
						targetClass.getCanonicalName())) {
					System.out
							.println("Target Class specified is different from parameters for field:"
									+ sourceFieldName
									+ " Specified:"
									+ targetClassName.getCanonicalName()
									+ " Allowed Target Class:"
									+ targetClass.getCanonicalName()
									+ " so skipping it");
					continue;
				}

				try {
					Field targetField = targetClassName
							.getDeclaredField(targetFieldName);

					targetField.setAccessible(true);
					MapEntry entryFieldMap = new MapEntry(sourceField,
							targetField);
					String fieldMappingKey = MapperUtil
							.constructFieldMappingKey(targetField);
					if (!fieldMappingMap.containsKey(fieldMappingKey)) {
						fieldMappingMap.put(fieldMappingKey, entryFieldMap);
					}
					if (sourceField.getType().isAnnotationPresent(
							Mappable.class)
							|| targetField.getType().isAnnotationPresent(
									Mappable.class)) {
						initFieldMaps(targetField.getType(),
								sourceField.getType());
					} else if (Collection.class.isAssignableFrom(targetField
							.getType())
							|| Map.class
									.isAssignableFrom(targetField.getType())) {
						if ((targetField.getGenericType() == targetField
								.getType())
								&& (sourceField.getGenericType() == sourceField
										.getType())) {
							System.out
									.println("Found non parameterized collections field:"
											+ targetField
											+ " skipping it as not supported yet");
						} else {
							initFieldMaps(
									(ParameterizedType) targetField
											.getGenericType(),
									(ParameterizedType) sourceField
											.getGenericType());

						}

					} else if (targetField.getType().isArray()
							&& sourceField.getType().isArray()) {
						if (!sourceField.getType().getComponentType()
								.isPrimitive() && (!sourceField.getType().getComponentType().equals(String.class))) {
							initFieldMaps(targetField.getType()
									.getComponentType(), sourceField.getType()
									.getComponentType());
						}
					}
				} catch (NoSuchFieldException e) {
					logger.error(
							"initFieldMapFromTarget(Class, Map<String,Class>)", e); //$NON-NLS-1$

					throw new UnknownPropertyException("Sink Property:"
							+ targetFieldName
							+ " defined in annotation for field :"
							+ sourceFieldName
							+ " is not prsent in target type:"
							+ targetClassName);
				}

			}
		}

	}

	private void initFieldMapFromTarget(Class targetClass,
			Map<String, Class> sourceClassMap) {
		if (logger.isDebugEnabled()) {
			logger.debug("initFieldMapFromTarget(Class, Map<String,Class>) - start"); //$NON-NLS-1$
		}

		Field[] targetFieldsArray = targetClass.getDeclaredFields();
		for (Field targetField : targetFieldsArray) {

			String targetFieldName = targetField.getName();
			targetField.setAccessible(true);

			FieldType fieldType = MapperUtil.getFieldType(targetField);
			System.out.println("Found target Field:" + targetFieldName
					+ " of type:" + fieldType);

			if (!targetField.isAnnotationPresent(Source.class)) {

				System.out.println("No annotation mapping found for field:"
						+ targetField + " so skipping it");
			} else {

				Source sourceAnnotation = targetField
						.getAnnotation(Source.class);
				if (null == sourceAnnotation) {
					System.out.println("No source annotation found for field :"
							+ targetField + " so skipping it");
					continue;
				} else {

					switch (fieldType) {
					case ARRAY:
					case COLLECTION:
					case ENUM:
					case JAVA:
					case MAP:
					case TEMPLATE:
					case USER:

					}
				}

				if (null != sourceAnnotation) {
					String sourceFieldName = sourceAnnotation.property();
					Class sourceClassName = sourceAnnotation.type();
					if (!sourceClassMap.containsKey(sourceClassName
							.getCanonicalName())) {
						throw new UnknownTypeException(
								"The source class in annotation :"
										+ sourceClassName
										+ " is not valid, valid values for the type: "
										+ sourceClassMap.keySet());

					}

					try {
						Field sourceField = sourceClassName
								.getDeclaredField(sourceFieldName);

						sourceField.setAccessible(true);
						MapEntry entry = new MapEntry(sourceField, targetField);
						String fieldMappingKey = MapperUtil
								.constructFieldMappingKey(targetField);
						if (!fieldMappingMap.containsKey(fieldMappingKey)) {
							fieldMappingMap.put(fieldMappingKey, entry);
						}
						if (targetField.getType().isAnnotationPresent(
								Mappable.class)) {
							initFieldMaps(targetField.getType(),
									sourceField.getType());
						} else if (Collection.class
								.isAssignableFrom(targetField.getType())
								|| Map.class.isAssignableFrom(targetField
										.getType())) {
							if ((targetField.getGenericType() == targetField
									.getType())
									&& (sourceField.getGenericType() == sourceField
											.getType())) {
								System.out
										.println("Found non parameterized collections field:"
												+ targetField
												+ " skipping it as not supported yet");
							} else {
								initFieldMaps(
										(ParameterizedType) targetField
												.getGenericType(),
										(ParameterizedType) sourceField
												.getGenericType());

							}

						} else if (targetField.getType().isArray()
								&& sourceField.getType().isArray()) {
							initFieldMaps(targetField.getType()
									.getComponentType(), sourceField.getType()
									.getComponentType());

						}
					} catch (NoSuchFieldException e) {
						logger.error(
								"initFieldMapFromTarget(Class, Map<String,Class>)", e); //$NON-NLS-1$

						throw new UnknownPropertyException("Source Property:"
								+ sourceFieldName
								+ " defined in annotation for field :"
								+ targetField
								+ " is not prsent in source type:"
								+ sourceClassName);
					}
				}
			}

		}

		if (logger.isDebugEnabled()) {
			logger.debug("initFieldMapFromTarget(Class, Map<String,Class>) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Map bean.
	 * 
	 * @param target
	 *            the target
	 * @param source
	 *            the source
	 */
	protected void mapBean(Object target, Object... source) {
		if (logger.isDebugEnabled()) {
			logger.debug("mapBean(Object, Object) - start"); //$NON-NLS-1$
		}

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
				MapEntry entry = fieldMappingMap.get(MapperUtil
						.constructFieldMappingKey(targetField));
				if (entry != null) {
					Field sourceField = entry.getSourceField();
					
					
					Object sourceObject = sourceObjectMap.get(sourceField
							.getDeclaringClass().getCanonicalName());
					
					if(!isSourceFieldSet(sourceField,sourceObject))
					{
						System.out.println("Source Field:"+sourceField.getName()+" not set so skipping it");
						continue;
					}
					// recursively map the enclosed beans too
					if (targetField.getType().isAnnotationPresent(
							Mappable.class)
							|| sourceField.getType().isAnnotationPresent(
									Mappable.class)) {
						Object targetObject = MapperUtil
								.createTargetFieldInstance(targetField);
						mapBean(targetObject, sourceField.get(sourceObject));
						targetField.set(target, targetObject);
					} else if (Collection.class.isAssignableFrom(targetField
							.getType())
							|| Map.class
									.isAssignableFrom(targetField.getType())) {
						Object targetObject = MapperUtil
								.createTargetFieldInstance(targetField);
						mapCollectionBeans(targetObject, sourceObject,
								targetField, sourceField);
						targetField.set(target, targetObject);
						// To be done

					} else if (targetField.getType().isArray()
							&& sourceField.getType().isArray()) {
						Object targetObject = MapperUtil
								.createTargetFieldArrayInstance(targetField,
										Array.getLength(sourceField
												.get(sourceObject)));
						mapArrayBeans(targetObject, sourceObject, targetField,
								sourceField);
						targetField.set(target, targetObject);
					} else {

						targetField.set(target, sourceField.get(sourceObject));
					}
				}
			}
		} catch (IllegalArgumentException e) {
			logger.error("mapBean(Object, Object)", e); //$NON-NLS-1$

			throw new UnableToMapException(
					"Unable to map beans successfully due to an unexpected error: ",
					e);
		} catch (IllegalAccessException e) {
			logger.error("mapBean(Object, Object)", e); //$NON-NLS-1$

			throw new UnableToMapException(
					"Unable to map beans successfully due to an unexpected error: ",
					e);
		} catch (InstantiationException e) {
			logger.error("mapBean(Object, Object)", e); //$NON-NLS-1$

			throw new UnableToMapException(
					"Unable to map beans successfully due to an unexpected error: ",
					e);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("mapBean(Object, Object) - end"); //$NON-NLS-1$
		}
	}

	private boolean isSourceFieldSet(Field sourceField, Object sourceObject) {
		// TODO Auto-generated method stub
		boolean returnValue = false;
		try {
			if (null != sourceField.get(sourceObject)) {
				returnValue = true;
			} else {
				returnValue = false;
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnValue = false;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnValue = false;
		}

		return returnValue;
	}

	/**
	 * Overloaded Methods to initialize field map for parameterized bean classes
	 * like collections and maps etc.
	 * 
	 * @param genericTypeTarget
	 *            the generic type target
	 * @param genericTypeSource
	 *            the generic type source
	 */
	private void initFieldMaps(ParameterizedType genericTypeTarget,
			ParameterizedType genericTypeSource) {
		if (logger.isDebugEnabled()) {
			logger.debug("initFieldMaps(ParameterizedType, ParameterizedType) - start"); //$NON-NLS-1$
		}

		Type[] targetFieldTypes = genericTypeTarget.getActualTypeArguments();
		Type[] sourceFieldTypes = genericTypeSource.getActualTypeArguments();
		if (targetFieldTypes.length != sourceFieldTypes.length) {
			throw new IncompatibleFieldsException("Paramterized target type :"
					+ genericTypeTarget
					+ " is not compatible with source type:"
					+ genericTypeSource);
		}

		for (int typeCount = 0; typeCount < targetFieldTypes.length; typeCount++) {
			initFieldMaps((Class) targetFieldTypes[typeCount],
					(Class) sourceFieldTypes[typeCount]);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("initFieldMaps(ParameterizedType, ParameterizedType) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * This method maps collection fields.
	 * 
	 * @param targetObject
	 *            the target object
	 * @param sourceObject
	 *            the source object
	 * @param targetField
	 *            the target field
	 * @param sourceField
	 *            the source field
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@SuppressWarnings({ "unchecked" })
	private void mapCollectionBeans(Object targetObject, Object sourceObject,
			Field targetField, Field sourceField)
			throws InstantiationException, IllegalAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("mapCollectionBeans(Object, Object, Field, Field) - start"); //$NON-NLS-1$
		}

		// TODO Auto-generated method stub
		ParameterizedType targetFieldType = (ParameterizedType) targetField
				.getGenericType();

		Collection targetCollection = (Collection) targetObject;
		Collection sourceCollection = (Collection) sourceField
				.get(sourceObject);
		Iterator sourceIterator = sourceCollection.iterator();
		while (sourceIterator.hasNext()) {
			Object targetCollectionElementObject = ((Class) targetFieldType
					.getActualTypeArguments()[0]).newInstance();
			mapBean(targetCollectionElementObject, sourceIterator.next());
			targetCollection.add(targetCollectionElementObject);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("mapCollectionBeans(Object, Object, Field, Field) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * @param targetObject
	 * @param sourceObject
	 * @param targetField
	 * @param sourceField
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 */
	private void mapArrayBeans(Object targetObject, Object sourceObject,
			Field targetField, Field sourceField)
			throws IllegalArgumentException, IllegalAccessException,
			InstantiationException {

		Object sourceArray = sourceField.get(sourceObject);
		Object targetArray = targetObject;
		int length = Array.getLength(sourceArray);
		for (int i = 0; i < length; i++) {
			Object sourceArrayElement = Array.get(sourceArray, i);
			if(sourceField.getType().getComponentType().isPrimitive())
			{
				Array.set(targetArray,i,sourceArrayElement);
			}
			else if(sourceField.getType().getComponentType().equals(String.class))
			{
				System.out.println("Found String array element");
				Array.set(targetArray, i, new String((String) sourceArrayElement));
			}
			else if (targetField.getType().getComponentType()
					.isAnnotationPresent(Mappable.class)
					|| sourceField.getType().getComponentType()
							.isAnnotationPresent(Mappable.class)) {
				Object targetArrayElement = MapperUtil
						.createTargetFieldInstance(targetField);
				
				mapBean(targetArrayElement, sourceArrayElement);
				Array.set(targetArray, i, targetArrayElement);
			} else {
				System.out.println("Array Type not supported yet:"
						+ sourceField.getType().getComponentType());
			}
		}

	}

}

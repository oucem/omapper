/*
 * 
 */
package org.omapper.mapper;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.omapper.annotations.Implementation;
import org.omapper.annotations.Mappable;
import org.omapper.annotations.Source;
import org.omapper.exception.IncompatibleFieldsException;
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
						String fieldMappingKey = constructFieldMappingKey(targetField);
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
	 * Check if compatible.
	 * 
	 * @param sourceField
	 *            the source field
	 * @param targetField
	 *            the target field
	 * @throws IncompatibleFieldsException
	 *             the incompatible fields exception
	 */
	private void checkIfCompatible(Field sourceField, Field targetField) throws IncompatibleFieldsException {
		
		if(!(targetField.getType().isAssignableFrom(sourceField.getType())))
		{
			throw new IncompatibleFieldsException("Source Field:"+sourceField.getName()+" Type:"+sourceField.getType()+" is not compatible with target field:"+targetField.getName()+" of Type:"+targetField.getType());
		}
			
		
	}

	/**
	 * Construct field mapping key.
	 * 
	 * @param targetField
	 *            the target field
	 * @return the string
	 */
	private String constructFieldMappingKey(Field targetField) {

		StringBuilder key = new StringBuilder(targetField.getDeclaringClass()
				.getCanonicalName()).append('.').append(targetField.getName());
		return key.toString();
	}

	/**
	 * Check if mappable.
	 * 
	 * @param annotatedElement
	 *            the annotated elements
	 */
	protected void checkIfMappable(AnnotatedElement annotatedElement) {

		if (annotatedElement != null) {
			if (!annotatedElement.isAnnotationPresent(Mappable.class)) {
				throw new NonMappableTargetBeanException(
						"Target Bean Class:"
								+ annotatedElement
								+ " is not mappable.\n Please add @Mappable annotation to the beans which needs to managed by OMapper");
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
						Object targetObject = createTargetFieldInstance(targetField);
						mapBean(targetObject, sourceField.get(sourceObject));
						targetField.set(target, targetObject);
					} else if(Collection.class.isAssignableFrom(targetField.getType()) || Map.class.isAssignableFrom(targetField.getType()))
					{
						Object targetObject=createTargetFieldInstance(targetField);
						mapCollectionBeans(targetObject,sourceObject,targetField,sourceField);
						targetField.set(target, targetObject);
						//To be done
						
					}
					else
					{

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
	
	
	/**
	 * Creates the target field instance.
	 * 
	 * @param targetField
	 *            the target field
	 * @return the object
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	private Object createTargetFieldInstance(Field targetField) throws InstantiationException, IllegalAccessException
	{
		Object targetObject=null;
		if(targetField.getType().isInterface() || Modifier.isAbstract(targetField.getType().getModifiers()))
		{
			if(targetField.isAnnotationPresent(Implementation.class))
			{
				Implementation interfaceAnnot=targetField.getAnnotation(Implementation.class);
				Class interfaceImpl=interfaceAnnot.name();
				targetObject=interfaceImpl.newInstance();
			}
			else
			{
				throw new UnknownTypeException("Type of target field could not be determined, use @interface annotaion to specify implementation type for interface types");
			}
			
		}
		else
		{
			targetObject= targetField.getType().newInstance();
		}
		
		return targetObject;
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
	private void initFieldMaps(ParameterizedType genericTypeTarget, ParameterizedType genericTypeSource) {
		
		Type[] targetFieldTypes=genericTypeTarget.getActualTypeArguments();
		Type[] sourceFieldTypes=genericTypeSource.getActualTypeArguments();
		if(targetFieldTypes.length!=sourceFieldTypes.length)
		{
			throw new IncompatibleFieldsException("Paramterized target type :"+genericTypeTarget+ " is not compatible with source type:"+genericTypeSource);
		}
		
		for(int typeCount=0;typeCount<targetFieldTypes.length;typeCount++)
		{
			initFieldMaps((Class)targetFieldTypes[typeCount], (Class)sourceFieldTypes[typeCount]);
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void mapCollectionBeans(Object targetObject, Object sourceObject,
			Field targetField, Field sourceField)
			throws InstantiationException, IllegalAccessException {
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
		

	}
	

}

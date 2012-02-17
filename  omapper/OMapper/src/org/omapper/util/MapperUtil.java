/**
 * 
 */
package org.omapper.util;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.omapper.annotations.Implementation;
import org.omapper.annotations.Mappable;
import org.omapper.enums.FieldType;
import org.omapper.exception.IncompatibleFieldsException;
import org.omapper.exception.NonMappableTargetBeanException;
import org.omapper.exception.UnknownTypeException;

/**
 * The Class MapperUtil.
 *
 * @author Sachin
 */
public class MapperUtil {

	/**
	 * Gets the field map for all the fields in the passed classes array.
	 *
	 * @param targetClasses the target classes
	 * @return the field map
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Field> getFieldMap(Class... targetClasses) {
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (Class targetClass : targetClasses) {

			Field[] fieldArray = targetClass.getDeclaredFields();

			for (Field field : fieldArray) {
				
				fieldMap.put(constructFieldMappingKey(field), field);
			}
		}
		return fieldMap;
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
	public static void checkIfCompatible(Field sourceField, Field targetField)
			throws IncompatibleFieldsException {

		if (!(targetField.getType().isAssignableFrom(sourceField.getType()))) {
			throw new IncompatibleFieldsException("Source Field:"
					+ sourceField.getName() + " Type:" + sourceField.getType()
					+ " is not compatible with target field:"
					+ targetField.getName() + " of Type:"
					+ targetField.getType());
		}

	}
	
	
	/**
	 * Construct field mapping key.
	 * 
	 * @param targetField
	 *            the target field
	 * @return the string
	 */
	public static String constructFieldMappingKey(Field targetField) {

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
	public static void checkIfMappable(AnnotatedElement annotatedElement) {

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
	 * Parses the field and returns the field type
	 * @param field
	 * @return
	 */
	public static FieldType getFieldType(Field field)
 {
		Class<?> fieldType = field.getType();
		FieldType fieldTypeEnum = null;
		if (Collection.class.isAssignableFrom(fieldType)) {
			fieldTypeEnum = FieldType.COLLECTION;
		} else if (Map.class.isAssignableFrom(fieldType)) {
			fieldTypeEnum = FieldType.MAP;
		} else if (field.getType().isEnum()) {
			fieldTypeEnum = FieldType.ENUM;
		} else if (field.getType().isArray()) {
			fieldTypeEnum = FieldType.ARRAY;
		} else if (field.getType().isAnnotationPresent(Mappable.class)) {
			fieldTypeEnum = FieldType.USER;
		} else if (fieldType.isInterface()
				|| Modifier.isAbstract(fieldType.getModifiers())) {
			fieldTypeEnum = FieldType.TEMPLATE;
		}

		else {
			fieldTypeEnum = FieldType.JAVA;
		}
		return fieldTypeEnum;

	}
	
	/**
	 * Checks if the passed field is parameterized or not
	 * @param field
	 * @return
	 */
	public static boolean isParameterized(Field field)
	{
		return (!(field.getType()==field.getGenericType()));
	}
	/**
	 * Returns the class of the parameterized field like for List<String> , this method would return String class object
	 * @param field
	 * @return
	 */
	public static Class<?> getParamterizedType(Field field)
	{
		return null;
	}
	

	
	public static Object createTargetFieldArrayInstance(Field targetField, int length) {

		Class<?> componentType = targetField.getType().getComponentType();
		Object targetObject = null;
		if (componentType.isInterface()
				|| Modifier.isAbstract(componentType.getModifiers())) {
			if (targetField.isAnnotationPresent(Implementation.class)) {
				Implementation interfaceAnnot = targetField
						.getAnnotation(Implementation.class);
				Class interfaceImpl = interfaceAnnot.name();
				targetObject = Array.newInstance(interfaceImpl, length);
			} else {
				throw new UnknownTypeException(
						"Type of target field could not be determined, use @interface annotaion to specify implementation type for interface types");
			}

		} else {
			targetObject = Array.newInstance(componentType, length);
		}

		return targetObject;

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
	public static Object createTargetFieldInstance(Field targetField)
			throws InstantiationException, IllegalAccessException {
		Object targetObject = null;
		if (targetField.getType().isInterface()
				|| Modifier.isAbstract(targetField.getType().getModifiers())) {
			if (targetField.isAnnotationPresent(Implementation.class)) {
				Implementation interfaceAnnot = targetField
						.getAnnotation(Implementation.class);
				Class interfaceImpl = interfaceAnnot.name();
				targetObject = interfaceImpl.newInstance();
			} else {
				throw new UnknownTypeException(
						"Type of target field could not be determined, use @interface annotaion to specify implementation type for interface types");
			}

		} else {
			targetObject = targetField.getType().newInstance();
		}

		return targetObject;
	}

	
}

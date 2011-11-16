/**
 * 
 */
package org.omapper.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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

			String className = targetClass.getCanonicalName();
			Field[] targetFields = targetClass.getDeclaredFields();

			for (Field target : targetFields) {
				String fieldName = target.getName();

				fieldMap.put(className + "." + fieldName, target);
			}
		}
		return fieldMap;
	}

}

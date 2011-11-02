package org.omapper.mapper;

import java.lang.reflect.Field;
import java.util.Map;

import org.omapper.util.MapperUtil;

/**
 * @author Sachin
 * 
 */
public abstract class AbstractMapper<T, S> {

	Map<String, Field> sourceFieldsMap;
	Map<String, Field> targetFieldsMap;

	protected void initFieldMaps(Class<T> targetClass, Class<S>... sourceClass) {

		targetFieldsMap = MapperUtil.getFieldMap(targetClass);

		sourceFieldsMap = MapperUtil.getFieldMap(sourceClass);
		System.out.println(sourceFieldsMap);
		System.out.println(targetFieldsMap);

	}

	public AbstractMapper(Class<T> targetClass, Class<S>... sourceClass) {

		initFieldMaps(targetClass, sourceClass);

	}
}

/**
 * 
 */
package org.omapper.mapper;


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
		
	}

	
	
	
}

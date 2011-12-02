/**
 * 
 */
package org.omapper.mapper;


// TODO: Auto-generated Javadoc
/**
 * This mapper is used to collate data from mutiple beans to one bean
 * 
 * @param <T>
 *            the generic type
 * @author Sachin
 */

public class CollatingMapper<T> extends AbstractMapper {

	/**
	 * Instantiates a new collating mapper.
	 * 
	 * @param targetClass
	 *            the target class
	 * @param sourceClasses
	 *            the source classes
	 */
	public CollatingMapper(Class<T> targetClass,
			Class<? extends Object>... sourceClasses) {

		super(targetClass, sourceClasses);
	}

	public void mapBean(Object target, Object... source) {
	
		super.mapBean(target, source);
		
	}
	

}

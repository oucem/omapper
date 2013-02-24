/**
 * 
 */
package org.omapper.enums;

import org.apache.log4j.Logger;

/**
 * @author Sachin
 *
 */
public enum FieldType {
	
	JAVA,
	USER,
	ENUM,
	ARRAY,
	COLLECTION,
	MAP,
	/*This field type indictaes that teh field type is an interfcae or abstract which can not be instantiated and needs an */
	TEMPLATE

}

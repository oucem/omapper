/*
 * 
 */
package org.omapper.test.collater;

import org.apache.log4j.Logger;
import org.omapper.annotations.Source;

/**
 * The Class Child2.
 */

public class Child2 {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Child2.class);

	/** The name. */
	@Source(type=Child1.class, property="name")
	private String name;
	
	/** The age. */
	@Source(type=Child1.class, property="age")
	private int age;
	
	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the age.
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Sets the age.
	 * 
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Child2 [name=");
		builder.append(name);
		builder.append(", age=");
		builder.append(age);
		builder.append("]");
		return builder.toString();
	}
}

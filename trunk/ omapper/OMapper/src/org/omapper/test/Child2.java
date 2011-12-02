package org.omapper.test;

import org.omapper.annotations.Mappable;
import org.omapper.annotations.Source;

@Mappable
public class Child2 {
	
	@Source(type=Child1.class,property="childName")
	private String  childName;
	@Source(type=Child1.class,property="childAge")
	private int childAge;
	
	public Child2(){};
	public Child2(String childName, int childAge) {
		super();
		this.childName = childName;
		this.childAge = childAge;
	}

	/**
	 * @return the childName
	 */
	public String getChildName() {
		return childName;
	}

	/**
	 * @return the childAge
	 */
	public int getChildAge() {
		return childAge;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Child2 [childName=");
		builder.append(childName);
		builder.append(", childAge=");
		builder.append(childAge);
		builder.append("]");
		return builder.toString();
	}
}

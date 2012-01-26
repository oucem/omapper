package org.omapper.test;

public class Child1 {

	private String childName;
	private int childAge;

	public Child1() {

	}

	public Child1(String childName, int childAge) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Child1 [childName=");
		builder.append(childName);
		builder.append(", childAge=");
		builder.append(childAge);
		builder.append("]");
		return builder.toString();
	}

}

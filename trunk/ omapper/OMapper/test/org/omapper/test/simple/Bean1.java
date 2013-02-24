/*
 * 
 */
package org.omapper.test.simple;

import java.util.List;

/**
 * The Class Bean1.
 */
public class Bean1 {

	/** The i. */
	private int i;
	
	/** The x. */
	private String x;
	
	/** The child. */
	private Child1 child;
	
	/** The child list. */
	private List<Child1> childList;
	
	private Child1[] childArray;

	/**
	 * Gets the i.
	 * 
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * Sets the i.
	 * 
	 * @param i
	 *            the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * Gets the x.
	 * 
	 * @return the x
	 */
	public String getX() {
		return x;
	}

	/**
	 * Sets the x.
	 * 
	 * @param x
	 *            the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * Gets the child.
	 * 
	 * @return the child
	 */
	public Child1 getChild() {
		return child;
	}

	/**
	 * Sets the child.
	 * 
	 * @param child
	 *            the child to set
	 */
	public void setChild(Child1 child) {
		this.child = child;
	}

	/**
	 * Gets the child list.
	 * 
	 * @return the childList
	 */
	public List<Child1> getChildList() {
		return childList;
	}

	/**
	 * Sets the child list.
	 * 
	 * @param childList
	 *            the childList to set
	 */
	public void setChildList(List<Child1> childList) {
		this.childList = childList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bean1 [i=");
		builder.append(i);
		builder.append(", x=");
		builder.append(x);
		builder.append(", child=");
		builder.append(child);
		builder.append(", childList=");
		builder.append(childList);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the childArray
	 */
	public Child1[] getChildArray() {
		return childArray;
	}

	/**
	 * @param childArray the childArray to set
	 */
	public void setChildArray(Child1[] childArray) {
		this.childArray = childArray;
	}

}

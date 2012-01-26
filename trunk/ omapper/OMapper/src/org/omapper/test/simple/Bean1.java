package org.omapper.test.simple;

import java.util.List;

public class Bean1 {

	private int i;
	private String x;
	private Child1 child;
	private List<Child1> childList;

	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @param i
	 *            the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * @return the x
	 */
	public String getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}

	/**
	 * @return the child
	 */
	public Child1 getChild() {
		return child;
	}

	/**
	 * @param child
	 *            the child to set
	 */
	public void setChild(Child1 child) {
		this.child = child;
	}

	/**
	 * @return the childList
	 */
	public List<Child1> getChildList() {
		return childList;
	}

	/**
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

}

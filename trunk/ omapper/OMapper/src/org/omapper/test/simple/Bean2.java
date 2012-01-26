package org.omapper.test.simple;

import java.util.ArrayList;
import java.util.List;

import org.omapper.annotations.Implementation;
import org.omapper.annotations.Mappable;
import org.omapper.annotations.Source;

@Mappable
public class Bean2 {
	
	@Source(type=Bean1.class,property="i")
	private int i;
	@Source(type=Bean1.class,property="x")
	private String x;
	@Source(type=Bean1.class,property="child")
	private Child2 child;
	@Source(type=Bean1.class,property="childList")
	@Implementation(name=ArrayList.class)
	private List<Child2> childList;
	
	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}
	/**
	 * @param i the i to set
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
	 * @param x the x to set
	 */
	public void setX(String x) {
		this.x = x;
	}
	/**
	 * @return the child1
	 */
	public Child2 getChild() {
		return child;
	}
	/**
	 * @param child1 the child1 to set
	 */
	public void setChild(Child2 child) {
		this.child = child;
	}
	/**
	 * @return the child1List
	 */
	public List<Child2> getChildList() {
		return childList;
	}
	/**
	 * @param child1List the child1List to set
	 */
	public void setChildList(List<Child2> childList) {
		this.childList = childList;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bean2 [i=");
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

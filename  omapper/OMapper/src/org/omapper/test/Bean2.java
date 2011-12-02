package org.omapper.test;

import java.util.List;

import org.omapper.annotations.Mappable;
import org.omapper.annotations.Source;


/**
 * The Class Bean2.
 */
@Mappable
public class Bean2 {

	/** The name. */
	@Source(type = org.omapper.test.Bean1.class, property = "name")
	private String name;
	
	/** The address. */
	@Source(type = Bean1.class, property = "address")
	private String address;
	
	/** The age. */
	@Source(type = Bean1.class, property = "age")
	private int age;
	
	/** The emp_id. */
	@Source(type = Bean1.class, property = "emp_id")
	private Integer emp_id;

	/** The positions list. */
	@Source(type = Bean3.class, property = "positionsList")
	private List<String> positionsList;

	/** The company name. */
	@Source(type = Bean3.class, property = "companyName")
	private String companyName;
	
	@Source(type=Bean1.class, property="child1")
	private Child2 child2;

	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * Gets the positions list.
	 *
	 * @return the positions list
	 */
	public List<String> getPositionsList() {
		return positionsList;
	}

	/**
	 * Sets the positions list.
	 *
	 * @param positionsList the new positions list
	 */
	public void setPositionsList(List<String> positionsList) {
		this.positionsList = positionsList;
	}

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
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @param age the new age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets the emp_id.
	 *
	 * @return the emp_id
	 */
	public Integer getEmp_id() {
		return emp_id;
	}

	/**
	 * Sets the emp_id.
	 *
	 * @param emp_id the new emp_id
	 */
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bean2 [name=");
		builder.append(name);
		builder.append(", address=");
		builder.append(address);
		builder.append(", age=");
		builder.append(age);
		builder.append(", emp_id=");
		builder.append(emp_id);
		builder.append(", positionsList=");
		builder.append(positionsList);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", child2=");
		builder.append(child2);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * @return the child2
	 */
	public Child2 getChild2() {
		return child2;
	}

}

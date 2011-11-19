package org.omapper.test;

// TODO: Auto-generated Javadoc
//Source Bean
/**
 * The Class Bean1.
 */
public class Bean1 {

	/** The name. */
	private String name;
	/** The address. */
	private String address;
	
	/** The age. */
	private int age;
	
	/** The emp_id. */
	private Integer emp_id;
	
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
		builder.append("Bean1 [name=").append(name).append(", address=")
				.append(address).append(", age=").append(age)
				.append(", emp_id=").append(emp_id).append("]");
		return builder.toString();
	}
	
}

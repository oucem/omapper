package org.omapper.test;

//Source Bean
public class Bean1 {

	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	private String address;
	private int age;
	private Integer emp_id;
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bean1 [name=").append(name).append(", address=")
				.append(address).append(", age=").append(age)
				.append(", emp_id=").append(emp_id).append("]");
		return builder.toString();
	}
	
}

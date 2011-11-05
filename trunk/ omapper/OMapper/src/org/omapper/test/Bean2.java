package org.omapper.test;

import java.util.List;

import org.omapper.annotations.Mappable;
import org.omapper.annotations.Source;

@Mappable
public class Bean2 {

	@Source(type = org.omapper.test.Bean1.class, property = "name")
	private String name;
	@Source(type = Bean1.class, property = "address")
	private String address;
	@Source(type = Bean1.class, property = "age")
	private int age;
	@Source(type = Bean1.class, property = "emp_id")
	private Integer emp_id;

	@Source(type = Bean3.class, property = "positionsList")
	private List<String> positionsList;

	@Source(type = Bean3.class, property = "companyName")
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<String> getPositionsList() {
		return positionsList;
	}

	public void setPositionsList(List<String> positionsList) {
		this.positionsList = positionsList;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bean2 [name=").append(name).append(", address=")
				.append(address).append(", age=").append(age)
				.append(", emp_id=").append(emp_id).append(", positionsList=")
				.append(positionsList).append(", companyName=")
				.append(companyName).append("]");
		return builder.toString();
	}

}

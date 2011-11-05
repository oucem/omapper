package org.omapper.test;

import java.util.List;

/**
 * @author Sachin
 *
 */
public class Bean3 {
	
	private String companyName;
	
	private List<String> positionsList;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bean3 [companyName=").append(companyName)
				.append(", positionsList=").append(positionsList).append("]");
		return builder.toString();
	}

}

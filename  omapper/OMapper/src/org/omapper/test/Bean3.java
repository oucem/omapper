package org.omapper.test;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Bean3.
 *
 * @author Sachin
 */
public class Bean3 {
	
	/** The company name. */
	private String companyName;
	
	/** The positions list. */
	private List<String> positionsList;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bean3 [companyName=").append(companyName)
				.append(", positionsList=").append(positionsList).append("]");
		return builder.toString();
	}

}

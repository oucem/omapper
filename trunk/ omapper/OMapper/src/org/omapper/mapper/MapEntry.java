package org.omapper.mapper;

import java.lang.reflect.Field;

public class MapEntry {

	private final Field sourceField;
	private final Field targetField;

	public MapEntry(Field sourceField, Field targetField) {
		this.sourceField = sourceField;
		this.targetField = targetField;
	}

	public Field getSourceField() {
		return sourceField;
	}

	public Field getTargetField() {
		return targetField;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MapEntry [sourceField=");
		builder.append(sourceField);
		builder.append(", targetField=");
		builder.append(targetField);
		builder.append("]");
		return builder.toString();
	}

}

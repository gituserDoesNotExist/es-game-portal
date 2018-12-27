package org.commons;

public enum ValidPoint {

	VALID("Valid"), NOT_VALID("Not valid"), DEFAULT("Filled Field");

	private String text;

	ValidPoint(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

}

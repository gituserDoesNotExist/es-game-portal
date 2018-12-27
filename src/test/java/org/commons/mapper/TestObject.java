package org.commons.mapper;

import java.time.LocalDateTime;

public class TestObject {

	private long id;
	private String description;
	private LocalDateTime lastModified;

	public TestObject() {
	}

	public TestObject(long id, String description, LocalDateTime lastModified) {
		this.id = id;
		this.description = description;
		this.lastModified = lastModified;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}

package org.commons;

import java.time.LocalDateTime;

public class VersionedDto extends BaseDto {

	private final LocalDateTime lastModified;

	public VersionedDto() {
		this(null);
	}

	public VersionedDto(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

}

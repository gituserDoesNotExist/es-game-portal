package org.commons;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;

@MappedSuperclass
public class VersionedEntity extends BaseEntity {

	@Version
	@Column(name = "LAST_MODIFIED")
	private LocalDateTime lastModified;

	@CreationTimestamp
	@Column(name = "CREATED_AT", updatable = false)
	private LocalDateTime createdAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

}

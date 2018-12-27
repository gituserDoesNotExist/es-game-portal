package org.commons;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(generator = "custom", strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (isNotPersistet())
			return false;
		return getClass().isInstance(other) && getId() == ((BaseEntity) other).getId();
	}

	private boolean isNotPersistet() {
		return getId() == 0;
	}

}

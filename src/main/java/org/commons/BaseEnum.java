package org.commons;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEnum {

	@Id
	protected long id;

	public BaseEnum() {
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		return getClass().isInstance(other) && getId() == ((BaseEnum) other).getId();
	}

	public long getId() {
		return id;
	}
}

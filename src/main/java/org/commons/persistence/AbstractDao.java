package org.commons.persistence;

import javax.persistence.EntityManager;

public class AbstractDao {

	protected EntityManager entityManager;

	public void setEntitManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}

package org.commons.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerCreator {

	private static EntityManager entityManager = null;

	private EntityManagerCreator() {

	}

	public static EntityManager uniqueEntityManager() {
		if (entityManager == null) {
			entityManager = Persistence.createEntityManagerFactory("testUnit").createEntityManager();
		}
		return entityManager;
	}
}

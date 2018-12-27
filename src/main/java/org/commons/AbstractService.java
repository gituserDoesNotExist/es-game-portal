package org.commons;

import javax.persistence.EntityManager;

import org.commons.persistence.EntityManagerCreator;

public class AbstractService {

	protected EntityManager entityManager;

	public AbstractService() {
		this.entityManager = EntityManagerCreator.uniqueEntityManager();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected void beginTransaction() {
		entityManager.getTransaction().begin();
	}

	protected void commitTransaction() {
		entityManager.getTransaction().commit();
	}

	protected void flush() {
		entityManager.flush();
	}
}

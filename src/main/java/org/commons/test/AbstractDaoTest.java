package org.commons.test;

import javax.persistence.EntityManager;

import org.commons.persistence.EntityManagerCreator;
import org.junit.After;
import org.junit.Before;

public class AbstractDaoTest extends AbstractTest {

	protected EntityManager entityManager = EntityManagerCreator.uniqueEntityManager();

	@Before
	public void setUp() {
		entityManager.getTransaction().begin();
	}

	@After
	public void tearDown() {
		entityManager.getTransaction().rollback();
	}

	public void flush() {
		entityManager.flush();
	}

}

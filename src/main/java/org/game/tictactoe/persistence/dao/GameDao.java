package org.game.tictactoe.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.commons.persistence.AbstractDao;
import org.game.tictactoe.pitch.Game;

public class GameDao extends AbstractDao {

	public GameDao() {

	}

	public GameDao(EntityManager entityManager) {
		setEntitManager(entityManager);
	}

	public Game saveTransientEntity(Game field) {
		entityManager.persist(field);
		return field;
	}

	public Game updateDetachedEntity(Game field) {
		return entityManager.merge(field);
		// merge gibt das Objekt zurück das in den PersistenceContext aufgenommen wird. Dies entspricht nicht dem übergebenen Objekt
	}

	public Game findById(long id) {
		Query query = entityManager.createQuery("SELECT game FROM Game AS game WHERE game.id = :parameter");
		query.setParameter("parameter", id);
		return (Game) query.getSingleResult();
	}

}

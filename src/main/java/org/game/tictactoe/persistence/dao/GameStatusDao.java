package org.game.tictactoe.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.commons.persistence.AbstractDao;
import org.game.tictactoe.pitch.GameStatus;

public class GameStatusDao extends AbstractDao {

	public GameStatusDao(EntityManager entityManager) {
		setEntitManager(entityManager);
	}

	public GameStatus findById(long id) {
		Query query = entityManager.createQuery("SELECT status FROM GameStatus AS status WHERE status.id= :parameter");
		query.setParameter("parameter", id);
		return (GameStatus) query.getSingleResult();
	}

}

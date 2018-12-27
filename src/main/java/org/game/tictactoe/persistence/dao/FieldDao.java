package org.game.tictactoe.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.commons.persistence.AbstractDao;
import org.game.tictactoe.field.Field;

public class FieldDao extends AbstractDao {

	public FieldDao(EntityManager entityManager) {
		setEntitManager(entityManager);
	}

	public Field saveTransientEntity(Field field) {
		entityManager.persist(field);
		return field;
	}

	public Field updateDetachedEntity(Field field) {
		return entityManager.merge(field);
	}

	@SuppressWarnings("unchecked")
	public List<Field> findAll() {
		Query query = entityManager.createQuery("SELECT field FROM Field AS field");
		return (List<Field>) query.getResultList();
	}

	public Field findById(long id) {
		Query query = entityManager.createQuery("SELECT field FROM Field AS field WHERE field.id = :parameter");
		query.setParameter("parameter", id);
		return (Field) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Field> findByGameFieldId(long gameFieldId) {
		Query query = entityManager.createQuery("SELECT field FROM Field AS field WHERE field.gameId = :parameter");
		query.setParameter("parameter", gameFieldId);
		return (List<Field>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Field> findTicTacToeFieldWithGameDescription(String description) {
		Query query = entityManager.createQuery(
				"SELECT field FROM Field AS field JOIN Game AS game ON field.gameId = game.id WHERE game.description =:parameter");
		// das mit JOIN geht erst seit Hibernate 5.1
		// zunächst wählt man mit ON tttField.gameFieldId = gameField.id alle Einträge aus bei denen PK und FK gleich sind
		// danach schränkt man das Suchergebnis mithilfe des WHERE Befehls ein
		query.setParameter("parameter", description);
		return (List<Field>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Field> findTicTacToeFieldWithGameFieldDescriptionOld(String description) {
		// vor Hibernate 5.1 konnte man kein JOIN verwenden
		Query query = entityManager.createQuery(
				"SELECT field FROM Field AS field, Game AS game WHERE field.gamedId = game.id AND game.description =:parameter");
		query.setParameter("parameter", description);
		return (List<Field>) query.getResultList();
	}

	public void delete(long id) {
		Query query = entityManager.createQuery("DELETE FROM Field AS field WHERE field.id =:parameter");
		query.setParameter("parameter", id);
		query.executeUpdate();

	}
}

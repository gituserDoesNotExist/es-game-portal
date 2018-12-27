package org.game.tictactoe.persistence;

import java.util.List;
import java.util.stream.IntStream;

import org.commons.AbstractService;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.minimax.TicTacToeConstants;
import org.game.tictactoe.persistence.dao.FieldDao;
import org.game.tictactoe.persistence.dao.GameDao;
import org.game.tictactoe.pitch.Game;
import org.game.tictactoe.pitch.GameFieldCreator;

public class TicTacToeService extends AbstractService {

	private GameDao gameDao = new GameDao(entityManager);
	private FieldDao tictactoeFieldDao = new FieldDao(entityManager);
	private GameFieldCreator creator = new GameFieldCreator();

	public Field findById(long id) {
		return tictactoeFieldDao.findById(id);
	}

	public List<Field> findByGameId(long id) {
		return tictactoeFieldDao.findByGameFieldId(id);
	}

	public Game createGame() {
		beginTransaction();
		Game saved = gameDao.saveTransientEntity(creator.emptyGameWithoutFields());
		commitTransaction();
		return saved;
	}

	public List<Field> createFieldsForGame(long gameId) {
		beginTransaction();
		IntStream.range(1, TicTacToeConstants.NUMBER_OF_FIELDS + 1).boxed()
				.forEach(fieldId -> tictactoeFieldDao.saveTransientEntity(tttField(gameId, fieldId)));
		List<Field> result = tictactoeFieldDao.findByGameFieldId(gameId);
		commitTransaction();
		return result;
	}

	public Field updateField(Field field) {
		Field fromDb = findAndDetachField(field.getId());
		beginTransaction();
		fromDb.checkConsistencyForUpdate(field);
		Field updated = tictactoeFieldDao.updateDetachedEntity(field);
		commitTransaction();
		return updated;
	}

	public Game updateGame(Game game) {
		Game fromDb = findAndDetachGame(game.getId());
		beginTransaction();
		fromDb.checkConsistencyForUpdate(game);
		Game updated = gameDao.updateDetachedEntity(game);
		commitTransaction();
		return updated;
	}

	private Field tttField(long gameId, long fieldId) {
		Field tttField = new Field();
		tttField.setGameId(gameId);
		tttField.setFieldId(fieldId);
		tttField.setValue(TicTacToeConstants.REPRESENTATION_EMPTY_FIELD);
		return tttField;
	}

	private Field findAndDetachField(long id) {
		Field tttField = tictactoeFieldDao.findById(id);
		entityManager.detach(tttField);
		return tttField;
	}

	private Game findAndDetachGame(long id) {
		Game tttField = gameDao.findById(id);
		entityManager.detach(tttField);
		return tttField;
	}

}

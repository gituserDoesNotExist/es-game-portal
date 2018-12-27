package org.game.tictactoe;

import static org.game.tictactoe.minimax.TicTacToeConstants.EMPTY_FIELD;

import java.util.List;
import java.util.Optional;

import org.commons.AbstractService;
import org.commons.Validations;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.field.FieldStatus;
import org.game.tictactoe.field.FieldStatusMapper;
import org.game.tictactoe.field.FieldsTo2DArrayMapper;
import org.game.tictactoe.minimax.MinimaxService;
import org.game.tictactoe.minimax.Rules;
import org.game.tictactoe.minimax.TicTacToeConstants;
import org.game.tictactoe.persistence.TicTacToeService;
import org.game.tictactoe.persistence.dao.GameStatusDao;

public class GameWrapper extends AbstractService {

	private static final long USER_WON = 1L;
	private static final long DRAW_LAST_MOVE_BY_USER = 3;
	private static final long AI_WON = 2;
	private static final long DRAW_LAST_MOVE_BY_AI = 4;
	private static final long IN_PROGRESS = 5;
	private static final String ERROR_INVALID_MOVE = "This was an invalid move";
	private MinimaxService minimaxService = new MinimaxService();
	private Rules rules = new Rules();
	private FieldsTo2DArrayMapper mapper = new FieldsTo2DArrayMapper();
	private FieldStatusMapper fieldStatusMapper = new FieldStatusMapper();
	private GameStatusDao gameStatusDao = new GameStatusDao(entityManager);
	private TicTacToeService tictactoeService = new TicTacToeService();

	public boolean checkIfInvalidMove(Field fieldSetByUser) {
		List<Field> fields = tictactoeService.findByGameId(fieldSetByUser.getGameId());
		return rules.fieldsAlreadyFilled(EMPTY_FIELD, mapper.as2DArray(fields)) || fieldForGivenIdNotEmpty(fields, fieldSetByUser.getFieldId())
				|| hasAiWon(fields);
	}

	public FieldStatus nextMoveOfAi(long gameId) {
		List<Field> fields = tictactoeService.findByGameId(gameId);
		int[][] fieldArray = mapper.as2DArray(fields);

		Field nextMove = minimaxService.computeNextMove(fields);
		// TODO: 2018 Manfred - fieldId als int!
		fields.set(((int) nextMove.getFieldId()) - 1, nextMove);
		fieldArray = mapper.as2DArray(fields);

		if (rules.hasPlayerWon(TicTacToeConstants.MAXIMIZING_PLAYER, fieldArray)) {
			return fieldStatusMapper.createFieldStatus(nextMove, gameStatusDao.findById(AI_WON));
		}
		if (isGameOver(fieldArray)) {
			return fieldStatusMapper.createFieldStatus(nextMove, gameStatusDao.findById(DRAW_LAST_MOVE_BY_AI));
		}
		return fieldStatusMapper.createFieldStatus(nextMove, gameStatusDao.findById(IN_PROGRESS));
	}

	public FieldStatus analyzeMoveFromUser(Field fieldSetByUser) {
		fieldSetByUser.setValue(TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER);
		Validations.checkNot(this.checkIfInvalidMove(fieldSetByUser), ERROR_INVALID_MOVE);

		long gameId = fieldSetByUser.getGameId();
		List<Field> fields = tictactoeService.findByGameId(gameId);
		fields.set(((int) fieldSetByUser.getFieldId()) - 1, fieldSetByUser);
		int[][] array = mapper.as2DArray(fields);

		if (rules.hasPlayerWon(TicTacToeConstants.MINIMIZING_PLAYER, array)) {
			return fieldStatusMapper.createFieldStatus(fieldSetByUser, gameStatusDao.findById(USER_WON));
		}
		if (isGameOver(array)) {
			return fieldStatusMapper.createFieldStatus(fieldSetByUser, gameStatusDao.findById(DRAW_LAST_MOVE_BY_USER));
		}
		return fieldStatusMapper.createFieldStatus(fieldSetByUser, gameStatusDao.findById(IN_PROGRESS));
	}

	public FieldStatus gameInProgress(Field field) {
		return fieldStatusMapper.createFieldStatus(field, gameStatusDao.findById(IN_PROGRESS));
	}

	private boolean isGameOver(int[][] array) {
		return rules.isGameOver(TicTacToeConstants.MINIMIZING_PLAYER, TicTacToeConstants.MAXIMIZING_PLAYER, TicTacToeConstants.EMPTY_FIELD, array);
	}

	private boolean hasAiWon(List<Field> fields) {
		return rules.hasPlayerWon(TicTacToeConstants.MAXIMIZING_PLAYER, mapper.as2DArray(fields));
	}

	private boolean fieldForGivenIdNotEmpty(List<Field> fields, long otherFieldId) {
		Optional<Field> findFirst = fields.stream().filter(field -> field.getFieldId() == otherFieldId).findAny();
		return findFirst.map(field -> !fieldIsEmpty(field)).orElse(true);
	}

	private boolean fieldIsEmpty(Field field) {
		return field.getValue().equals(TicTacToeConstants.REPRESENTATION_EMPTY_FIELD);
	}

}

package org.game.tictactoe.minimax;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.minimax.MinimaxService;
import org.game.tictactoe.minimax.TicTacToeConstants;
import org.junit.Test;

public class MinimaxServiceTest {

	private static final String AI = TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER;
	private static final String USER = TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER;
	private static final String EMPTY = TicTacToeConstants.REPRESENTATION_EMPTY_FIELD;
	private MinimaxService service = new MinimaxService();

	@Test
	public void nextMove() {
		Field nextMove = service.computeNextMove(tttFields());

		Assertions.assertThat(nextMove.getFieldId()).isEqualTo(2);
		Assertions.assertThat(nextMove.getValue()).isEqualTo(AI);

	}

	private List<Field> tttFields() {
		return Arrays.asList(tttField(EMPTY, 1), tttField(EMPTY, 2), tttField(EMPTY, 3), tttField(EMPTY, 4), tttField(USER, 5),
				tttField(EMPTY, 6), tttField(EMPTY, 7), tttField(USER, 8), tttField(AI, 9));
	}

	private Field tttField(String value, int fieldId) {
		Field field = new Field();
		field.setId(1);
		field.setFieldId(fieldId);
		field.setValue(value);
		return field;
	}

}

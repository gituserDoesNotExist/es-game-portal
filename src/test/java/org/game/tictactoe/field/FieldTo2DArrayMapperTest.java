package org.game.tictactoe.field;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.commons.GamePortalExceptions;
import org.commons.MappingNotPossibleException;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.field.FieldsTo2DArrayMapper;
import org.game.tictactoe.minimax.TicTacToeConstants;
import org.junit.Test;

public class FieldTo2DArrayMapperTest {

	FieldsTo2DArrayMapper mapper = new FieldsTo2DArrayMapper();

	private static final String AI = TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER;
	private static final int AI_MINIMAX = TicTacToeConstants.MAXIMIZING_PLAYER;
	private static final String USER = TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER;
	private static final int USER_MINIMAX = TicTacToeConstants.MINIMIZING_PLAYER;
	private static final String EMPTY = TicTacToeConstants.REPRESENTATION_EMPTY_FIELD;
	private static final int EMPTY_MINIMAX = TicTacToeConstants.EMPTY_FIELD;

	@Test
	public void as2DArray_EmptyList_ReturnsEmptyField() {
		List<Field> list = new ArrayList<>();

		Assertions.assertThat(mapper.as2DArray(list)).isEmpty();
	}

	@Test
	public void as2DArray_InvalidList_ThrowsException() {
		List<Field> list = Arrays.asList(tttField("any", 1));

		Assertions.assertThatThrownBy(() -> mapper.as2DArray(list)).isInstanceOf(GamePortalExceptions.class)
				.hasMessageContaining("Square root of");
	}

	@Test
	public void as2DArray_ValidList_EdgeLengthCalculatedCorrectly() {
		List<Field> list = new ArrayList<>();
		IntStream.range(1, 5).boxed().forEach(integer -> list.add(tttField(AI, 1)));

		Assertions.assertThat(mapper.as2DArray(list).length).isEqualTo(2);

	}

	@Test
	public void as2DArray_ValidList_MappedCorrectly() {
		List<Field> list = Arrays.asList(tttField(AI, 1), tttField(USER, 2), tttField(AI, 3), tttField(USER, 4), tttField(AI, 5),
				tttField(USER, 6), tttField(AI, 7), tttField(USER, 8), tttField(AI, 9));

		int[][] expectation = new int[][] { { AI_MINIMAX, USER_MINIMAX, AI_MINIMAX }, { USER_MINIMAX, AI_MINIMAX, USER_MINIMAX },
				{ AI_MINIMAX, USER_MINIMAX, AI_MINIMAX } };

		Assertions.assertThat(mapper.as2DArray(list)).isEqualTo(expectation);
	}

	@Test
	public void as2DArray_ValidListContainsEmptyFields_MappedCorrectly() {
		List<Field> list = Arrays.asList(tttField(AI, 1), tttField(USER, 2), tttField(EMPTY, 3), tttField(USER, 4),
				tttField(EMPTY, 5), tttField(USER, 6), tttField(USER, 7), tttField(EMPTY, 8), tttField(AI, 9));

		int[][] expectation = new int[][] { { AI_MINIMAX, USER_MINIMAX, EMPTY_MINIMAX }, { USER_MINIMAX, EMPTY_MINIMAX, USER_MINIMAX },
				{ USER_MINIMAX, EMPTY_MINIMAX, AI_MINIMAX } };

		Assertions.assertThat(mapper.as2DArray(list)).isEqualTo(expectation);
	}

	@Test
	public void as2DArray_InvalidListContainsInvalidStringValue_ThrowsException() {
		List<Field> list = Arrays.asList(tttField(AI, 1), tttField(USER, 2), tttField(AI, 3), tttField(USER, 4), tttField(AI, 5),
				tttField(USER, 6), tttField(AI, 7), tttField(USER, 8), tttField("Invalid", 9));

		Assertions.assertThatThrownBy(() -> mapper.as2DArray(list)).isInstanceOf(MappingNotPossibleException.class)
				.hasMessage("String value could not mapped to int value");
	}

	@Test
	public void asTicTacToeField() {
		List<Field> list = Arrays.asList(tttField(AI, 1), tttField(USER, 2), tttField(AI, 3), tttField(USER, 4), tttField(AI, 5),
				tttField(USER, 6), tttField(AI, 7), tttField(USER, 8), tttField(AI, 9));

		Assertions.assertThat(mapper.asTicTacToeField(new Point(1, 1), list).getValue()).isEqualTo(AI);
		Assertions.assertThat(mapper.asTicTacToeField(new Point(1, 1), list).getFieldId()).isEqualTo(5);
	}

	private Field tttField(String value, int fieldId) {
		Field field = new Field();
		field.setId(1);
		field.setFieldId(fieldId);
		field.setValue(value);
		return field;
	}

}

package org.commons;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.minimax.TicTacToeConstants;
import org.junit.Test;

public class ValidationTest {

	@Test
	public void checkNot_Success() {
		assertThatCode(() -> Validations.checkNot(false, "egal")).doesNotThrowAnyException();
	}

	@Test
	public void checkNot_ThrowsException() {
		String errorMessage = "error message";
		assertThatThrownBy(() -> Validations.checkNot(true, errorMessage)).isInstanceOf(GamePortalExceptions.class).hasMessage("error message");
	}

	@Test
	public void validField_InvalidField_ThrowsException() {
		List<Field> list = new ArrayList<>();
		IntStream.range(0, 7).boxed().forEach(integer -> list.add(createTTTField(integer)));

		Assertions.assertThatThrownBy(() -> Validations.validField(list)).isInstanceOf(GamePortalExceptions.class)
				.hasMessage("Square root of field size must be an integer!");
	}

	@Test
	public void validField_InvalidFieldHasSizeOne_ThrowsrException() {
		List<Field> list = new ArrayList<>(Arrays.asList(createTTTField(2)));

		Assertions.assertThatThrownBy(() -> Validations.validField(list)).isInstanceOf(GamePortalExceptions.class)
				.hasMessage("Square root of field size must be an integer!");
	}

	@Test
	public void validField_ValidField() {
		List<Field> list = new ArrayList<>();
		IntStream.range(0, 9).boxed().forEach(integer -> list.add(createTTTField(integer)));

		Assertions.assertThat(Validations.validField(list)).isTrue();
	}

	@Test
	public void validPoint() {
		List<Field> list = new ArrayList<>();
		IntStream.range(0, 9).boxed().forEach(integer -> list.add(createTTTField(integer)));

		Assertions.assertThat(Validations.validPoint(new Point(1, 1), list)).isEqualTo(ValidPoint.VALID);
	}

	@Test
	public void validPoint_DefaultPoint_ValidPointDefault() {
		List<Field> list = new ArrayList<>();
		IntStream.range(0, 9).boxed().forEach(integer -> list.add(createTTTField(integer)));

		Assertions.assertThatThrownBy(() -> Validations.validPoint(TicTacToeConstants.POINT_FILLED_FIELD, list)).isInstanceOf(InvalidPointException.class)
				.hasMessage("The point coordinates are negative. Is the game over?");
	}

	@Test
	public void validPoint_InvalidPoint_ThrowsException() {
		List<Field> list = new ArrayList<>();
		IntStream.range(0, 9).boxed().forEach(integer -> list.add(createTTTField(integer)));

		Assertions.assertThatThrownBy(() -> Validations.validPoint(new Point(999, 1), list)).isInstanceOf(InvalidPointException.class)
				.hasMessageContaining("The point is outside");
		Assertions.assertThatThrownBy(() -> Validations.validPoint(new Point(1, 999), list)).isInstanceOf(InvalidPointException.class)
				.hasMessageContaining("The point is outside");
		Assertions.assertThatThrownBy(() -> Validations.validPoint(new Point(999, 999), list)).isInstanceOf(InvalidPointException.class)
				.hasMessageContaining("The point is outside");
	}

	private Field createTTTField(long fieldId) {
		Field field = new Field();
		field.setId(1);
		field.setFieldId(fieldId);
		return field;
	}

}

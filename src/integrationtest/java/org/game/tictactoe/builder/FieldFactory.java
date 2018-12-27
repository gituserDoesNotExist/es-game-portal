package org.game.tictactoe.builder;

import org.game.tictactoe.field.Field;

public class FieldFactory {

	public static Field aTicTacToeField(long gameFieldId, long fieldId, String value) {
		Field ticTacToeField = new Field();
		ticTacToeField.setGameId(gameFieldId);
		ticTacToeField.setFieldId(fieldId);
		ticTacToeField.setValue(value);
		return ticTacToeField;
	}

}

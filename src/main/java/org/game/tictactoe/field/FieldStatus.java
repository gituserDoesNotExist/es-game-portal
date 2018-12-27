package org.game.tictactoe.field;

import org.game.tictactoe.pitch.GameStatus;

public class FieldStatus {

	private Field field;
	private GameStatus status;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

}

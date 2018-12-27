package org.game.tictactoe.builder;

import java.time.LocalDateTime;

import org.game.tictactoe.field.Field;

public class FieldBuilder {

	private static FieldBuilder SELF;
	private Field field = new Field();

	private FieldBuilder() {

	}

	public static FieldBuilder aTicTacToeField() {
		SELF = new FieldBuilder();
		return SELF;
	}

	public FieldBuilder withId(long id) {
		field.setId(id);
		return SELF;
	}

	public FieldBuilder withLastModified(LocalDateTime time) {
		field.setLastModified(time);
		return SELF;
	}

	public FieldBuilder withFieldId(long fieldId) {
		field.setFieldId(fieldId);
		return SELF;
	}

	public FieldBuilder withGameId(long gameId) {
		field.setGameId(gameId);
		return SELF;
	}

	public FieldBuilder withValue(String value) {
		field.setValue(value);
		return SELF;
	}

	public Field build() {
		return field;
	}

}

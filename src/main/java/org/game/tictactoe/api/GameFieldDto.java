package org.game.tictactoe.api;

import org.commons.BaseDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "game", "field" })
public class GameFieldDto extends BaseDto {

	private final GameDto game;

	private final FieldDto field;

	public GameFieldDto() {
		this(null, null);
	}

	public GameFieldDto(GameDto game, FieldDto field) {
		this.game = game;
		this.field = field;
	}

	public FieldDto getField() {
		return field;
	}

	public GameDto getGame() {
		return game;
	}

}

package org.game.tictactoe.api;

import java.util.List;

import org.commons.BaseDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "game", "fields" })
public class CompleteGameDto extends BaseDto {

	private final GameDto game;
	private final List<FieldDto> fields;

	public CompleteGameDto() {
		this(null, null);
	}

	public CompleteGameDto(GameDto game, List<FieldDto> fields) {
		this.game = game;
		this.fields = fields;
	}

	public GameDto getGame() {
		return game;
	}

	public List<FieldDto> getFields() {
		return fields;
	}
}

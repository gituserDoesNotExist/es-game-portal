package org.game.tictactoe.api;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "status")
public class GameStatusDto {

	private final long id;
	private final String text;

	public GameStatusDto(long id, String text) {
		this.id = id;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

}

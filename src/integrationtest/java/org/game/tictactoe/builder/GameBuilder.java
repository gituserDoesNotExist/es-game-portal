package org.game.tictactoe.builder;

import java.time.LocalDateTime;

import org.game.tictactoe.pitch.GameStatus;
import org.game.tictactoe.pitch.Game;

public class GameBuilder {

	private GameBuilder SELF;
	private long idValue;
	private boolean idIsSet;
	private LocalDateTime lastModifiedValue;
	private boolean lastModifiedIsSet;
	private LocalDateTime createdAtValue;
	private boolean createdAtIsSet;
	private String descriptionValue;
	private boolean descriptionIsSet;
	private GameStatus gameStatusValue;
	private boolean gameStatusIsSet;

	private GameBuilder() {
		SELF = (GameBuilder) this;
	}

	public static GameBuilder aTicTacToeGame() {
		return new GameBuilder();
	}

	public GameBuilder withId(long id) {
		idValue = id;
		idIsSet = true;
		return SELF;
	}

	public GameBuilder withLastModified(LocalDateTime time) {
		lastModifiedValue = time;
		lastModifiedIsSet = true;
		return SELF;
	}

	public GameBuilder withCreatedAt(LocalDateTime time) {
		createdAtValue = time;
		createdAtIsSet = true;
		return SELF;
	}

	public GameBuilder withDescription(String description) {
		descriptionValue = description;
		descriptionIsSet = true;
		return SELF;
	}

	public GameBuilder withGameStatus(GameStatus status) {
		gameStatusValue = status;
		gameStatusIsSet = true;
		return SELF;
	}

	public Game build() {
		Game game = new Game();
		if (idIsSet)
			game.setId(idValue);
		if (lastModifiedIsSet)
			game.setLastModified(lastModifiedValue);
		if (createdAtIsSet)
			game.setCreatedAt(createdAtValue);
		if (descriptionIsSet)
			game.setDescription(descriptionValue);
		if (gameStatusIsSet)
			game.setStatus(gameStatusValue);
		return game;
	}

}

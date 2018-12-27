package org.game.tictactoe.pitch;

public class GameFieldCreator {

	public Game emptyGameWithoutFields() {
		Game gameField = new Game();
		gameField.setDescription("new game");
		gameField.setStatus(new GameStatus(4L, "Neu"));
		return gameField;
	}

}

package org.game.tictactoe.builder;

import org.game.tictactoe.pitch.GameStatus;

public class GameStatusFactory {

	public static GameStatus aUserHatGewonnen() {
		return new GameStatus(1L, "USER hat gewonnen");
	}

	public static GameStatus aAIHatGewonnen() {
		return new GameStatus(2L, "AI hat gewonnen");
	}

	public static GameStatus aUnentschiedenLastMoveUser() {
		return new GameStatus(3L, "Unentschieden - letzter Zug von User");
	}

	public static GameStatus aUnentschiedenLastMoveAi() {
		return new GameStatus(4L, "Unentschieden - letzter Zug von AI");
	}

	public static GameStatus aSpielLaeuftNoch() {
		return new GameStatus(5L, "Spiel laeuft noch");
	}

}

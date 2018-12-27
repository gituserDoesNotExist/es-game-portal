package org.game.tictactoe.pitch;

import org.game.tictactoe.api.GameStatusDto;

public class GameStatusMapper {

	public GameStatusDto asDto(GameStatus status) {
		return new GameStatusDto(status.getId(), status.getText());
	}

}

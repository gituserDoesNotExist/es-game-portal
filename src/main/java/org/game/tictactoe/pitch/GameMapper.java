package org.game.tictactoe.pitch;

import org.game.tictactoe.api.GameDto;

public class GameMapper {

	public GameDto asDto(Game entity) {
		return new GameDto(entity.getId(), entity.getLastModified(), entity.getDescription(), entity.getStatus());
	}

	public Game fromDto(GameDto dto) {
		Game entity = new Game();
		entity.setId(dto.getId());
		entity.setLastModified(dto.getLastModified());
		entity.setDescription(dto.getDescription());
		entity.setStatus(dto.getStatus());
		return entity;
	}

}

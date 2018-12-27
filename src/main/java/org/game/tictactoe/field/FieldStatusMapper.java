package org.game.tictactoe.field;

import org.game.tictactoe.api.FieldStatusDto;
import org.game.tictactoe.pitch.GameStatus;
import org.game.tictactoe.pitch.GameStatusMapper;

public class FieldStatusMapper {

	private FieldMapper fieldMapper = new FieldMapper();
	private GameStatusMapper statusMapper = new GameStatusMapper();

	public FieldStatus createFieldStatus(Field field, GameStatus status) {
		FieldStatus result = new FieldStatus();

		result.setField(field);
		result.setStatus(status);

		return result;
	}

	public FieldStatusDto asDto(FieldStatus fieldStatus) {
		return new FieldStatusDto(fieldMapper.asDto(fieldStatus.getField()), statusMapper.asDto(fieldStatus.getStatus()));
	}

}

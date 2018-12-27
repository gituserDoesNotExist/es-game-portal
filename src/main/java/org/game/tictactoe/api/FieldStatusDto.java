package org.game.tictactoe.api;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "fieldstatus")
public class FieldStatusDto {

	private final FieldDto field;
	private final GameStatusDto status;

	public FieldStatusDto(FieldDto fieldDto, GameStatusDto statusDto) {
		this.field = fieldDto;
		this.status = statusDto;
	}

	public FieldDto getField() {
		return field;
	}

	public GameStatusDto getStatus() {
		return status;
	}

}

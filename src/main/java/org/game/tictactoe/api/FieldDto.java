package org.game.tictactoe.api;

import java.time.LocalDateTime;

import org.commons.VersionedDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "field")
@JsonPropertyOrder({ "id", "lastModified", "gameId", "fieldId", "value" })
public class FieldDto extends VersionedDto {

	private final long id;
	private final long gameId;
	private final long fieldId;
	private final String value;

	public FieldDto() {
		this(0, null, 0, 0, null);
	}

	public FieldDto(long id, LocalDateTime lastModified, long gameId, long fieldId, String value) {
		super(lastModified);
		this.id = id;
		this.gameId = gameId;
		this.fieldId = fieldId;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public long getGameId() {
		return gameId;
	}

	public long getFieldId() {
		return fieldId;
	}

	public String getValue() {
		return value;
	}
}

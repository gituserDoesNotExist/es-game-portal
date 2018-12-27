package org.game.tictactoe.api;

import java.time.LocalDateTime;

import org.commons.VersionedDto;
import org.game.tictactoe.pitch.GameStatus;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "lastModified", "description", "status" })
public class GameDto extends VersionedDto {

	private final long id;
	private final String description;
	private final GameStatus status;

	public GameDto() {
		// TODO: Nov 26, 2017 Manfred -braucht man für den Jackson-Mapper (entity <-> json)
		this(0, null, null, null);
	}

	public GameDto(long id, LocalDateTime lastModified, String description, GameStatus status) {
		super(lastModified);
		this.id = id;
		this.description = description;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public GameStatus getStatus() {
		return status;
	}

}

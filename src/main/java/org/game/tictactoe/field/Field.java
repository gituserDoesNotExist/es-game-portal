package org.game.tictactoe.field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.commons.ResourceToUpdateNotValidException;
import org.commons.VersionedEntity;

@Entity
@SequenceGenerator(name = "custom", sequenceName = "SEQ_TICTACTOE_FIELD", initialValue = 1, allocationSize = 1)
@Table(name = "TICTACTOE_FIELD")
public class Field extends VersionedEntity {

	@Column(name = "GAME_ID")
	private long gameId;

	@Column(name = "FIELD_ID")
	private long fieldId;
	@Column(name = "VALUE")
	private String value;

	public long getFieldId() {
		return fieldId;
	}

	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameFieldId) {
		this.gameId = gameFieldId;
	}

	public void checkConsistencyForUpdate(Field other) {
		if (other.getFieldId() != this.getFieldId()) {
			throw new ResourceToUpdateNotValidException("Update not possible: FieldIDs do not match");
		}
		if (other.getGameId() != this.getGameId()) {
			throw new ResourceToUpdateNotValidException("Update not possible: Resource refers to other game");
		}
	}

}

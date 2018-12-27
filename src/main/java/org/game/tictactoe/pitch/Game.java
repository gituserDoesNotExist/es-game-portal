package org.game.tictactoe.pitch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.commons.ResourceToUpdateNotValidException;
import org.commons.VersionedEntity;

@Entity
@SequenceGenerator(name = "custom", sequenceName = "SEQ_GAME", initialValue = 1, allocationSize = 1)
@Table(name = "TICTACTOE_GAME")
public class Game extends VersionedEntity {

	@Column(name = "DESCRIPTION")
	private String description;

	@JoinColumn(name = "STATUS_ID")
	@ManyToOne(optional = false)
	private GameStatus status;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public void checkConsistencyForUpdate(Game other) {
		if (other.getId() != this.getId()) {
			throw new ResourceToUpdateNotValidException("Update not possible: IDs do not match");
		}
	}

}

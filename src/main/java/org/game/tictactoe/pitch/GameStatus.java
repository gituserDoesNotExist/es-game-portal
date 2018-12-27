package org.game.tictactoe.pitch;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.commons.BaseEnum;

@Entity
@Table(name = "GAME_STATUS")
public class GameStatus extends BaseEnum {

	@Column(name = "TEXT")
	private String text;

	public GameStatus() {

	}

	public GameStatus(long id, String text) {
		this.text = text;
		this.id = id;
	}

	public String getText() {
		return text;
	}

}

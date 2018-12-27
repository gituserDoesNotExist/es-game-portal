package org.game.tictactoe.pitch;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.game.tictactoe.api.GameDto;
import org.game.tictactoe.builder.GameStatusFactory;
import org.game.tictactoe.builder.GameBuilder;
import org.game.tictactoe.pitch.GameStatus;
import org.game.tictactoe.pitch.Game;
import org.game.tictactoe.pitch.GameMapper;
import org.junit.Test;

public class GameMapperTest {

	private GameMapper mapper = new GameMapper();
	private static final long ID = 1L;
	private static final GameStatus STATUS = GameStatusFactory.aAIHatGewonnen();
	private static final String DESCRIPTION = "description";
	private static final LocalDateTime TIMESTAMP = LocalDateTime.of(2000, 11, 10, 1, 2, 3);

	private Game entity = GameBuilder.aTicTacToeGame().withId(ID).withLastModified(TIMESTAMP).withDescription(DESCRIPTION)
			.withGameStatus(STATUS).build();

	private GameDto dto = new GameDto(1L, TIMESTAMP, DESCRIPTION, STATUS);

	@Test
	public void asDto() {
		Assertions.assertThat(mapper.asDto(entity)).isEqualTo(dto);
	}

	@Test
	public void fromDto() {
		Assertions.assertThat(mapper.fromDto(dto)).isEqualTo(entity);
	}

}

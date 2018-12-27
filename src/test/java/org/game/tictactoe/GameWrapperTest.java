package org.game.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.game.tictactoe.builder.FieldBuilder;
import org.game.tictactoe.builder.GameStatusFactory;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.field.FieldStatus;
import org.game.tictactoe.field.FieldStatusMapper;
import org.game.tictactoe.minimax.TicTacToeConstants;
import org.game.tictactoe.persistence.TicTacToeService;
import org.game.tictactoe.persistence.dao.GameStatusDao;
import org.game.tictactoe.pitch.GameStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameWrapperTest {

	private static final long GAME_ID = 100L;
	private static final long AI_WON = 2L;
	private static final long DRAW_LAST_MOVE_BY_AI = 4L;
	private static final long IN_PROGRESS = 5;

	@InjectMocks
	private GameWrapper wrapper;

	@Mock
	private TicTacToeService service;
	@Mock
	private GameStatusDao gameStatusDao;
	@Mock
	private FieldStatusMapper fieldStatusMapper;

	@Captor
	private ArgumentCaptor<Field> fieldCaptor;
	@Captor
	private ArgumentCaptor<GameStatus> gameStatusCaptor;

	@Test
	public void nextMoveOfAi_GameNotOver_ShouldReturnMoveFromAI() {
		FieldStatus fieldStatus = new FieldStatus();
		when(service.findByGameId(GAME_ID)).thenReturn(Arrays.asList(//
				createField(1L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(2L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD),
				createField(3L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD), createField(4L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD),
				createField(5L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD), createField(6L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD),
				createField(7L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD), createField(8L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD),
				createField(9L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD)));
		when(gameStatusDao.findById(IN_PROGRESS)).thenReturn(new GameStatus(IN_PROGRESS, "Spiel laeuft noch"));
		when(fieldStatusMapper.createFieldStatus(Mockito.any(Field.class), Mockito.eq(GameStatusFactory.aSpielLaeuftNoch()))).thenReturn(fieldStatus);

		FieldStatus nextMove = wrapper.nextMoveOfAi(GAME_ID);

		verify(fieldStatusMapper).createFieldStatus(fieldCaptor.capture(), Mockito.any());

		assertThat(nextMove).isSameAs(fieldStatus);
		assertThat(fieldCaptor.getValue().getFieldId()).isNotEqualTo(1L);
	}

	@Test
	public void makeNextMove_LastMoveByAi_NobodyHasWon_ReturnsDraw() {
		FieldStatus fieldStatus = new FieldStatus();
		when(service.findByGameId(GAME_ID)).thenReturn(Arrays.asList(//
				createField(1L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(2L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER),
				createField(3L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), createField(4L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER),
				createField(5L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(6L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER),
				createField(7L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(8L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER),
				createField(9L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD)));
		when(gameStatusDao.findById(DRAW_LAST_MOVE_BY_AI)).thenReturn(new GameStatus(DRAW_LAST_MOVE_BY_AI, "Unentschieden - letzter Zug von AI"));
		when(fieldStatusMapper.createFieldStatus(Mockito.any(Field.class), Mockito.any())).thenReturn(fieldStatus);

		FieldStatus nextMove = wrapper.nextMoveOfAi(GAME_ID);

		verify(fieldStatusMapper).createFieldStatus(fieldCaptor.capture(), gameStatusCaptor.capture());

		assertThat(nextMove).isSameAs(fieldStatus);
		assertThat(fieldCaptor.getValue().getFieldId()).isEqualTo(9L);
		assertThat(gameStatusCaptor.getValue()).isEqualToComparingFieldByField(GameStatusFactory.aUnentschiedenLastMoveAi());
	}

	@Test
	public void makeNextMove_AiWins() {
		FieldStatus fieldStatus = new FieldStatus();
		when(service.findByGameId(GAME_ID)).thenReturn(Arrays.asList(//
				createField(1L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), createField(2L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER),
				createField(3L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD), createField(4L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD),
				createField(5L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(6L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD),
				createField(7L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(8L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD),
				createField(9L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD)));
		when(gameStatusDao.findById(AI_WON)).thenReturn(new GameStatus(AI_WON, "AI hat gewonnen"));
		when(fieldStatusMapper.createFieldStatus(Mockito.any(Field.class), Mockito.any())).thenReturn(fieldStatus);

		FieldStatus nextMove = wrapper.nextMoveOfAi(GAME_ID);

		verify(fieldStatusMapper).createFieldStatus(fieldCaptor.capture(), gameStatusCaptor.capture());

		assertThat(nextMove).isSameAs(fieldStatus);
		assertThat(fieldCaptor.getValue().getFieldId()).isEqualTo(3L);
		assertThat(gameStatusCaptor.getValue()).isEqualToComparingFieldByField(GameStatusFactory.aAIHatGewonnen());
	}

	@Test
	public void checkIfInvalidMove_EmptyFieldsRemaining_ReturnsFalse() {
		when(service.findByGameId(GAME_ID)).thenReturn(Arrays.asList(//
				createField(1L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(2L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER),
				createField(3L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), createField(4L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER),
				createField(5L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(6L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER),
				createField(7L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), createField(8L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER),
				createField(9L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD)));

		assertThat(wrapper.checkIfInvalidMove(createField(9L, "egal"))).isFalse();
	}

	@Test
	public void checkIfInvalidMove_NoEmptyFieldsRemaining_ReturnsTrue() {
		when(service.findByGameId(GAME_ID)).thenReturn(Arrays.asList(//
				createField(1L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(2L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER),
				createField(3L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), createField(4L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER),
				createField(5L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), createField(6L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER),
				createField(7L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), createField(8L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER),
				createField(9L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER)));

		assertThat(wrapper.checkIfInvalidMove(createField(9L, "egal"))).isTrue();
	}

	@Test
	public void checkIfInvalidMove_FieldsSetByOpponent_MoveOnOpponentsField_ReturnsTrue() {
		when(service.findByGameId(GAME_ID)).thenReturn(Arrays.asList(//
				createField(1L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), //
				createField(2L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), //
				createField(3L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), //
				createField(4L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), //
				createField(5L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), //
				createField(6L, TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER), //
				createField(7L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD), //
				createField(8L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), //
				createField(9L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD)));

		assertThat(wrapper.checkIfInvalidMove(createField(8L, "USER"))).isTrue();
	}

	@Test
	public void checkIfInvalidMove_FieldsSetByOpponent_AiHasAlreadyWon_ReturnsTrue() {
		when(service.findByGameId(GAME_ID)).thenReturn(Arrays.asList(//
				createField(1L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), //
				createField(2L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), //
				createField(3L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), //
				createField(4L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), //
				createField(5L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD), //
				createField(6L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD), //
				createField(7L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD), //
				createField(8L, TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER), //
				createField(9L, TicTacToeConstants.REPRESENTATION_EMPTY_FIELD)));

		assertThat(wrapper.checkIfInvalidMove(createField(6L, "USER"))).isTrue();
	}

	private Field createField(long fieldId, String value) {
		return FieldBuilder.aTicTacToeField().withFieldId(fieldId).withValue(value).withGameId(GAME_ID).withId(2L).build();
	}

}

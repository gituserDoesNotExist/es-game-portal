package org.game.tictactoe.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.commons.ResourceToUpdateNotValidException;
import org.commons.test.AbstractTest;
import org.game.tictactoe.builder.GameStatusFactory;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.persistence.dao.FieldDao;
import org.game.tictactoe.persistence.dao.GameDao;
import org.game.tictactoe.pitch.Game;
import org.game.tictactoe.pitch.GameStatus;
import org.junit.Test;

public class TicTacToeServiceIntegrationTest extends AbstractTest {

	private static final long TICTACTOE_FIELD_ID = 1L;
	private static final long GAME_ID = 1L;
	private TicTacToeService service = new TicTacToeService();
	private FieldDao fieldDao = new FieldDao(service.getEntityManager());
	private GameDao gameDAo = new GameDao(service.getEntityManager());

	@Test
	public void findById() {
		Field result = service.findById(TICTACTOE_FIELD_ID);

		Assertions.assertThat(result.getValue()).isEqualTo("x");
		Assertions.assertThat(result.getGameId()).isEqualTo(1L);
		Assertions.assertThat(result.getFieldId()).isEqualTo(2L);
	}

	@Test
	public void findByGameId() {
		Assertions.assertThat(service.findByGameId(GAME_ID).stream().map(Field::getId).collect(Collectors.toList())).contains(1L, 2L,
				3L, 4L);
	}

	@Test
	public void getNewGame() {
		Game newGame = service.createGame();

		Assertions.assertThat(newGame.getId()).isGreaterThan(0L);
		Assertions.assertThat(newGame.getStatus().getText()).isEqualTo("Neu");
	}

	@Test
	public void createFieldsForGame() {
		List<Field> fields = service.createFieldsForGame(GAME_ID);

		fields.stream().forEach(field -> {
			Assertions.assertThat(field.getId()).isGreaterThan(0L);
			Assertions.assertThat(field.getGameId()).isEqualTo(GAME_ID);
		});
	}

	@Test
	public void updateSingleField() {
		String newValue = "new value";
		Field fromDb = fieldDao.findById(TICTACTOE_FIELD_ID);
		service.getEntityManager().detach(fromDb);

		fromDb.setValue(newValue);
		Field updated = service.updateField(fromDb);

		Field retrieved = fieldDao.findById(TICTACTOE_FIELD_ID);

		Assertions.assertThat(retrieved.getValue()).isEqualTo(newValue).isEqualTo(updated.getValue());
	}

	@Test
	public void updateGame() {
		GameStatus newStatus = GameStatusFactory.aAIHatGewonnen();
		Game fromDb = gameDAo.findById(GAME_ID);
		service.getEntityManager().detach(fromDb);

		fromDb.setStatus(newStatus);
		Game updated = service.updateGame(fromDb);

		Game retrieved = gameDAo.findById(GAME_ID);

		Assertions.assertThat(retrieved.getStatus()).isEqualTo(newStatus).isEqualTo(updated.getStatus());
	}

	@Test
	public void updateField_ChangeGameFieldId_ThrowsException() {
		Field toUpdate = fieldDao.findById(TICTACTOE_FIELD_ID);
		service.getEntityManager().detach(toUpdate);

		toUpdate.setGameId(999L);
		Assertions.assertThatThrownBy(() -> service.updateField(toUpdate)).isInstanceOf(ResourceToUpdateNotValidException.class)
				.hasMessage("Update not possible: Resource refers to other game");
		service.getEntityManager().getTransaction().commit();
	}

	@Test
	public void updateField_ChangeFieldId_ThrowsException() {
		Field toUpdate = fieldDao.findById(TICTACTOE_FIELD_ID);
		service.getEntityManager().detach(toUpdate);

		toUpdate.setFieldId(3L);
		Assertions.assertThatThrownBy(() -> service.updateField(toUpdate)).isInstanceOf(ResourceToUpdateNotValidException.class)
				.hasMessage("Update not possible: FieldIDs do not match");
		service.getEntityManager().getTransaction().commit();
	}

}

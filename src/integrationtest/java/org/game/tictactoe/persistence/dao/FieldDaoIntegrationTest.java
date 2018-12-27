package org.game.tictactoe.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.commons.test.AbstractDaoTest;
import org.game.tictactoe.builder.FieldBuilder;
import org.game.tictactoe.field.Field;
import org.junit.Test;

public class FieldDaoIntegrationTest extends AbstractDaoTest {

	private static final long TICTACTOE_FIELD_ID = 1L;
	private static final int ID_GAME_FIELD_WITH_TTTFIELDS = 1;
	private static final long GAME_FIELD_ID = 2L;

	private FieldDao tictactoeFieldDao = new FieldDao(entityManager);

	@Test
	public void findAll() {
		List<Field> result = tictactoeFieldDao.findAll();

		Assertions.assertThat(result.stream().map(Field::getId)).containsExactly(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L,
				14L);
	}

	@Test
	public void findById() {
		Field result = tictactoeFieldDao.findById(TICTACTOE_FIELD_ID);

		Assertions.assertThat(result.getValue()).isEqualTo("x");
		Assertions.assertThat(result.getGameId()).isEqualTo(1L);
		Assertions.assertThat(result.getFieldId()).isEqualTo(2L);
	}

	@Test
	public void findByGameFieldId() {
		List<Field> result = tictactoeFieldDao.findByGameFieldId(ID_GAME_FIELD_WITH_TTTFIELDS);

		Assertions.assertThat(result.stream().map(Field::getFieldId)).containsExactly(2L, 3L, 4L, 5L);
		Assertions.assertThat(result.stream().map(Field::getValue)).containsExactly("x", "o", "x", "o");
	}

	@Test
	public void findTicTacToeFieldWithGameFieldDescription() {
		List<Field> result = tictactoeFieldDao.findTicTacToeFieldWithGameDescription("third game");

		Assertions.assertThat(result.stream().map(field -> field.getId()).collect(Collectors.toList())).containsExactly(5L);
	}

	@Test
	public void save_IdNotSet_IdIsCreated() {
		Field saved = tictactoeFieldDao.saveTransientEntity(
				FieldBuilder.aTicTacToeField().withId(0L).withFieldId(1).withGameId(GAME_FIELD_ID).withValue("xoxo").build());
		flush();
		Assertions.assertThat(saved.getId()).isGreaterThan(0L);
	}

	@Test
	public void save_IdIsSet_ButIdIsCreated() {
		Field saved = tictactoeFieldDao.saveTransientEntity(
				FieldBuilder.aTicTacToeField().withId(999L).withFieldId(1).withGameId(GAME_FIELD_ID).withValue("xoxoxoxo").build());
		flush();
		Assertions.assertThat(saved.getId()).isNotEqualTo(999L);
	}

	@Test
	public void update_AssertThatEntityGivenToUpdateMethodIsNotEqualToEntityReturnedByUpdateMethod() {
		String newValue = "new value";
		Field fromDb = findAndDetach();

		fromDb.setValue(newValue);
		Field updated = tictactoeFieldDao.updateDetachedEntity(fromDb);
		flush();

		Assertions.assertThat(updated.getValue()).isEqualTo(newValue);
		Assertions.assertThat(updated.getLastModified()).isAfter(LocalDateTime.now().minusSeconds(2L));
		Assertions.assertThat(updated == fromDb).isFalse();
	}

	private Field findAndDetach() {
		Field inDb = tictactoeFieldDao.findById(TICTACTOE_FIELD_ID);
		entityManager.detach(inDb);
		return inDb;
	}

}

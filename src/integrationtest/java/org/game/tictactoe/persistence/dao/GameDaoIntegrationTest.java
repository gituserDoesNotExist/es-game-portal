package org.game.tictactoe.persistence.dao;

import static org.game.tictactoe.builder.GameStatusFactory.aUnentschiedenLastMoveUser;
import static org.game.tictactoe.builder.GameStatusFactory.aUserHatGewonnen;

import java.time.LocalDateTime;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.assertj.core.api.Assertions;
import org.commons.test.AbstractDaoTest;
import org.game.tictactoe.builder.GameBuilder;
import org.game.tictactoe.pitch.Game;
import org.junit.Test;

public class GameDaoIntegrationTest extends AbstractDaoTest {

	private GameDao gameDao = new GameDao(entityManager);

	private static final long GAME_ID = 2L;

	@Test
	public void findById() {
		Game expectation = GameBuilder.aTicTacToeGame().withId(GAME_ID).withDescription("game")
				.withLastModified(LocalDateTime.of(2016, 12, 17, 9, 02, 54)).withCreatedAt(LocalDateTime.of(2013, 11, 17, 9, 02, 54))
				.withGameStatus(aUserHatGewonnen()).build();

		Game result = gameDao.findById(GAME_ID);

		Assertions.assertThat(result).isEqualToComparingFieldByFieldRecursively(expectation);
	}

	@Test
	public void save_IdNotSet_IdIsCreated() {
		Game game = gameWithId(0L).build();
		Game saved = gameDao.saveTransientEntity(game);
		flush();

		Assertions.assertThat(saved.getId()).isGreaterThan(0L);
	}

	@Test
	public void save_IdIsSet_ButIdIsCreated() {
		Game game = gameWithId(999L).build();
		Game saved = gameDao.saveTransientEntity(game);
		flush();

		Assertions.assertThat(saved.getId()).isNotEqualTo(999L);
	}

	@Test
	public void save_IdAndCreatedAtAreSet_NewEntityWithNewIdAndCreatedAtAreCreated() {
		Game game = gameWithId(999L).withCreatedAt(LocalDateTime.now().minusYears(20)).build();
		Game saved = gameDao.saveTransientEntity(game);
		flush();

		Assertions.assertThat(saved.getId()).isNotEqualTo(999L);
		Assertions.assertThat(gameDao.findById(saved.getId()).getCreatedAt()).isAfter(LocalDateTime.now().minusSeconds(2L));
	}

	@Test
	public void save_IdAndLastModifiedAreSet_HibernateThinksEntityAlreadyPersisted_ThrowsException() {
		Game game = gameWithId(999L).withLastModified(LocalDateTime.now().minusYears(20)).build();

		Assertions.assertThatThrownBy(() -> gameDao.saveTransientEntity(game)).isInstanceOf(PersistenceException.class);
	}

	@Test
	public void save_LastModifiedIsSet_NewEntityIsCreated() {
		Game game = gameWithId(0L).withLastModified(LocalDateTime.now().minusYears(20)).build();

		Game saved = gameDao.saveTransientEntity(game);
		flush();

		Assertions.assertThat(saved.getId()).isNotEqualTo(0L);
		Assertions.assertThat(gameDao.findById(saved.getId()).getCreatedAt()).isAfter(LocalDateTime.now().minusSeconds(2L));
	}

	@Test
	public void updateDetachedEntity() {
		String newDescription = "description for update";
		Game game = findAndDetach(GAME_ID);
		game.setDescription(newDescription);

		Game updated = gameDao.updateDetachedEntity(game);
		flush();

		Assertions.assertThat(updated.getDescription()).isEqualTo(newDescription);
		Assertions.assertThat(updated.getLastModified()).isAfter(LocalDateTime.now().minusSeconds(2L));
		System.out.println(updated);
		System.out.println(game);
		Assertions.assertThat(updated == game).isFalse();
	}

	@Test
	public void updateEntity_InputHasOnlyIdSet_NewEntityIsCreated() {
		String onlyIdIsSet = "only id is set";
		Game game = findAndDetach(GAME_ID);
		game.setLastModified(null);
		game.setCreatedAt(null);
		game.setDescription(onlyIdIsSet);

		Game updated = gameDao.updateDetachedEntity(game);
		flush();

		Assertions.assertThat(updated.getId()).isNotEqualTo(game.getId());
		Assertions.assertThat(updated.getCreatedAt()).isAfter(LocalDateTime.now().minusSeconds(2L));
		Assertions.assertThat(gameDao.findById(updated.getId()).getDescription()).isEqualTo(onlyIdIsSet);
	}

	@Test
	public void updateEntity_InputHasOnlyIdAndCreatedAtSet_NewEntityIsCreated() {
		String onlyIdAndCreatedAtSet = "only id and createdAt is set";
		Game game = findAndDetach(GAME_ID);
		game.setLastModified(null);
		game.setDescription(onlyIdAndCreatedAtSet);

		Game updated = gameDao.updateDetachedEntity(game);
		flush();

		Assertions.assertThat(updated.getId()).isNotEqualTo(game.getId());
		Assertions.assertThat(updated.getCreatedAt()).isAfter(LocalDateTime.now().minusSeconds(2L));
		Assertions.assertThat(gameDao.findById(updated.getId()).getDescription()).isEqualTo(onlyIdAndCreatedAtSet);
	}

	@Test
	public void updateEntity_InputHasOnlyIdAndLastModifiedSet_NoNewEntityIsCreated_ExistingEntityIsUpdated_CreatedAtIsNotChanged() {
		// ID und lastModified müssen gesetzt sein, ansonsten wird eine neue Entität angelegt
		String onlyIdAndLastModifiedSet = "only id and lastModified set";
		Game game = findAndDetach(GAME_ID);
		game.setCreatedAt(null);
		game.setDescription(onlyIdAndLastModifiedSet);

		Game updated = gameDao.updateDetachedEntity(game);
		flush();
		entityManager.clear();

		Assertions.assertThat(updated.getId()).isEqualTo(game.getId());
		Assertions.assertThat(gameDao.findById(GAME_ID).getCreatedAt()).isEqualTo(LocalDateTime.of(2013, 11, 17, 9, 2, 54));
		Assertions.assertThat(gameDao.findById(GAME_ID).getDescription()).isEqualTo(onlyIdAndLastModifiedSet);
	}

	@Test
	public void updateEntity_InputHasOnlyLastModifiedSet_NewEntityIsCreated() {
		String onlyLastModifiedSet = "only lastModified is set";
		Game game = findAndDetach(GAME_ID);
		game.setCreatedAt(null);
		game.setId(0L);
		game.setDescription(onlyLastModifiedSet);

		Game updated = gameDao.updateDetachedEntity(game);
		flush();

		entityManager.clear();
		Assertions.assertThat(updated.getId()).isNotEqualTo(game.getId());
		Assertions.assertThat(gameDao.findById(updated.getId()).getCreatedAt()).isAfter(LocalDateTime.now().minusSeconds(2L));
		Assertions.assertThat(gameDao.findById(updated.getId()).getDescription()).isEqualTo(onlyLastModifiedSet);

	}

	@Test
	public void update_EntityWasSavedBefore_ThrowsOptimisticLockingException() {
		Game gameField = findAndDetach(GAME_ID);

		Game gameFieldOtherUser = findAndDetach(GAME_ID);
		gameFieldOtherUser.setDescription("description from user who secondly fetched entity");
		entityManager.merge(gameFieldOtherUser);
		flush();

		gameField.setDescription("new description from user who firstly fetched entity");

		Assertions.assertThatThrownBy(() -> updateGameFieldChangedByFirstUser(gameField)).isInstanceOf(OptimisticLockException.class);
	}

	private GameBuilder gameWithId(long id) {
		return GameBuilder.aTicTacToeGame().withId(id).withDescription("another game").withGameStatus(aUnentschiedenLastMoveUser());
	}

	private Game findAndDetach(long id) {
		Game gameField = gameDao.findById(id);
		entityManager.detach(gameField);
		return gameField;
	}

	private void updateGameFieldChangedByFirstUser(Game gameField) {
		gameDao.updateDetachedEntity(gameField);
		flush();
	}

}

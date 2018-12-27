package org.game.tictactoe.persistence.dao;

import org.assertj.core.api.Assertions;
import org.commons.test.AbstractDaoTest;
import org.junit.Test;

public class GameStatusDaoIntegrationTest extends AbstractDaoTest {

	GameStatusDao dao = new GameStatusDao(entityManager);

	@Test
	public void findById_ReturnsResult() {
		Assertions.assertThat(dao.findById(2L).getText()).isEqualTo("AI hat gewonnen");
	}

}

package org.game.tictactoe.persistence.playground;

import org.commons.AbstractService;
import org.game.tictactoe.persistence.dao.GameDao;
import org.game.tictactoe.pitch.GameFieldCreator;
import org.game.tictactoe.persistence.dao.FieldDao;

public class DummyService extends AbstractService {

	private GameDao gameFieldDao = new GameDao(entityManager);
	private FieldDao tictactoeFieldDao = new FieldDao(entityManager);
	private GameFieldCreator creator = new GameFieldCreator();

}

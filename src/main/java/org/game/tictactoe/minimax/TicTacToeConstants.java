package org.game.tictactoe.minimax;

import java.awt.Point;

public class TicTacToeConstants {

	public static final int NUMBER_OF_FIELDS = 9;
	public static final int MAXIMIZING_PLAYER = 1;
	public static final int MINIMIZING_PLAYER = 2;
	public static final int EMPTY_FIELD = 0;
	public static final Point POINT_FILLED_FIELD = new Point(-1, -1);
	public static final long FIELDID_FILLED_GAMEFIELD = -1L;

	public static final String REPRESENTATION_MAXIMIZING_PLAYER = "AI";
	public static final String REPRESENTATION_MINIMZING_PLAYER = "USER";
	public static final String REPRESENTATION_EMPTY_FIELD = "--";

	public static final int VALUE_SUCCESS_MAXIMIZING_PLAYER = 1;
	public static final int VALUE_SUCCESS_MINIMIZING_PLAYER = -1;
}

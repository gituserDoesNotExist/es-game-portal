package org.commons;

import java.awt.Point;
import java.util.List;

import org.game.tictactoe.minimax.TicTacToeConstants;

public class Validations {

	public static void checkNot(boolean condition, String message) {
		if (condition) {
			throw new GamePortalExceptions(message);
		}
	}

	public static <T extends BaseEntity> boolean validField(List<T> fields) {
		if (!isInteger(Math.sqrt(fields.size())) || fields.size() == 1) {
			throw new GamePortalExceptions("Square root of field size must be an integer!");
		}
		return true;
	}

	public static <T extends BaseEntity> ValidPoint validPoint(Point point, List<T> fields) {
		if (point.equals(TicTacToeConstants.POINT_FILLED_FIELD))
			throw new InvalidPointException("The point coordinates are negative. Is the game over?");
		Validations.validField(fields);
		int edgeLength = (int) Math.sqrt(fields.size());
		if (point.getX() > edgeLength || point.getY() > edgeLength) {
			throw new InvalidPointException("The point is outside the range of the game field");
		}
		return ValidPoint.VALID;
	}

	private static boolean isInteger(double value) {
		return Math.ceil(value) == Math.floor(value) ? true : false;
	}

}

package org.game.tictactoe.minimax;

import java.awt.Point;
import java.util.List;

public class Minimax {

	private Point compMove = TicTacToeConstants.POINT_FILLED_FIELD;
	private Rules rules = new Rules();
	private int[][] gameField2DArray;

	public Point makeAMove(int[][] gameField2DArray) {
		this.gameField2DArray = gameField2DArray;
		lastMinimax(0, TicTacToeConstants.MAXIMIZING_PLAYER);
		return compMove;
	}

	private int lastMinimax(int depth, int player) {
		if (rules.hasPlayerWon(TicTacToeConstants.MAXIMIZING_PLAYER, gameField2DArray)) {
			return TicTacToeConstants.VALUE_SUCCESS_MAXIMIZING_PLAYER;
		}
		if (rules.hasPlayerWon(TicTacToeConstants.MINIMIZING_PLAYER, gameField2DArray)) {
			return TicTacToeConstants.VALUE_SUCCESS_MINIMIZING_PLAYER;
		}

		List<Point> emptyFields = rules.determineBlankFields(TicTacToeConstants.EMPTY_FIELD, gameField2DArray);
		if (emptyFields.isEmpty()) {
			return 0;
		}

		if (player == TicTacToeConstants.MAXIMIZING_PLAYER) {
			int bestValue = Integer.MIN_VALUE;
			for (Point point : emptyFields) {
				fillFieldIfEmpty(point, TicTacToeConstants.MAXIMIZING_PLAYER);
				int currentValue = lastMinimax(depth + 1, TicTacToeConstants.MINIMIZING_PLAYER);
				bestValue = Math.max(bestValue, currentValue);
				if (depth == 0) {
					// System.out.println("computer score for point " + point + ":" + currentValue);
				}
				if (depth == 0 && currentValue >= bestValue) {
					compMove = point;
				}
				fillField(point, TicTacToeConstants.EMPTY_FIELD);
			}
			return bestValue;
		}

		if (player == TicTacToeConstants.MINIMIZING_PLAYER) {
			int bestValue = Integer.MAX_VALUE;
			for (Point point : emptyFields) {
				fillFieldIfEmpty(point, TicTacToeConstants.MINIMIZING_PLAYER);
				int currentValue = lastMinimax(depth + 1, TicTacToeConstants.MAXIMIZING_PLAYER);
				bestValue = Math.min(bestValue, currentValue);
				fillField(point, TicTacToeConstants.EMPTY_FIELD);
			}
			return bestValue;
		}
		return 100;
	}

	private boolean fillFieldIfEmpty(Point point, int spieler) {
		if (gameField2DArray[point.x][point.y] != TicTacToeConstants.EMPTY_FIELD) {
			return false;
		}
		gameField2DArray[point.x][point.y] = spieler;
		return true;
	}

	private void fillField(Point point, int spieler) {
		gameField2DArray[point.x][point.y] = spieler;
	}

}

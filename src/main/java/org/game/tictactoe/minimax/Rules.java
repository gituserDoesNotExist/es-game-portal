package org.game.tictactoe.minimax;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Rules {

	private int edgeLength = (int) Math.sqrt(TicTacToeConstants.NUMBER_OF_FIELDS);

	public boolean isGameOver(int player1, int player2, int valueEmptyField, int[][] gameField2DArray) {
		return hasPlayerWon(player1, gameField2DArray) || hasPlayerWon(player2, gameField2DArray)
				|| determineBlankFields(valueEmptyField, gameField2DArray).size() == 0;
	}

	public boolean hasPlayerWon(int spieler, int[][] gameField2DArray) {
		if (compareFields(gameField2DArray[0][0], gameField2DArray[1][1], gameField2DArray[2][2], spieler)) {
			return true;
		}
		if (compareFields(gameField2DArray[2][0], gameField2DArray[1][1], gameField2DArray[0][2], spieler)) {
			return true;
		}
		boolean result = false;
		for (int index = 0; index < edgeLength; index++) {
			boolean waagrecht = compareFields(gameField2DArray[0][index], gameField2DArray[1][index], gameField2DArray[2][index], spieler);
			boolean senkrecht = compareFields(gameField2DArray[index][0], gameField2DArray[index][1], gameField2DArray[index][2], spieler);
			if (waagrecht || senkrecht) {
				result = waagrecht || senkrecht;
				break;
			}
		}
		return result;
	}

	private boolean compareFields(int field1, int field2, int field3, int player) {
		return field1 == field2 && field1 == field3 && field1 == player;
	}

	public List<Point> determineBlankFields(int valueEmptyField, int[][] gameField2DArray) {
		List<Point> list = new ArrayList<>();
		for (int row = 0; row < edgeLength; row++) {
			for (int col = 0; col < edgeLength; col++) {
				if (gameField2DArray[row][col] == valueEmptyField) {
					list.add(new Point(row, col));
				}
			}
		}
		return list;
	}

	public boolean fieldsAlreadyFilled(int valueEmptyField, int[][] gameField2DArray) {
		return (determineBlankFields(valueEmptyField, gameField2DArray).size() == 0) ? true : false;
	}

}

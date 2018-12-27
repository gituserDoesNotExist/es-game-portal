package org.game.tictactoe.field;

import java.awt.Point;
import java.util.List;

import org.commons.MappingNotPossibleException;
import org.commons.Validations;
import org.game.tictactoe.minimax.TicTacToeConstants;

public class FieldsTo2DArrayMapper {

	public int[][] as2DArray(List<Field> fields) {
		Validations.validField(fields);
		int edgeLength = (int) Math.sqrt(fields.size());
		int[][] field2DArray = new int[edgeLength][edgeLength];

		for (int row = 0; row < edgeLength; row++) {
			for (int col = 0; col < edgeLength; col++) {
				field2DArray[row][col] = valueToNumber(fields.get(row * edgeLength + col).getValue());
			}
		}
		return field2DArray;
	}

	public Field asTicTacToeField(Point point, List<Field> fields) {
		Validations.validPoint(point, fields);
		int edgeLength = (int) Math.sqrt(fields.size());
		int fieldId = point.x * edgeLength + point.y;
		fields.get(fieldId).setValue(TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER);
		return fields.get(fieldId);
	}

	private int valueToNumber(String value) {
		switch (value) {
		case TicTacToeConstants.REPRESENTATION_MAXIMIZING_PLAYER:
			return TicTacToeConstants.MAXIMIZING_PLAYER;
		case TicTacToeConstants.REPRESENTATION_MINIMZING_PLAYER:
			return TicTacToeConstants.MINIMIZING_PLAYER;
		case TicTacToeConstants.REPRESENTATION_EMPTY_FIELD:
			return TicTacToeConstants.EMPTY_FIELD;
		default:
			throw new MappingNotPossibleException("String value could not mapped to int value");
		}
	}

}

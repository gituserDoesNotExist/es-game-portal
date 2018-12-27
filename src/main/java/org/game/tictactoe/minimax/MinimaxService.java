package org.game.tictactoe.minimax;

import java.awt.Point;
import java.util.List;

import org.game.tictactoe.field.Field;
import org.game.tictactoe.field.FieldsTo2DArrayMapper;

public class MinimaxService {

	private Minimax minimax = new Minimax();
	private FieldsTo2DArrayMapper mapper = new FieldsTo2DArrayMapper();

	public Field computeNextMove(List<Field> fields) {
		int[][] as2DArray = mapper.as2DArray(fields);
		Point move = minimax.makeAMove(as2DArray);
		return mapper.asTicTacToeField(move, fields);
	}

}

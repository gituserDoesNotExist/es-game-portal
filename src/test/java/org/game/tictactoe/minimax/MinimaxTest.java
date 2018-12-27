package org.game.tictactoe.minimax;

import java.awt.Point;

import org.assertj.core.api.Assertions;
import org.game.tictactoe.minimax.TicTacToeConstants;
import org.game.tictactoe.minimax.Minimax;
import org.junit.Test;

public class MinimaxTest {

	private static final int EMPTY = 0;
	private static final int PL_HUMAN = TicTacToeConstants.MINIMIZING_PLAYER;
	private static final int PL_AI = TicTacToeConstants.MAXIMIZING_PLAYER;
	private Minimax minimax = new Minimax();

	@Test
	public void makeAMove_PreventDefeat() {
		int[][] field1 = new int[][] { { EMPTY, EMPTY, EMPTY }, { EMPTY, PL_HUMAN, EMPTY }, { EMPTY, PL_HUMAN, PL_AI } };
		int[][] field2 = new int[][] { { EMPTY, PL_AI, EMPTY }, { EMPTY, PL_HUMAN, PL_HUMAN }, { EMPTY, PL_HUMAN, PL_AI } };
		int[][] field3 = new int[][] { { PL_AI, EMPTY, PL_HUMAN }, { EMPTY, PL_HUMAN, PL_HUMAN }, { EMPTY, PL_HUMAN, PL_AI } };

		Assertions.assertThat(minimax.makeAMove(field1)).isEqualToComparingFieldByField(new Point(0, 1));
		Assertions.assertThat(minimax.makeAMove(field2)).isEqualToComparingFieldByField(new Point(1, 0));
		Assertions.assertThat(minimax.makeAMove(field3)).isEqualToComparingFieldByField(new Point(2, 0));
	}

	@Test
	public void makeAMove_DoSameWhenHumanDoesSame() {
		int[][] field1 = new int[][] { { EMPTY, EMPTY, EMPTY }, { EMPTY, PL_HUMAN, EMPTY }, { EMPTY, EMPTY, EMPTY } };
		int[][] field2 = new int[][] { { EMPTY, EMPTY, EMPTY }, { PL_HUMAN, EMPTY, EMPTY }, { EMPTY, EMPTY, EMPTY } };

		Assertions.assertThat(minimax.makeAMove(field1)).isEqualToComparingFieldByField(new Point(2, 2));
		Assertions.assertThat(minimax.makeAMove(field2)).isEqualToComparingFieldByField(new Point(2, 0));
	}

	@Test
	public void makeAMove_Win() {
		int[][] field1 = new int[][] { { EMPTY, PL_AI, PL_AI }, { PL_HUMAN, PL_HUMAN, EMPTY }, { PL_HUMAN, PL_HUMAN, PL_AI } };
		int[][] field2 = new int[][] { { PL_HUMAN, EMPTY, EMPTY }, { PL_HUMAN, PL_HUMAN, PL_AI }, { PL_AI, PL_HUMAN, PL_AI } };

		Assertions.assertThat(minimax.makeAMove(field1)).isEqualToComparingFieldByField(new Point(1, 2));
		Assertions.assertThat(minimax.makeAMove(field2)).isEqualToComparingFieldByField(new Point(0, 2));
	}
}

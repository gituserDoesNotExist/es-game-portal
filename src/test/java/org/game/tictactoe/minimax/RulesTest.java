package org.game.tictactoe.minimax;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Point;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class RulesTest {

	private static final int EMPTY = TicTacToeConstants.EMPTY_FIELD;
	private static final int PL_2 = TicTacToeConstants.MINIMIZING_PLAYER;
	private static final int PL_1 = TicTacToeConstants.MAXIMIZING_PLAYER;
	private Rules rules = new Rules();

	@Test
	public void hasPlayerWon_True() {
		int[][] fieldWin1 = new int[][] { { PL_1, PL_2, PL_2 }, { PL_1, PL_2, PL_2 }, { PL_1, PL_2, PL_2 } };
		int[][] fieldWin2 = new int[][] { { PL_2, PL_1, PL_2 }, { PL_2, PL_1, PL_2 }, { PL_2, PL_1, PL_2 } };
		int[][] fieldWin3 = new int[][] { { PL_2, PL_2, PL_1 }, { PL_2, PL_2, PL_1 }, { PL_2, PL_2, PL_1 } };
		int[][] fieldWin4 = new int[][] { { PL_1, PL_1, PL_1 }, { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin5 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_1, PL_1, PL_1 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin6 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 }, { PL_1, PL_1, PL_1 } };
		int[][] fieldWin7 = new int[][] { { PL_1, PL_2, PL_2 }, { PL_2, PL_1, PL_2 }, { PL_2, PL_2, PL_1 } };
		int[][] fieldWin8 = new int[][] { { PL_2, PL_2, PL_1 }, { PL_2, PL_1, PL_2 }, { PL_1, PL_2, PL_2 } };

		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin1)).isTrue();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin2)).isTrue();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin3)).isTrue();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin4)).isTrue();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin5)).isTrue();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin6)).isTrue();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin7)).isTrue();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin8)).isTrue();
	}

	@Test
	public void hasPlayerWon_False() {
		int[][] fieldWin1 = new int[][] { { PL_1, PL_2, PL_2 }, { PL_1, PL_2, PL_2 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin2 = new int[][] { { PL_2, PL_1, PL_2 }, { PL_2, PL_1, PL_2 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin3 = new int[][] { { PL_2, PL_2, PL_1 }, { PL_2, PL_2, PL_1 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin4 = new int[][] { { PL_1, PL_1, PL_2 }, { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin5 = new int[][] { { PL_1, PL_2, PL_1 }, { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin6 = new int[][] { { PL_2, PL_1, PL_1 }, { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin7 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_1, PL_1, PL_2 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin8 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_1, PL_2, PL_1 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin9 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_2, PL_1, PL_1 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin10 = new int[][] { { PL_1, PL_2, PL_2 }, { PL_2, PL_1, PL_1 }, { PL_2, PL_2, PL_2 } };
		int[][] fieldWin11 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 }, { PL_1, PL_1, PL_2 } };
		int[][] fieldWin12 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 }, { PL_1, PL_2, PL_1 } };
		int[][] fieldWin13 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 }, { PL_2, PL_1, PL_1 } };
		int[][] fieldWin14 = new int[][] { { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 } };

		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin1)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin2)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin3)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin4)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin5)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin6)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin7)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin8)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin9)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin10)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin11)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin12)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin13)).isFalse();
		Assertions.assertThat(rules.hasPlayerWon(PL_1, fieldWin14)).isFalse();
	}

	@Test
	public void isGameOver() {
		int[][] field1 = new int[][] { { PL_1, PL_2, PL_2 }, { PL_1, PL_2, PL_2 }, { PL_2, PL_2, PL_2 } };
		int[][] field2 = new int[][] { { PL_1, PL_2, PL_2 }, { PL_1, EMPTY, EMPTY }, { PL_2, PL_2, EMPTY } };
		Assertions.assertThat(rules.isGameOver(PL_1, PL_2, EMPTY, field1)).isTrue();
		Assertions.assertThat(rules.isGameOver(PL_1, PL_2, EMPTY, field2)).isFalse();
	}

	@Test
	public void determineBlankFields() {
		int[][] field1 = new int[][] { { PL_1, PL_2, PL_2 }, { PL_1, PL_2, PL_2 }, { PL_2, PL_2, EMPTY } };
		int[][] field2 = new int[][] { { PL_1, PL_2, PL_2 }, { EMPTY, EMPTY, PL_2 }, { PL_2, PL_2, EMPTY } };
		int[][] field3 = new int[][] { { EMPTY, EMPTY, PL_2 }, { EMPTY, EMPTY, EMPTY }, { PL_2, PL_2, EMPTY } };

		Assertions.assertThat(rules.determineBlankFields(EMPTY, field1)).containsExactly(point(2, 2));
		Assertions.assertThat(rules.determineBlankFields(EMPTY, field2)).containsExactly(point(1, 0), point(1, 1), point(2, 2));
		Assertions.assertThat(rules.determineBlankFields(EMPTY, field3)).containsExactly(point(0, 0), point(0, 1), point(1, 0), point(1, 1),
				point(1, 2), point(2, 2));
	}

	@Test
	public void areAllFieldsAlreadyFilled() {
		int[][] field1 = new int[][] { { PL_1, PL_2, PL_2 }, { PL_1, PL_2, PL_2 }, { PL_2, PL_2, EMPTY } };
		int[][] filledField = new int[][] { { PL_1, PL_2, PL_2 }, { PL_2, PL_2, PL_2 }, { PL_2, PL_2, PL_2 } };

		assertThat(rules.fieldsAlreadyFilled(EMPTY, field1)).isFalse();
		assertThat(rules.fieldsAlreadyFilled(EMPTY, filledField)).isTrue();
	}

	private Point point(int x, int y) {
		return new Point(x, y);
	}

}

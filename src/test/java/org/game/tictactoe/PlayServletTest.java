package org.game.tictactoe;

import org.game.tictactoe.builder.FieldBuilder;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.field.FieldMapper;
import org.game.tictactoe.field.FieldStatusMapper;
import org.game.tictactoe.persistence.TicTacToeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlayServletTest {

	@InjectMocks
	private AiServlet servlet = new AiServlet();

	@Mock
	private TicTacToeService tictactoeService;
	@Mock
	private FieldMapper fieldMapper;
	@Mock
	private GameWrapper wrapper;
	@Mock
	private FieldStatusMapper fieldStatusMapper;

	@Test
	public void doPut_InvalidMove_DoesNotUpdate() {
		Field field = FieldBuilder.aTicTacToeField().build();
		Mockito.when(wrapper.checkIfInvalidMove(field)).thenReturn(true);

		Mockito.verifyZeroInteractions(tictactoeService);
	}

}

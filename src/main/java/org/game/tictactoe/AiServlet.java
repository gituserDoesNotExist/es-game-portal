package org.game.tictactoe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.commons.mapper.EntityMapper;
import org.commons.mapper.RequestExtractor;
import org.game.tictactoe.api.FieldStatusDto;
import org.game.tictactoe.field.FieldStatus;
import org.game.tictactoe.field.FieldStatusMapper;
import org.game.tictactoe.persistence.TicTacToeService;

@WebServlet(description = "Get next move from AI", urlPatterns = { "/field/ai/move/*" })
public class AiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private TicTacToeService tictactoeService = new TicTacToeService();
	private GameWrapper wrapper = new GameWrapper();
	private FieldStatusMapper fieldStatusMapper = new FieldStatusMapper();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("--get called--");

		long gameId = RequestExtractor.retrieveId(request);

		FieldStatus makeNextMove = wrapper.nextMoveOfAi(gameId);

		tictactoeService.updateField(makeNextMove.getField());

		FieldStatusDto responseEntity = fieldStatusMapper.asDto(makeNextMove);

		PrintWriter writer = response.getWriter();
		writer.write(EntityMapper.entityToJson(responseEntity));
		writer.close();
	}

}
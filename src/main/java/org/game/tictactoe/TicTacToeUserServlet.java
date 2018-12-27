package org.game.tictactoe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.commons.GamePortalExceptions;
import org.commons.mapper.EntityMapper;
import org.commons.mapper.RequestExtractor;
import org.game.tictactoe.api.FieldDto;
import org.game.tictactoe.api.FieldStatusDto;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.field.FieldMapper;
import org.game.tictactoe.field.FieldStatus;
import org.game.tictactoe.field.FieldStatusMapper;
import org.game.tictactoe.persistence.TicTacToeService;

@WebServlet(description = "Update field from user for the given ID", urlPatterns = { "/field/move/*" })
public class TicTacToeUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private TicTacToeService tictactoeService = new TicTacToeService();
	private FieldMapper fieldMapper = new FieldMapper();
	private GameWrapper wrapper = new GameWrapper();
	private FieldStatusMapper fieldStatusMapper = new FieldStatusMapper();

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("--put called--");

		FieldDto dto = EntityMapper.jsonToEntity(RequestExtractor.readBody(request), FieldDto.class);
		Field fieldFromUser = fieldMapper.fromUpdatedDto(RequestExtractor.retrieveId(request), dto);

		PrintWriter writer = response.getWriter();
		try {
			FieldStatus move = wrapper.analyzeMoveFromUser(fieldFromUser);

			tictactoeService.updateField(fieldFromUser);
			FieldStatusDto responseEntity = fieldStatusMapper.asDto(move);

			writer.write(EntityMapper.entityToJson(responseEntity));
		} catch (GamePortalExceptions e) {
			writer.write("boeser Zug");
		} finally {
			writer.close();
		}
	}

}
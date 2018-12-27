package org.game.tictactoe;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.commons.mapper.EntityMapper;
import org.game.tictactoe.api.CompleteGameDto;
import org.game.tictactoe.field.Field;
import org.game.tictactoe.field.FieldMapper;
import org.game.tictactoe.persistence.TicTacToeService;
import org.game.tictactoe.pitch.Game;
import org.game.tictactoe.pitch.GameMapper;

@WebServlet(description = "Handle Play-Request", urlPatterns = "/game/load-tictactoe")
public class LoadNewGameServlet extends HttpServlet {

	private static final long serialVersionUID = -4267990675498253515L;

	private FieldMapper fieldMapper = new FieldMapper();
	private GameMapper gameMapper = new GameMapper();
	private TicTacToeService service = new TicTacToeService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		System.out.println("hello world");
		PrintWriter writer = response.getWriter();
		Game newGame = service.createGame();
		List<Field> fields = service.createFieldsForGame(newGame.getId());
		CompleteGameDto dto = new CompleteGameDto(gameMapper.asDto(newGame), fieldMapper.asDtoList(fields));
		String json = EntityMapper.entityToJson(dto);
		System.out.println(json);
		writer.write(json);
		writer.close();
	}
}

// man wurde zur Resource /game/tictactoe weitergeleitet. Davon ausgehend leitet man zu tictactoe.html weiter
// RequestDispatcher dispatcher = request.getRequestDispatcher("tictactoe.html");
// dispatcher.forward(request, response);
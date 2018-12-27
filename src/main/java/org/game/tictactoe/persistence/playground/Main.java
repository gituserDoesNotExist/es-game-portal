package org.game.tictactoe.persistence.playground;

import java.io.File;
import java.net.URL;

import org.game.tictactoe.persistence.TicTacToeService;

public class Main {

	public static void main(String[] args) {
		String[] property = System.getProperty("java.class.path").split(File.pathSeparator);
		for (String string : property) {
			System.out.println(string);
		}

		System.out.println("xxx");
		URL resource = Main.class.getResource("Main.class");
		String path = resource.getPath();
		System.out.println(path);

		System.out.println("two entityManager?");
		TicTacToeService tttService = new TicTacToeService();
		DummyService dummyService = new DummyService();

		System.out.println(
				"tttService.getEM equals dummyService.getEM: " + tttService.getEntityManager().equals(dummyService.getEntityManager()));
		System.out.println("tttService.getEM: " + tttService.getEntityManager());
		System.out.println("dummyService.getEM: " + dummyService.getEntityManager());
	}

}

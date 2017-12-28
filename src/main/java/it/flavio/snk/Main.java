package it.flavio.snk;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		logger.info("Starting app!");

		Application app = new Application();
//		app.console();
		app.testDB();
		logger.info("Exiting");
	}
}

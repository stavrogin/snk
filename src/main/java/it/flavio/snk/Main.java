package it.flavio.snk;

import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.flavio.snk.console.Console;
import it.flavio.snk.console.ConsoleFactory;
import it.flavio.snk.constants.Constants;
import it.flavio.snk.service.DataService;
import it.flavio.snk.service.DataServiceFactory;

/**
 * 
 * Main class to run the SNK application
 * Available command list:
 * 
 * 	posting: <user name> -> <message>
 *	reading: <user name>
 *	following: <user name> follows <another user>
 *	wall: <user name> wall
 *  exit
 *
 * @author flavio
 *
 */
public class Main {
	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		logger.info("*** Starting app! ***");
		
		DataService dataService = DataServiceFactory.getDataService(Constants.JPA_PERSISTENCE_UNIT_NAME);
		Console console = ConsoleFactory.getConsole();
		
		Application app = new Application();
		app.setDataService(dataService);
		app.setConsole(console);
		app.writeHeader();
		
		Scanner scanner = new Scanner(System.in);
		String input;
		
		do {
			input = scanner.nextLine();
			app.processInput(input);
		} while (!"exit".equals(input));
		
		scanner.close();
		
		logger.info("*** Exiting ***");
	}
}

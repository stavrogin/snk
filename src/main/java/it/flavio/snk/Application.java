package it.flavio.snk;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandFactory;
import it.flavio.snk.service.DataService;
import it.flavio.snk.utils.ConsoleUtils;

/**
 * Class to create and launch the console application
 * @author flavio
 */
public class Application {

	private static Logger logger = LogManager.getLogger(Main.class);
	
	private DataService dataService;
	
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	/**
	 * Starts the console application by waiting for user inputs
	 */
	public void console() {
		
		writeHeader();
		Scanner scanner = new Scanner(System.in);
		String input;
		
		do {
			input = scanner.nextLine();
			Command cmd = CommandFactory.getCommand(input);
			if (cmd != null) {
				cmd.setDataService(dataService);
				cmd.execute();
			} else {
				logger.info(String.format("Unknown command: %s", input));
				ConsoleUtils.write("Unknown command: skipping");
			}
		} while (!"exit".equals(input));
		scanner.close();

	}
	
	/**
	 * Writes the application presentation
	 */
	private void writeHeader() {
		ConsoleUtils.write("Command list:");
		ConsoleUtils.write("<user> -> <mesassage> : user posts a message on his timeline");
		ConsoleUtils.write("<user> : view user's timeline (all messages written by him)");
		ConsoleUtils.write("<user> follows <another_user> : user follows another user");
		ConsoleUtils.write("<user> wall : view user's wall (his timeline and his followed people's");
		ConsoleUtils.write("exit : close the application");
		ConsoleUtils.write("Please insert a command");
	}

}

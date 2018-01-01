package it.flavio.snk;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandFactory;
import it.flavio.snk.console.Console;
import it.flavio.snk.service.DataService;

/**
 * Class to create and launch the console application
 * @author flavio
 */
public class Application {

	private DataService dataService;
	private Console console;
	
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public void setConsole(Console console) {
		this.console = console;
	}
	
	/**
	 * Processes the input string and run the proper command
	 * @param input the input string
	 */
	public void processInput(String input) {
		Command cmd = CommandFactory.getCommand(input);
		cmd.setDataService(dataService);
		cmd.setConsole(console);
		cmd.execute();
		cmd.write();
	}
	
	/**
	 * Writes the application presentation
	 */
	public void writeHeader() {
		console.write("Command list:");
		console.write("<user> -> <mesassage> : user posts a message on his timeline");
		console.write("<user> : view user's timeline (all messages written by him)");
		console.write("<user> follows <another_user> : user follows another user");
		console.write("<user> wall : view user's wall (his timeline and his followed people's)");
		console.write("exit : close the application");
		console.write("Please insert a command");
	}

}

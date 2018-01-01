package it.flavio.snk;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandFactory;
import it.flavio.snk.console.OutputWriter;
import it.flavio.snk.service.DataService;

/**
 * Class to create and launch the console application
 * @author flavio
 */
public class Application {

	private DataService dataService;
	private OutputWriter output;
	
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
	public void setConsole(OutputWriter console) {
		this.output = console;
	}
	
	/**
	 * Processes the input string and run the proper command
	 * @param input the input string
	 */
	public void processInput(String input) {
		Command cmd = CommandFactory.getCommand(input);
		cmd.setDataService(dataService);
		cmd.setOutput(output);
		cmd.execute();
		cmd.write();
	}
	
	/**
	 * Writes the application presentation
	 */
	public void writeHeader() {
		output.write("Command list:");
		output.write("<user> -> <mesassage> : user posts a message on his timeline");
		output.write("<user> : view user's timeline (all messages written by him)");
		output.write("<user> follows <another_user> : user follows another user");
		output.write("<user> wall : view user's wall (his timeline and his followed people's)");
		output.write("exit : close the application");
		output.write("Please insert a command");
	}

}

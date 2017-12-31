package it.flavio.snk.command;

import it.flavio.snk.console.Console;
import it.flavio.snk.service.DataService;

/**
 * 
 * Interface to execute the command provided by the console input
 * @author flavio
 *
 */
public interface Command {

	/**
	 * Sets the data service
	 * @param dataService the data service interface
	 */
	void setDataService(DataService dataService);
	
	/**
	 * Sets the console
	 * @param console the console to write output to
	 */
	void setConsole(Console console);
	
	/**
	 * Executes the command
	 */
	void execute();
	
	/**
	 * Writes on console
	 */
	void write();
	
}

package it.flavio.snk.command;

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
	 * Executes the command
	 */
	void execute();
	
}

package it.flavio.snk.command;

import java.util.List;

import it.flavio.snk.console.OutputWriter;
import it.flavio.snk.database.model.Message;
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
	void setOutput(OutputWriter console);
	
	/**
	 * Executes the command
	 */
	void execute();
	
	/**
	 * Writes on console
	 */
	void write();
	
	/**
	 * Gets the returned message list
	 * @return the message list to be printed
	 */
	List<Message> getMessageList();
}

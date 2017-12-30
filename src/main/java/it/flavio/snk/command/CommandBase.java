package it.flavio.snk.command;

import it.flavio.snk.service.DataService;

/**
 * Base class for all the command implementations
 * @author flavio
 */
public abstract class CommandBase implements Command {

	protected DataService dataService;
	
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	};
	
}

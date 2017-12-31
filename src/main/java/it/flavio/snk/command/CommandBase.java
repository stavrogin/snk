package it.flavio.snk.command;

import java.util.List;

import it.flavio.snk.console.Console;
import it.flavio.snk.database.model.Message;
import it.flavio.snk.service.DataService;

/**
 * Base class for all the command implementations
 * @author flavio
 */
public abstract class CommandBase implements Command {

	protected DataService dataService;
	protected Console console;
	protected List<Message> messageList;
	
	@Override
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	};
	
	@Override
	public void setConsole(Console console) {
		this.console = console;
	}
	
	@Override
	public void write() {}
	
	@Override
	public List<Message> getMessageList() {
		return messageList;
	}
	
}

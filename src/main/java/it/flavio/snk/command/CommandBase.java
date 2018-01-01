package it.flavio.snk.command;

import java.util.List;

import it.flavio.snk.console.OutputWriter;
import it.flavio.snk.database.model.Message;
import it.flavio.snk.service.DataService;

/**
 * Base class for all the command implementations
 * @author flavio
 */
public abstract class CommandBase implements Command {

	protected DataService dataService;
	protected OutputWriter output;
	protected List<Message> messageList;
	
	@Override
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	};
	
	@Override
	public void setOutput(OutputWriter console) {
		this.output = console;
	}
	
	@Override
	public void write() {}
	
	@Override
	public List<Message> getMessageList() {
		return messageList;
	}
	
}

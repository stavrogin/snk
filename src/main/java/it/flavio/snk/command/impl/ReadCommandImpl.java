package it.flavio.snk.command.impl;

import java.util.List;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;
import it.flavio.snk.database.model.Message;
import it.flavio.snk.service.DataService;
import it.flavio.snk.utils.ConsoleUtils;

/**
 * Command implementation to read a user's timeline (all his posts)
 * @author flavio
 */
public class ReadCommandImpl extends CommandBase implements Command {

	private String user;
	
	public ReadCommandImpl(DataService dataService, String user) {
		this.dataService = dataService;
		this.user = user;
	}

	@Override
	public void execute() {
		List<Message> messageList = dataService.getMessagesByUserName(user);
	}
	
	/**
	 * Writes an output on the console
	 */
	private void write(List<Message> messageList) {
		messageList.stream()
			.sorted((Message m1, Message m2) -> m1.getInsertts().compareTo(m2.getInsertts()))
			.forEach(m -> ConsoleUtils.write(m));
	}

}
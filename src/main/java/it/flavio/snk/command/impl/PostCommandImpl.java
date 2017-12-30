package it.flavio.snk.command.impl;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;
import it.flavio.snk.service.DataService;

/**
 * Command implementation to post a message on the personal timeline
 * @author flavio
 */
public class PostCommandImpl extends CommandBase implements Command {

	private String user;
	private String messageText;
	
	public PostCommandImpl(DataService dataService, String user, String messageText) {
		this.dataService = dataService;
		this.user = user;
		this.messageText = messageText;
	}

	@Override
	public void execute() {
		dataService.createMessage(user, messageText);
	}

}

package it.flavio.snk.command.impl;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;
import it.flavio.snk.configuration.Settings;
import it.flavio.snk.database.model.Message;

/**
 * Command implementation to read a user's timeline (all his posts)
 * 
 * @author flavio
 */
public class ReadCommandImpl extends CommandBase implements Command {

	private String user;

	public ReadCommandImpl(String user) {
		this.user = user;
	}

	@Override
	public void execute() {
		messageList = dataService.getMessagesByUserName(user);
	}

	@Override
	public void write() {
		if (messageList != null) {
			messageList.stream()
				.sorted((Message m1, Message m2) -> m2.getInsertts().compareTo(m1.getInsertts()))
				.limit(Settings.getInstance().getTimelineMaxLength())
				.forEach(m -> console.writeTimelineMessage(m));
		}
	}

}

package it.flavio.snk.command.impl;

import java.util.List;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;
import it.flavio.snk.configuration.Settings;
import it.flavio.snk.database.model.Message;
import it.flavio.snk.utils.ConsoleUtils;

/**
 * Command implementation to read a user's timeline (all his posts)
 * @author flavio
 */
public class ReadCommandImpl extends CommandBase implements Command {

	private String user;
	
	public ReadCommandImpl(String user) {
		this.user = user;
	}

	@Override
	public void execute() {
		List<Message> messageList = dataService.getMessagesByUserName(user);
		write(messageList);
	}
	
	/**
	 * Writes an output on the console
	 * @param messageList the messages to write
	 */
	private void write(List<Message> messageList) {
		messageList.stream()
			.sorted((Message m1, Message m2) -> m2.getInsertts().compareTo(m1.getInsertts()))
			.limit(Settings.getInstance().getTimelineMaxLength())
			.forEach(m -> ConsoleUtils.writeTimeLineMessage(m));
	}

}

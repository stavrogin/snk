package it.flavio.snk.command.impl;

import java.util.List;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;
import it.flavio.snk.configuration.Settings;
import it.flavio.snk.database.model.Message;
import it.flavio.snk.utils.ConsoleUtils;

/**
 * Command implementation to view a user's wall (his timeline and his followed users')
 * @author flavio
 */
public class WallCommandImpl extends CommandBase implements Command {

	private String user;

	public WallCommandImpl(String user) {
		this.user = user;
	}

	@Override
	public void execute() {
		List<Message> messageList = dataService.getAllFollowedUsersMessages(user);
		write(messageList);
	}

	/**
	 * Writes output to console
	 * @param messageList the messages to write
	 */
	private void write(List<Message> messageList) {
		messageList.stream()
				.sorted((Message m1, Message m2) -> m2.getInsertts().compareTo(m1.getInsertts()))
				.limit(Settings.getInstance().getWallMaxLength())
				.forEach(m -> ConsoleUtils.writeWallMessage(m));
		ConsoleUtils.writeEmptyLine();
	}

}

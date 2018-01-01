package it.flavio.snk.command.impl;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;
import it.flavio.snk.configuration.Settings;
import it.flavio.snk.database.model.Message;

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
		messageList = dataService.getAllFollowedUsersMessages(user);
	}

	@Override
	public void write() {
		if (messageList != null) {
			messageList.stream()
				.sorted((Message m1, Message m2) -> m2.getInsertts().compareTo(m1.getInsertts()))
				.limit(Settings.getInstance().getWallMaxLength())
				.forEach(m -> output.writeWallMessage(m));
		}
	}

}

package it.flavio.snk.command.impl;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;
import it.flavio.snk.service.DataService;

/**
 * Command implementation to view a user's wall (his timeline and his followed users')
 * @author flavio
 */
public class WallCommandImpl extends CommandBase implements Command {

	private String user;
	
	public WallCommandImpl(DataService dataService, String user) {
		this.dataService = dataService;
		this.user = user;
	}

	@Override
	public void execute() {
		dataService.getAllFollowedUsersMessages(user);
	}

}

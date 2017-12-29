package it.flavio.snk.command;

import it.flavio.snk.service.DataService;

public class WallCommandImpl extends CommandBase implements Command {

	private String user;
	
	public WallCommandImpl(DataService dataService, String user) {
		this.dataService = dataService;
		this.user = user;
	}

	@Override
	public void execute() {
		System.out.println("WALL");
		dataService.getAllFollowedUsersMessages(user);
	}

}

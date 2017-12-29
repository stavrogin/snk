package it.flavio.snk.command;

import it.flavio.snk.service.DataService;

public class FollowCommandImpl extends CommandBase implements Command {

	private String follower;
	private String followed;
	
	public FollowCommandImpl(DataService dataService, String follower, String followed) {
		this.dataService = dataService;
		this.follower = follower;
		this.followed = followed;
	}

	@Override
	public void execute() {
		System.out.println("FOLLOW");
		dataService.follow(follower, followed);
	}

}

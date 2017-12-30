package it.flavio.snk.command.impl;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;

/**
 * Command implementation to make a user following another user
 * @author flavio
 */
public class FollowCommandImpl extends CommandBase implements Command {

	private String follower;
	private String followed;
	
	public FollowCommandImpl(String follower, String followed) {
		this.follower = follower;
		this.followed = followed;
	}

	@Override
	public void execute() {
		dataService.follow(follower, followed);
	}

}

package it.flavio.snk.command.impl;

import java.util.List;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;
import it.flavio.snk.database.model.User;

public class FriendsCommandImpl extends CommandBase implements Command {

	private String user;
	private List<User> friends;
	
	public FriendsCommandImpl(String user) {
		this.user = user;
	}
	
	@Override
	public void execute() {
		friends = dataService.getFriendsList(user);
		
	}
	
	@Override
	public void write() {
		friends.forEach(u -> output.writeFriend(u));
	}

}

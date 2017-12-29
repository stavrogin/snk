package it.flavio.snk.command;

import it.flavio.snk.service.DataService;

public class ReadCommandImpl extends CommandBase implements Command {

	private String user;
	
	public ReadCommandImpl(DataService dataService, String user) {
		this.dataService = dataService;
		this.user = user;
	}

	@Override
	public void execute() {
		System.out.println("READ");
		dataService.getMessagesByUserName(user);
	}

}

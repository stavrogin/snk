package it.flavio.snk.command;

import it.flavio.snk.service.DataService;

public class PostCommandImpl extends CommandBase implements Command {

	private String user;
	private String messageText;
	
	public PostCommandImpl(DataService dataService, String user, String messageText) {
		this.dataService = dataService;
		this.user = user;
		this.messageText = messageText;
	}

	@Override
	public void execute() {
		System.out.println("POST");
		//dataService.postMessage
	}

}

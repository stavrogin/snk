package it.flavio.snk.command.impl;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;

/**
 * Command implementation of a not known command (outputs an unknown command message)
 * @author flavio
 */
public class UnknownCommand extends CommandBase implements Command {

	private String input;
	
	public UnknownCommand(String input) {
		this.input = input;
	}
	
	@Override
	public void execute() {	}

	@Override
	public void write() {
		console.write("Unknown command: " + input);
	}
}

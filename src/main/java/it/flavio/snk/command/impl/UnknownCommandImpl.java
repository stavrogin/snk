package it.flavio.snk.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.flavio.snk.command.Command;
import it.flavio.snk.command.CommandBase;

/**
 * Command implementation of a not known command (outputs an unknown command message)
 * @author flavio
 */
public class UnknownCommandImpl extends CommandBase implements Command {

	private static Logger logger = LogManager.getLogger(UnknownCommandImpl.class);
	
	private String input;
	
	public UnknownCommandImpl(String input) {
		logger.info("Unknown command: " + input);
		this.input = input;
	}
	
	@Override
	public void execute() {	}

	@Override
	public void write() {
		output.write("Unknown command: " + input);
	}
}

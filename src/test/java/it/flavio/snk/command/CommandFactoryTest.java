package it.flavio.snk.command;

import org.junit.Assert;
import org.junit.Test;

import it.flavio.snk.command.impl.FollowCommandImpl;
import it.flavio.snk.command.impl.PostCommandImpl;
import it.flavio.snk.command.impl.ReadCommandImpl;
import it.flavio.snk.command.impl.UnknownCommand;
import it.flavio.snk.command.impl.WallCommandImpl;

/**
 * Tests if the input pattern corresponds to the right command
 * @author flavio
 */
public class CommandFactoryTest {
	
	@Test
	public void evaluateInputCommand() {
		
		Command command = CommandFactory.getCommand("testuser");
		Assert.assertTrue(command instanceof ReadCommandImpl);
		
		command = CommandFactory.getCommand("testuser -> testmessage1 testmessage2 testmessage3");
		Assert.assertTrue(command instanceof PostCommandImpl);
		
		command = CommandFactory.getCommand("testuser wall");
		Assert.assertTrue(command instanceof WallCommandImpl);
		
		command = CommandFactory.getCommand("testuser follows anothertestuser");
		Assert.assertTrue(command instanceof FollowCommandImpl);
		
		command = CommandFactory.getCommand("");
		Assert.assertTrue(command instanceof UnknownCommand);

		command = CommandFactory.getCommand("testuser ->");
		Assert.assertTrue(command instanceof UnknownCommand);
		
		command = CommandFactory.getCommand(">testuser");
		Assert.assertTrue(command instanceof UnknownCommand);
		
		command = CommandFactory.getCommand("testuser follow anotheruser");
		Assert.assertTrue(command instanceof UnknownCommand);
	}

}

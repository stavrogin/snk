package it.flavio.snk.command;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.flavio.snk.command.impl.FollowCommandImpl;
import it.flavio.snk.command.impl.PostCommandImpl;
import it.flavio.snk.command.impl.ReadCommandImpl;
import it.flavio.snk.command.impl.UnknownCommandImpl;
import it.flavio.snk.command.impl.WallCommandImpl;
import it.flavio.snk.console.OutputWriter;
import it.flavio.snk.console.OutputWriterFactory;
import it.flavio.snk.constants.Constants;
import it.flavio.snk.database.model.Message;
import it.flavio.snk.service.DataService;
import it.flavio.snk.service.DataServiceFactory;

/**
 * Tests if the input pattern corresponds to the right command
 * @author flavio
 */
public class CommandTest {

	private DataService dataService;
	private OutputWriter output;
	
	@Before
	public void setUp() {
		dataService = DataServiceFactory.getDataService(Constants.JPA_TEST_PERSISTENCE_UNIT_NAME);
		output = OutputWriterFactory.getOutput();
	}
	
	@After 
	public void tearDown() {
		dataService.deleteAllMessages();
		dataService.deleteAllUsers();
	}
	
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
		Assert.assertTrue(command instanceof UnknownCommandImpl);

		command = CommandFactory.getCommand("testuser ->");
		Assert.assertTrue(command instanceof UnknownCommandImpl);
		
		command = CommandFactory.getCommand(">testuser");
		Assert.assertTrue(command instanceof UnknownCommandImpl);
		
		command = CommandFactory.getCommand("testuser follow anotheruser");
		Assert.assertTrue(command instanceof UnknownCommandImpl);
	}
	
	@Test 
	public void postCommandTest() {
		Command cmd = CommandFactory.getCommand("testuser -> ciao");
		cmd.setDataService(dataService);
		cmd.execute();

		List<Message> messages = dataService.getMessagesByUserName("testuser");
		assertEquals(messages.size(), 1);
		assertEquals(messages.get(0).getMessage(), "ciao");
	}
	
	@Test 
	public void followReadWallCommandTest() {
		//testuser writes ciao
		Command cmd = CommandFactory.getCommand("testuser -> ciao");
		cmd.setDataService(dataService);
		cmd.execute();
		
		//testuser2 writes ciao
		cmd = CommandFactory.getCommand("testuser2 -> testmessage");
		cmd.setDataService(dataService);
		cmd.execute();
		
		//testuser follows testuser2
		cmd = CommandFactory.getCommand("testuser follows testuser2");
		cmd.setDataService(dataService);
		cmd.execute();
		
		//read testuser timeline
		cmd = CommandFactory.getCommand("testuser");
		cmd.setDataService(dataService);
		cmd.execute();

		List<Message> messages = cmd.getMessageList();
		assertEquals(messages.size(), 1);
		
		//read testuser wall
		cmd = CommandFactory.getCommand("testuser wall");
		cmd.setDataService(dataService);
		cmd.execute();

		messages = cmd.getMessageList();
		assertEquals(messages.size(), 2);
	}
	
	@Test
	public void evaluateFriends() {
		Command cmd = CommandFactory.getCommand("bob follows jim");
		cmd.setDataService(dataService);
		cmd.execute();
		
		cmd = CommandFactory.getCommand("bob follows tom");
		cmd.setDataService(dataService);
		cmd.execute();
		cmd = CommandFactory.getCommand("bob follows mark");
		cmd.setDataService(dataService);
		cmd.execute();
		
		cmd = CommandFactory.getCommand("bob friends");
		cmd.setDataService(dataService);
		cmd.setOutput(output);
		cmd.execute();
		
		cmd.write();
	}

}

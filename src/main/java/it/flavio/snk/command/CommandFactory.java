package it.flavio.snk.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.flavio.snk.command.impl.FollowCommandImpl;
import it.flavio.snk.command.impl.PostCommandImpl;
import it.flavio.snk.command.impl.ReadCommandImpl;
import it.flavio.snk.command.impl.WallCommandImpl;
import it.flavio.snk.service.DataService;

/**
 * Factory responsible to create the proper Command implementation depending on the
 * user input
 * @author flavio
 */
public class CommandFactory {
	
	private static final Pattern POST_PATTERN = Pattern.compile("^(\\w+) -> (.+)$");
	private static final Pattern READ_PATTERN = Pattern.compile("^(\\w+)$");
	private static final Pattern FOLLOW_PATTERN = Pattern.compile("^(\\w+) follows (\\w+)$");
	private static final Pattern WALL_PATTERN = Pattern.compile("^(\\w+) wall$");
	
	/**
	 * Factory method to create the proper Command implementation depending on the input string
	 * @param input the user input string
	 * @param dataService the data service
	 * @return the proper Command implementation (null if not found)
	 */
	public static Command getCommand(String input, DataService dataService) {
		Matcher matcher = POST_PATTERN.matcher(input);
		if (matcher.find()) {
			return buildPostCommand(matcher.group(1), matcher.group(2), dataService);
		} 
		
		matcher = READ_PATTERN.matcher(input);
		if (matcher.find()) {
			return buildReadCommand(matcher.group(1), dataService);
		}
		
		matcher = FOLLOW_PATTERN.matcher(input);
		if (matcher.find()) {
			return buildFollowCommand(matcher.group(1), matcher.group(2), dataService);
		}
		
		matcher = WALL_PATTERN.matcher(input);
		if (matcher.find()) {
			return buildWallCommand(matcher.group(1), dataService);
		}
		
		return null;
	}
	
	/**
	 * Builds post command
	 * @param user the user name
	 * @param message the message to post
	 * @param dataService the data service
	 * @return the build command
	 */
	private static Command buildPostCommand(String user, String message, DataService dataService) {
		Command postCommand = new PostCommandImpl(user, message);
		postCommand.setDataService(dataService);
		return postCommand;
	}
	
	/**
	 * Builds read command
	 * @param user the user to read message from
	 * @param dataService the data service
	 * @return the read command
	 */
	private static Command buildReadCommand(String user, DataService dataService) {
		Command readCommand = new ReadCommandImpl(user);
		readCommand.setDataService(dataService);
		return readCommand;
	}
	
	/**
	 * Builds follow command
	 * @param follower the follower name
	 * @param followed the followed name
	 * @param dataService the data service
	 * @return the follow command
	 */
	private static Command buildFollowCommand(String follower, String followed, DataService dataService) {
		Command followCommand = new FollowCommandImpl(follower, followed);
		followCommand.setDataService(dataService);
		return followCommand;
	}
	
	/**
	 * Builds the wall command
	 * @param user the user name
	 * @param dataService the data service
	 * @return the wall command
	 */
	private static Command buildWallCommand(String user, DataService dataService) {
		Command wallCommand = new WallCommandImpl(user);
		wallCommand.setDataService(dataService);
		return wallCommand;
	}
	
}

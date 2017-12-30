package it.flavio.snk.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.flavio.snk.service.DataService;

/**
 * 
 * Factory responsible to create the proper Command implementation depending on the
 * user input
 * @author flavio
 *
 */
public class CommandFactory {
	
	private static final Pattern POST_PATTERN = Pattern.compile("^(\\w+) -> (.+)$");
	private static final Pattern READ_PATTERN = Pattern.compile("^(\\w+)$");
	private static final Pattern FOLLOW_PATTERN = Pattern.compile("(\\w+) follows (\\w+)");
	private static final Pattern WALL_PATTERN = Pattern.compile("(\\w+) wall");
	
	/**
	 * Factory method to create the proper Command implementation
	 * @param input the user input string
	 * @param dataService the data service
	 * @return the proper Command implementation (null if not found)
	 */
	public static Command getCommand(String input, DataService dataService) {
		Matcher matcher = POST_PATTERN.matcher(input);
		if (matcher.find()) {
			return new PostCommandImpl(dataService, matcher.group(1), matcher.group(2));
		} 
		
		matcher = READ_PATTERN.matcher(input);
		if (matcher.find()) {
			return new ReadCommandImpl(dataService, matcher.group(1));
		}
		
		matcher = FOLLOW_PATTERN.matcher(input);
		if (matcher.find()) {
			return new FollowCommandImpl(dataService, matcher.group(1), matcher.group(2));
		}
		
		matcher = WALL_PATTERN.matcher(input);
		if (matcher.find()) {
			return new WallCommandImpl(dataService, matcher.group(1));
		}
		
		return null;
	}
	
}
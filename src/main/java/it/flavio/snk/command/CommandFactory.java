package it.flavio.snk.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.flavio.snk.command.impl.FollowCommandImpl;
import it.flavio.snk.command.impl.PostCommandImpl;
import it.flavio.snk.command.impl.ReadCommandImpl;
import it.flavio.snk.command.impl.UnknownCommandImpl;
import it.flavio.snk.command.impl.WallCommandImpl;

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
	 * @return the proper Command implementation (null if not found)
	 */
	public static Command getCommand(String input) {
		Matcher matcher = POST_PATTERN.matcher(input);
		if (matcher.find()) {
			return new PostCommandImpl(matcher.group(1), matcher.group(2));
		} 
		
		matcher = READ_PATTERN.matcher(input);
		if (matcher.find()) {
			return new ReadCommandImpl(matcher.group(1));
		}
		
		matcher = FOLLOW_PATTERN.matcher(input);
		if (matcher.find()) {
			return new FollowCommandImpl(matcher.group(1), matcher.group(2));
		}
		
		matcher = WALL_PATTERN.matcher(input);
		if (matcher.find()) {
			return new WallCommandImpl(matcher.group(1));
		}
		
		return new UnknownCommandImpl(input);
	}
	
}

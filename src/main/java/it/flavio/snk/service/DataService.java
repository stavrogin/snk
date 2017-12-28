package it.flavio.snk.service;

import java.util.List;

import it.flavio.snk.database.model.Message;
import it.flavio.snk.database.model.User;

/**
 * Data service interface to perform data operations
 * @author flavio
 */
public interface DataService {
	
	/**
	 * Creates a new user from the passed-in name
	 * @param name the user name
	 */
	void createUser(String name);
	
	/**
	 * Gets a user by user name
	 * @param name the user name
	 * @return the user corresponding to the passed-in name; null if not found
	 */
	User getUserByName(String name);
	
	/**
	 * Gets all the messages of the passed-in user name
	 * @param name the user name
	 * @return the user message list
	 */
	List<Message> getUserMessages(String name);
	
	/**
	 * Gets the list of the users followed by the passed-in user name
	 * @param name the user name
	 * @return the list of the followed users
	 */
	List<User> getFollowedUsersByFollowerName(String name);
	
	/**
	 * Gets the list of the followers of the passed-in user name
	 * @param name the user name
	 * @return the followers list
	 */
	List<User> getFollowersByUserName(String name);

}

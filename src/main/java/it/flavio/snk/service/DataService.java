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
	 * Sets the JPA persistence unit name
	 * @param name the persistence unit name
	 */
	void setPersistenceUnitName(String persistenceUnitName);
	
	/**
	 * Creates a new user from the passed-in name
	 * @param name the user name
	 */
	User retrieveUser(String name);
	
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
	
	/** 
	 * Register the follower-followed association
	 * @param followerName the user that wants to follow someone
	 * @param followedName the user that is wanted to be followed
	 */
	void follow(String followerName, String followedName);
	
	/**
	 * Posts a message to the passed-in user timeline
	 * @param name the user name
	 * @param text the message text
	 */
	void createMessage(String name, String text);
	
	/**
	 * Get all the passed-in user messages
	 * @param name the user name
	 */
	List<Message> getMessagesByUserName(String name);
	
	/**
	 * Get all the followed user messages, including the one of the passed-in user
	 * @param name the user name
	 * @return all the followed user messages, including the one of the passed-in user
	 */
	List<Message> getAllFollowedUsersMessages(String name);
	
	/**
	 * Delete all messages
	 */
	void deleteAllMessages();
	
	/**
	 * Delete all users
	 */
	void deleteAllUsers();

	List<User> getFriendsList(String userName);
}

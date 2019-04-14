package it.flavio.snk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.h2.util.StringUtils;

import it.flavio.snk.database.model.Message;
import it.flavio.snk.database.model.User;

/**
 * Implementation of DataService interface, in order to retrieve data
 * from the configured database
 * @author flavio
 */
public class DataServiceImpl implements DataService {

	private static EntityManagerFactory factory;
	private EntityManager em;
	
	private String persistenceUnitName;

	@Override
	public User retrieveUser(String name) {
		User user = getUserByName(name);
		if (user == null) {
			user = new User();
			user.setName(name);
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(user);
			getEntityManager().getTransaction().commit();
		}
		return user;
	}
	
	@Override
	public List<User> getFollowedUsersByFollowerName(String name) {
		User user = retrieveUser(name);
		TypedQuery<User> query = getEntityManager().createNamedQuery("User.findFollowedByFollowerName", User.class).setParameter("userId", user.getUserId());
		List<User> users = query.getResultList();
		return users;
	}
	
	@Override
	public List<User> getFollowersByUserName(String name) {
		User user = retrieveUser(name);
		TypedQuery<User> query = getEntityManager().createNamedQuery("User.findFollowersByUserName", User.class).setParameter("userId", user.getUserId());
		List<User> users = query.getResultList();
		return users;
	}

	@Override
	public void follow(String followerName, String followedName) {
		if (!userFollowsUser(followerName, followedName)) {
			User follower = retrieveUser(followerName);
			User followed = retrieveUser(followedName);
			
			getEntityManager().getTransaction().begin();
			
			follower.getFollowed().add(followed);
			followed.getFollowers().add(follower);
			getEntityManager().merge(follower);
			getEntityManager().merge(followed);
			
			getEntityManager().getTransaction().commit();
		}
	}
	
	/**
	 * Checks if a user already follows another user
	 * @param followerName the follower
	 * @param followedName the followed
	 * @return true if the passed-in follower already follows the other user
	 */
	private boolean userFollowsUser(String followerName, String followedName) {
		User follower = retrieveUser(followerName);

		long followedCount = 0;
		if (follower != null) {
			followedCount = follower.getFollowed().stream()
				.filter(user -> StringUtils.equals(followedName, user.getName()))
				.count();
		}
		return followedCount > 0;
	}
	
	@Override
	public void createMessage(String name, String text) {
		User user = retrieveUser(name);

		Message message = new Message();
		message.setUser(user);
		message.setMessage(text);
		message.setInsertts(new Date());
		user.getMessages().add(message);
		
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(user);
		getEntityManager().getTransaction().commit();
	}
	
	@Override
	public List<Message> getMessagesByUserName(String name) {
		TypedQuery<Message> query = getEntityManager().createNamedQuery("Message.findByUserName", Message.class).setParameter("name", name);
		List<Message> messages = query.getResultList();
		return messages;
	}
	
	@Override
	public List<Message> getAllFollowedUsersMessages(String name) {
		User user = retrieveUser(name);
		List<Message> messages = new ArrayList<>();
		if (user != null) {
			List<String> userNames = new ArrayList<>();
			userNames.add(name);
			
			List<String> followedNames = user.getFollowed().stream()
					.map(u -> u.getName())
					.collect(Collectors.toList());
			userNames.addAll(followedNames);
			
			TypedQuery<Message> query = getEntityManager().createNamedQuery("Message.findByUserList", Message.class).setParameter("names", userNames);
			messages = query.getResultList();
		}
		return messages;
	}
	
	/** 
	 * Gets a user by name
	 * @param name the user name
	 * @return the User if found; null otherwise
	 */
	private User getUserByName(String name) {
		TypedQuery<User> query = getEntityManager().createNamedQuery("User.findUsersByName", User.class).setParameter("name", name);
		List<User> users = query.getResultList();
		User user = users.isEmpty() ? null : users.get(0);
		return user;
	}
	
	@Override
	public void deleteAllMessages() {
		getEntityManager().getTransaction().begin();
		getEntityManager().createQuery("DELETE FROM Message m").executeUpdate();
		getEntityManager().getTransaction().commit();
	}

	@Override
	public void deleteAllUsers() {
		getEntityManager().getTransaction().begin();
		getEntityManager().createQuery("DELETE FROM User u").executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
	@Override
	public void setPersistenceUnitName(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}
	
	/**
	 * Gets the entity manager factory, creating if it is null
	 * @return the entity manager factory
	 */
	private EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		return factory;
	}
	
	/**
	 * Gets the entity manager, creating if it is null
	 * @return the entity manager
	 */
	private EntityManager getEntityManager() {
		if (em == null) {
			em = getEntityManagerFactory().createEntityManager();
		}
		return em;
	}

	@Override
	public List<User> getFriendsList(String userName) {
		User user = retrieveUser(userName);
		if (user != null) {
			List<User> friendsList = user.getFollowed();
			return friendsList;
		}
		return null;
	}

}

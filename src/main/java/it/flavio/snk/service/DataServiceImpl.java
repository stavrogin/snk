package it.flavio.snk.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.flavio.snk.database.model.Message;
import it.flavio.snk.database.model.User;

public class DataServiceImpl implements DataService {

	private static EntityManagerFactory factory;
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "users";

	@Override
	public void createUser(String name) {
		if (!isUserStored(name)) {
			User user = new User();
			user.setName(name);
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(user);
			getEntityManager().getTransaction().commit();
		}
	}
	
	/**
	 * Checks if the user name already exists
	 * @param name the user name
	 * @return true if the user already exists, false otherwise
	 */
	private boolean isUserStored(String name) {
		return getUserByName(name) != null;
	}
	
	@Override
	public User getUserByName(String name) {
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.name = :name", User.class).setParameter("name", name);
		List<User> users = query.getResultList();
		User user = users.isEmpty() ? null : users.get(0);
		return user;
	}
	
	@Override
	public List<Message> getUserMessages(String name) {
		TypedQuery<Message> query = getEntityManager().createQuery("SELECT m FROM User u JOIN u.messages m WHERE u.name = :name", Message.class).setParameter("name", name);
		List<Message> messages = query.getResultList();
		return messages;
	}
	
	@Override
	public List<User> getFollowedUsersByFollowerName(String name) {
		User user = getUserByName(name);
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u JOIN u.followed f WHERE f.userId = :userId", User.class).setParameter("userId", user.getUserId());
		List<User> users = query.getResultList();
		users.forEach(u -> System.out.println(u.getName()));
		return users;
	}
	
	@Override
	public List<User> getFollowersByUserName(String name) {
		User user = getUserByName(name);
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u JOIN u.followers f WHERE f.userId = :userId", User.class).setParameter("userId", user.getUserId());
		List<User> users = query.getResultList();
		users.forEach(u -> System.out.println(u.getName()));
		return users;
	}
	
	public void follow(String follower, String followed) {
		
	}

	/**
	 * Gets the entity manager factory, creating if it is null
	 * @return the entity manager factory
	 */
	private EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
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
	
}

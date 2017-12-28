package it.flavio.snk.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.flavio.snk.database.model.Message;
import it.flavio.snk.database.model.User;

public class UserDataService {

	private static EntityManagerFactory factory;
	private EntityManager em;
	private static final String PERSISTENCE_UNIT_NAME = "users";

	private EntityManager getEntityManager() {
		if (em == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();
		}
		return em;
	}

	public void createUser(String name) {
		User user = new User();
		user.setName(name);
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(user);
		getEntityManager().getTransaction().commit();
	}
	
	public User getUserByName(String name) {
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.name = :name", User.class).setParameter("name", name);
		List<User> users = query.getResultList();
		User user = users.isEmpty() ? null : users.get(0);
		return user;
	}
	
	public void follow(String follower, String followed) {
		
	}
	
	public List<Message> getUserMessages(String name) {
		TypedQuery<Message> query = getEntityManager().createQuery("SELECT m FROM User u JOIN u.messages m WHERE u.name = :name", Message.class).setParameter("name", name);
		List<Message> messages = query.getResultList();
		return messages;
	}
	
	public List<User> getFollowedUsersByFollowerName(String name) {
		User user = getUserByName(name);
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u JOIN u.followed f WHERE f.userId = :userId", User.class).setParameter("userId", user.getUserId());
		List<User> users = query.getResultList();
		users.forEach(u -> System.out.println(u.getName()));
		return users;
	}
	
	public List<User> getFollowersByUserName(String name) {
		User user = getUserByName(name);
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u JOIN u.followers f WHERE f.userId = :userId", User.class).setParameter("userId", user.getUserId());
		List<User> users = query.getResultList();
		users.forEach(u -> System.out.println(u.getName()));
		return users;
	}

	public List<User> getAllUsers() {
		TypedQuery<User> q = getEntityManager().createQuery("SELECT u FROM User u", User.class);
		List<User> users = q.getResultList();
		users.stream().map(u -> u.getName()).forEach(u -> System.out.println(u));
		return null;
	}

	public List<User> getAllFollowers() {
		TypedQuery<User> q = getEntityManager().createQuery("SELECT u FROM User u WHERE u.userId = 1", User.class);
		List<User> users = q.getResultList();
		users.stream().map(u -> u.getFollowers()).forEach(u -> u.forEach(fw -> System.out.println(fw.getName())));
		return null;
	}

	public List<User> getAllFollowed() {
		TypedQuery<User> q = getEntityManager().createQuery("SELECT u FROM User u WHERE u.userId = 1", User.class);
		List<User> users = q.getResultList();
		users.stream().map(u -> u.getFollowed()).forEach(u -> u.forEach(fw -> System.out.println(fw.getName())));
		return null;
	}

}

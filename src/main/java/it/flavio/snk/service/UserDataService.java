package it.flavio.snk.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	public boolean isUserStored(String name) {
		TypedQuery<User> query = getEntityManager().createQuery("SELECT u FROM User u WHERE u.name = :name", User.class).setParameter("name", name);
		List<User> users = query.getResultList();
		if (users.isEmpty()) {
			return false;
		}
		return true;
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
		Query q = getEntityManager().createQuery("SELECT u FROM User u WHERE u.userId = 1");
		List<User> users = q.getResultList();
		users.stream().map(u -> u.getFollowed()).forEach(u -> u.forEach(fw -> System.out.println(fw.getName())));
		return null;
	}

}

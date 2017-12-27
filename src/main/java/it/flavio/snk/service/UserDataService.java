package it.flavio.snk.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	
	public List<User> getAllUsers() {
		Query q = getEntityManager().createQuery("SELECT u FROM User u");
		List<User> users = q.getResultList();
		users.stream().map(u -> u.getName()).forEach(u -> System.out.println(u));
		return null;
	}
	
	public List<User> getAllFollowers() {
		Query q = getEntityManager().createQuery("SELECT u FROM User u WHERE u.userId = 1");
		List<User> users = q.getResultList();
		users.stream().map(u -> u.getUsers1()).forEach(u -> u.forEach(fw -> System.out.println(fw.getName())));
		return null;
	}
	
	public List<User> getAllFollowed() {
		Query q = getEntityManager().createQuery("SELECT u FROM User u WHERE u.userId = 1");
		List<User> users = q.getResultList();
		users.stream().map(u -> u.getUsers2()).forEach(u -> u.forEach(fw -> System.out.println(fw.getName())));
		return null;
	}

}

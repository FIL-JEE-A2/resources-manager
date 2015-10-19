package fr.mines.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import fr.mines.entitites.User;
import fr.mines.persistence.JPAUtils;

/**
 * User entity dao.
 * TODO : thread safe
 */
public class UserDao {
	private EntityManager entityManager;

	private UserDao() {
		entityManager = JPAUtils.createEntityManager();
	}

	private static UserDao instance;

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public User create(User user) {
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.persist(user);
		transaction.commit();
		return user;
	}

	public User update(Long id, User user) {
		User previousUser = this.entityManager.find(User.class, id);
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		user.copyIn(previousUser);
		transaction.commit();
		return user;
	}

	public User getByMail(String mail) {
		Query query = this.entityManager.createQuery("SELECT u FROM User u WHERE u.mail=:mail");
		query.setParameter("mail", mail);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public User getByLogin(String login) {
		Query query = this.entityManager.createQuery("SELECT u FROM User u WHERE u.login=:login");
		query.setParameter("login", login);
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		Query selectQuery = this.entityManager.createQuery("SELECT u FROM User u");
		return selectQuery.getResultList();
	}

	public User get(Long id) {
		return this.entityManager.find(User.class, id);
	}

	public User remove(Long id) {
		User previousUser = this.entityManager.find(User.class, id);
		EntityTransaction transaction = this.entityManager.getTransaction();
		transaction.begin();
		this.entityManager.remove(previousUser);
		transaction.commit();
		return previousUser;
	}
}

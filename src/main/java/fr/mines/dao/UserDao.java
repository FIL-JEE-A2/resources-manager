package fr.mines.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import fr.mines.entitites.User;

/**
 * User entity dao.
 * TODO : thread safe
 */
public class UserDao extends AbstractDao<User, Long> {

	private UserDao() {
		super(User.class);
	}

	private static UserDao instance;

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public User getByMail(String mail) {
		Query query = this.getEntityManager().createQuery("SELECT u FROM User u WHERE u.mail=:mail");
		query.setParameter("mail", mail);
		try {
			return refresh((User) query.getSingleResult());
		} catch (NoResultException nre) {
			return null;
		}
	}

	public User getByLogin(String login) {
		Query query = this.getEntityManager().createQuery("SELECT u FROM User u WHERE u.login=:login");
		query.setParameter("login", login);
		try {
			return refresh((User) query.getSingleResult());
		} catch (NoResultException nre) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllImpl() {
		Query selectQuery = this.getEntityManager().createQuery("SELECT u FROM User u");
		return selectQuery.getResultList();
	}

}

package fr.mines.service;

import java.util.List;

import fr.mines.dao.UserDao;
import fr.mines.entitites.User;

/**
 * Service to manage application users
 */
public class UserService {
	private static UserService instance;
	private UserDao userDao;

	private UserService() {
		userDao = UserDao.getInstance();
	}

	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	public User create(User user) throws ServiceExecutionException {
		checkUserConstraint(user, null);
		return this.userDao.create(user);
	}

	public User get(Long id) throws ServiceExecutionException {
		return this.userDao.get(id);
	}

	public User remove(Long id) throws ServiceExecutionException {
		return this.userDao.remove(id);
	}

	public User checkPassword(String login, String password) {
		User user = this.userDao.getByLogin(login);
		if (user != null) {
			String userpass = user.getPassword();
			if (userpass != null && userpass.equals(password)) {
				return user;
			}
		}
		return user;
	}

	public User update(Long id, User toUpdate) throws ServiceExecutionException {
		checkUserConstraint(toUpdate, id);
		return this.userDao.update(id, toUpdate);
	}

	private void checkUserConstraint(User user, Long id) throws ServiceExecutionException {
		//TODO : check empty on login,mail,password
		User byLogin = this.userDao.getByLogin(user.getLogin());
		if (byLogin != null && (id == null || !id.equals(byLogin.getId()))) {
			throw new ServiceExecutionException("Il existe déjà un utilisateur avec le login \"" + user.getLogin() + "\"");
		}
		User byMail = this.userDao.getByMail(user.getMail());
		if (byMail != null && (id == null || !id.equals(byMail.getId()))) {
			throw new ServiceExecutionException("Il existe déjà un utilisateur avec le mail \"" + user.getMail() + "\"");
		}
	}

	public List<User> getAll() throws ServiceExecutionException {
		return this.userDao.getAll();
	}
}

package fr.mines.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.dao.ResourceDao;
import fr.mines.dao.UserDao;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;
import fr.mines.entitites.User;

/**
 * Service to manage application users
 */
public class UserService extends AbstractService<User, Long, UserDao> {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	private static UserService instance;

	private UserService() {
		super(UserDao.getInstance());
	}

	public static UserService getInstance() {
		return instance == null ? instance = new UserService() : instance;
	}

	@Override
	protected void checkRemove(Long userID) throws ServiceExecutionException {
		checkUserReservation(userID);
		checkResourceManager(userID);
	}

	private void checkUserReservation(Long userID) throws ServiceExecutionException {
		User user = get(userID);
		List<Reservation> reservations = user.getReservations();
		if (!reservations.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			msg.append("Impossible de supprimer l'utilisateur ").append(user.getFullname())
					.append(", il existe des réservations associées à l'utilisateur : ");
			msg.append("<ul>");
			for (Reservation reservation : reservations) {
				msg.append("<li>").append(reservation.getResource().getName()).append(" du ").append(reservation.getReservationStartLabel())
						.append(" au ").append(reservation.getReservationStopLabel()).append("</li>");
			}
			msg.append("</ul>");
			throw new ServiceExecutionException(msg.toString());
		}
	}

	private void checkResourceManager(Long userID) throws ServiceExecutionException {
		List<Resource> resourceByManager = ResourceDao.getInstance().getResourceByManager(userID);
		if (!resourceByManager.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			msg.append("L'utilisateur ne peut pas être supprimé car il est le responsable des ressources :");
			msg.append("<ul>");
			for (Resource resource : resourceByManager) {
				msg.append("<li>").append(resource.getName()).append("</li>");
			}
			msg.append("</ul>");
			throw new ServiceExecutionException(msg.toString());
		}
	}

	public User checkPassword(String login, String password) {
		User user = dao.getByLogin(login);
		if (user != null) {
			String userpass = user.getPassword();
			if (userpass != null && userpass.equals(password))
				return user;
		}
		return user;
	}

	@Override
	protected void checkCreate(User toCreate) throws ServiceExecutionException {
		if (StringUtils.isEmpty(toCreate.getLogin()))
			throw new ServiceExecutionException("Le login de l'utilisateur ne peut pas �tre vide");
		if (StringUtils.isEmpty(toCreate.getPassword()))
			throw new ServiceExecutionException("Le mot de passe de l'utilisateur ne peut pas �tre vide");
		if (dao.getByLogin(toCreate.getLogin()) != null)
			throw new ServiceExecutionException("Il existe d�j� un utilisateur avec le login \"" + toCreate.getLogin() + "\"");
		if (dao.getByMail(toCreate.getMail()) != null)
			throw new ServiceExecutionException("Il existe d�j� un utilisateur avec le mail \"" + toCreate.getMail() + "\"");
	}

	@Override
	protected void checkUpdate(User toUpdate, Long id) throws ServiceExecutionException {
		if (StringUtils.isEmpty(toUpdate.getLogin()))
			throw new ServiceExecutionException("Le login de l'utilisateur ne peut pas �tre vide");
		if (StringUtils.isEmpty(toUpdate.getPassword()))
			throw new ServiceExecutionException("Le mot de passe de l'utilisateur ne peut pas �tre vide");
		User byLogin = dao.getByLogin(toUpdate.getLogin());
		if (byLogin != null && !id.equals(byLogin.getId()))
			throw new ServiceExecutionException("Il existe déjà un utilisateur avec le login \"" + toUpdate.getLogin() + "\"");
		User byMail = dao.getByMail(toUpdate.getMail());
		if (byMail != null && !id.equals(byMail.getId()))
			throw new ServiceExecutionException("Il existe déjà un utilisateur avec le mail \"" + toUpdate.getMail() + "\"");
	}
}

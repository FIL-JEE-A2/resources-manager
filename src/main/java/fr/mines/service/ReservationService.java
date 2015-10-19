package fr.mines.service;

import java.util.List;

import fr.mines.dao.ReservationDao;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;
import fr.mines.entitites.User;

public class ReservationService {

	private static ReservationService instance;

	private ReservationDao reservationDao;

	private ReservationService() {
		reservationDao = ReservationDao.getInstance();
	}
	
	public Reservation create(Reservation reservation, Long userID, Long resourceID) throws ServiceExecutionException {
		User user = UserService.getInstance().get(userID);
		reservation.setUser(user);
		Resource resource = ResourceService.getInstance().get(resourceID);
		reservation.setResource(resource);
		return this.reservationDao.create(reservation);
	}

	public Reservation update(Long id, Reservation toUpdate, Long userId, Long resourceId) throws ServiceExecutionException {
		User user = UserService.getInstance().get(userId);
		toUpdate.setUser(user);
		Resource resource = ResourceService.getInstance().get(resourceId);
		toUpdate.setResource(resource);
		return this.reservationDao.update(id, toUpdate);
	}

	public Reservation remove(Long id) throws ServiceExecutionException {
		return this.reservationDao.remove(id);
	}

	public static ReservationService getInstance() {
		return instance == null ? instance = new ReservationService() : instance;
	}

	public Reservation get(Long id) throws ServiceExecutionException {
		return this.reservationDao.get(id);
	}

	public List<Reservation> getAll() throws ServiceExecutionException {
		return this.reservationDao.getAll();
	}
}

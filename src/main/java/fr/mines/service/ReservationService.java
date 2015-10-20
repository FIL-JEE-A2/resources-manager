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
		checkReservationCreate(reservation);
		return this.reservationDao.create(reservation);
	}

	private void checkReservationCreate(Reservation reservation) throws ServiceExecutionException {
		checkReservationDate(reservation);
		checkReservationConflict(reservation);
	}

	private void checkReservationDate(Reservation reservation) throws ServiceExecutionException {
		if(!reservation.getReservationStart().before(reservation.getReservationStop())){
			throw new ServiceExecutionException("La date du début de réservation doit être avant la date de fin de réservation");
		}
	}

	private void checkReservationConflict(Reservation reservation) throws ServiceExecutionException {
		List<Reservation> conflictReservations = ReservationDao.getInstance().getReservationByDateAndResource(reservation.getResource().getId(),
				reservation.getReservationStart(), reservation.getReservationStop());
		if (!conflictReservations.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			msg.append("La réservation ne peut pas être effectuée, car la ressource est déjà réservée sur ce créneau :");
			msg.append("<ul>");
			for (Reservation conflictRes : conflictReservations) {
				msg.append("<li>").append("Du ").append(conflictRes.getReservationStartLabel()).append(" au ")
						.append(conflictRes.getReservationStopLabel()).append(" (par ").append(conflictRes.getUser().getFullname()).append(" )")
						.append("</li>");
			}
			msg.append("</ul>");
			throw new ServiceExecutionException(msg.toString());
		}
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

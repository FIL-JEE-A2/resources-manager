package fr.mines.dao;

import java.util.List;

import javax.persistence.Query;

import fr.mines.entitites.Reservation;

public class ReservationDao extends AbstractDao<Reservation, Long> {
	private static ReservationDao instance;

	private ReservationDao() {
		super(Reservation.class);
	}

	public static ReservationDao getInstance() {
		return instance == null ? instance = new ReservationDao() : instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getAll() {
		Query selectQuery = this.getEntityManager().createQuery("SELECT r FROM Reservation r");
		return selectQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationByUser(Long userID) {
		Query selectQuery = this.getEntityManager().createQuery("SELECT r FROM Reservation r JOIN r.user m WHERE m.id=:userID");
		selectQuery.setParameter("userID", userID);
		return selectQuery.getResultList();
	}
}

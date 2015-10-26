package fr.mines.dao;

import java.util.Date;
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
	public List<Reservation> getAllImpl() {
		Query selectQuery = entityManager().createQuery("SELECT r FROM Reservation r");
		return selectQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationByUser(Long userID) {
		Query selectQuery = entityManager().createQuery(
				"SELECT r FROM Reservation r JOIN r.user m WHERE m.id=:userID");
		selectQuery.setParameter("userID", userID);
		return selectQuery.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationByUser(Long userId, int limit)
	{
		Query selectQuery = entityManager().createQuery(
				"SELECT r FROM Reservation r JOIN r.user m WHERE m.id=:userID ORDER BY r.reservationStart DESC")
				.setParameter("userID", userId)
				.setMaxResults(limit);
		return selectQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationByDateAndResource(Long resourceID, Date startDate, Date stopDate) {
		Query selectQuery = entityManager()
				.createQuery("SELECT r FROM Reservation r JOIN r.resource res WHERE "//
						+ "res.id=:resourceID AND "//
						+ "((r.reservationStart >= :startDate AND r.reservationStart <= :stopDate)"//
						+ "OR r.reservationStop <= :stopDate)");
		selectQuery.setParameter("resourceID", resourceID);
		selectQuery.setParameter("startDate", startDate);
		selectQuery.setParameter("stopDate", stopDate);
		return selectQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationByDateAndResourceExcludePrevious(Long previousReservationId, Long resourceID, Date startDate,
			Date stopDate) {
		Query selectQuery = entityManager()
				.createQuery("SELECT r FROM Reservation r JOIN r.resource res WHERE "//
						+ "res.id=:resourceID AND "//
						+ "((r.reservationStart >= :startDate AND r.reservationStart <= :stopDate)"//
						+ "OR r.reservationStop <= :stopDate) AND " + "r.id != :previousID");
		selectQuery.setParameter("resourceID", resourceID);
		selectQuery.setParameter("startDate", startDate);
		selectQuery.setParameter("stopDate", stopDate);
		selectQuery.setParameter("previousID", previousReservationId);

		return selectQuery.getResultList();
	}

	public Long getNbReservationByUser(Long id)
	{
		return (Long) entityManager().createQuery(
				"select count(r) from Reservation r join r.user m where " +
						"m.id = :userId and " +
						"r.reservationStop >= current_date()")
				.setParameter("userId", id)
				.getSingleResult();
	}
}

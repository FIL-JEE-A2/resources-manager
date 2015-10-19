package fr.mines.service;

import fr.mines.dao.ReservationDao;

public class ReservationService {

	private static ReservationService instance;

	private ReservationDao reservationDao;

	private ReservationService() {
		reservationDao = ReservationDao.getInstance();
	}

	public static ReservationService getInstance() {
		return instance == null ? instance = new ReservationService() : instance;
	}
}

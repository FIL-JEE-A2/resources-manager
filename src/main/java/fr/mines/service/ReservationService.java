package fr.mines.service;

import java.util.Date;
import java.util.List;

import fr.mines.dao.ReservationDao;
import fr.mines.entitites.Reservation;

public class ReservationService extends AbstractService<Reservation, Long, ReservationDao>{

	private static ReservationService instance;

	public static ReservationService getInstance()
	{
		return instance == null ? instance = new ReservationService() : instance;
	}

	private ReservationService() {
		super(ReservationDao.getInstance());
	}

	@Override
	protected void checkCreate(Reservation toCreate) throws ServiceExecutionException
	{
		checkReservationDate(toCreate);
		checkReservationConflict(toCreate);
	}

	@Override
	protected void checkUpdate(Reservation toUpdate, Long idToUpdate) throws ServiceExecutionException
	{
		checkReservationDate(toUpdate);
		checkReservationConflictWithoutPrevious(toUpdate, idToUpdate);
	}

	private void checkReservationConflictWithoutPrevious(Reservation reservation, Long previousID) throws ServiceExecutionException {
		List<Reservation> conflictReservations = dao.getReservationByDateAndResourceExcludePrevious(previousID,
				reservation.getResource().getId(), reservation.getReservationStart(), reservation.getReservationStop());
		createConflictListException(conflictReservations);
	}

	private void checkReservationDate(Reservation reservation) throws ServiceExecutionException {
		if (!reservation.getReservationStart().before(reservation.getReservationStop())) {
			throw new ServiceExecutionException("La date du d�but de r�servation doit �tre avant la date de fin de r�servation");
		}
	}

	private void checkReservationConflict(Reservation reservation) throws ServiceExecutionException {
		List<Reservation> conflictReservations = ReservationDao.getInstance().getReservationByDateAndResource(reservation.getResource().getId(),
				reservation.getReservationStart(), reservation.getReservationStop());
		createConflictListException(conflictReservations);
	}

	private void createConflictListException(List<Reservation> conflictReservations) throws ServiceExecutionException {
		if (!conflictReservations.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			msg.append("La r�servation ne peut pas �tre effectu�e, car la ressource est d�j� r�serv�e sur ce cr�neau :");
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

	public List<Reservation> getByUser(Long userId)
	{
		return dao.getReservationByUser(userId);
	}

	public List<Reservation> getByUser(Long userId, int limit)
	{
		return dao.getReservationByUser(userId, limit);
	}

	public Long getNbByUser(Long id)
	{
		return dao.getNbReservationByUser(id);
	}
	
	public List<Reservation> getReservationWithFilter(String resource, String dateStartOperator, Date dateStart, String dateStopOperator, Date dateStop, String userFirstName, String userLastName) {
		if(resource==null && dateStart==null && dateStop==null && userFirstName==null && userLastName==null) {
			return dao.getAll();
		} else {
			return dao.getReservationWithFilter(resource, dateStartOperator, dateStart, dateStopOperator, dateStop, userFirstName, userLastName);
		}
	}
	
}

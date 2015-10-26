package fr.mines.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import fr.mines.dao.ResourceDao;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;

public class ResourceService extends AbstractService<Resource, Long, ResourceDao> {
	private static ResourceService instance;

	private ResourceService() {
		super(ResourceDao.getInstance());
	}

	public static ResourceService getInstance() {
		return instance == null ? instance = new ResourceService() : instance;
	}

	protected void checkRemove(Long resourceID) throws ServiceExecutionException {
		Resource resource = this.get(resourceID);
		Collection<Reservation> reservations = resource.getReservations();
		if (!reservations.isEmpty()) {
			StringBuilder msg = new StringBuilder();
			msg.append("Impossible de supprimer la ressource ").append(resource.getName())
					.append(", il existe des réservations associées à la ressource : ");
			msg.append("<ul>");
			for (Reservation reservation : reservations) {
				msg.append("<li>").append("Du ").append(reservation.getReservationStartLabel()).append(" au ")
						.append(reservation.getReservationStopLabel()).append(" par ").append(reservation.getUser().getFullname()).append("</li>");
			}
			msg.append("</ul>");
			throw new ServiceExecutionException(msg.toString());
		}
	}

	public List<Resource> getFreeResourceByDateAndResourceType(Long resourceTypeId, Date startDate, Date stopDate) throws ServiceExecutionException {
		checkReservationDate(startDate, stopDate);
		return this.dao.getFreeResourceByDateAndResourceType(resourceTypeId, startDate, stopDate);
	}

	private void checkReservationDate(Date start, Date stop) throws ServiceExecutionException {
		if (!start.before(stop)) {
			throw new ServiceExecutionException("La date du début de r�servation doit être avant la date de fin de réservation");
		}
	}
}
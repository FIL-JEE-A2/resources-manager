package fr.mines.service;

import java.util.Collection;

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
}
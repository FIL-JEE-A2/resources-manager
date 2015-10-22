package fr.mines.controller.actions.reservation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;
import fr.mines.service.ServiceExecutionException;

public class AddReservationAction extends AbstractFrontAction {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddReservationAction.class);

	@Override
	public String getID() {
		return "add-reservation";
	}

	@Override
	public boolean isTemplateView() {
		return true;
	}

	@Override
	public ActionSecurity getSecurityLevel() {
		return ActionSecurity.BASIC;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.RESERVATION;
	}

	@Override
	public String handle(HttpServletRequestDecorator request, HttpServletResponse response) throws Exception {
		if (request.isSet("selectedResource")) {

			Long userID = request.connectedUser().getId();
			Long resourceID = Long.parseLong(request.param("selectedResource"));

			Date reservationStart = Reservation.FIELD_DATE_FORMAT.parse(request.param("reservationStart"));
			Date reservationStop = Reservation.FIELD_DATE_FORMAT.parse(request.param("reservationStop"));

			Reservation reservation = new Reservation(reservationStart, reservationStop);
			reservation.setUser(userService.get(userID));
			reservation.setResource(resourceService.get(resourceID));
			request.attr("previousReservation", reservation);
			LOGGER.info("Reservation {}", reservation);
			try {
				reservationService.create(reservation);
				request.attr("reservationAdded", true);
				request.attr("reservationAddedResourceName", reservation.getResource().getName());
				request.attr("reservationAddedStart", reservation.getReservationStartLabel());
				request.attr("reservationAddedStop", reservation.getReservationStopLabel());
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while adding the reservation", e);
				request.attr("reservationAddedResourceName", reservation.getResource() != null ? reservation.getResource().getName() : "inconnue");
				request.attr("reservationAddError", true);
				request.attr("reservationAddErrorMessage", e.getMessage());
			}
		}
		List<Resource> resourceList = resourceService.getAll();
		request.attr("resourceList", resourceList);
		return "/jsp/pages/reservations/add-modify-reservation.jsp";
	}

}

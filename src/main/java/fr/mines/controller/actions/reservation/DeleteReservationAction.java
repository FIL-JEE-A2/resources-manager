package fr.mines.controller.actions.reservation;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.Reservation;
import fr.mines.service.ServiceExecutionException;

public class DeleteReservationAction extends AbstractFrontAction {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteReservationAction.class);

	@Override
	public String getID() {
		return "delete-reservation";
	}

	@Override
	public boolean isTemplateView() {
		return true;
	}

	@Override
	public boolean isSecured() {
		return true;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.RESERVATION;
	}

	@Override
	public String handle(HttpServletRequestDecorator request, HttpServletResponse response) throws Exception {
		if (request.isSet("id")) {
			Long reservationID = Long.parseLong(request.param("id"));
			LOGGER.info("Will ask for the reservation remove \"{}\"", request.param("id"));
			request.attr("reservationToDelete", reservationService.get(reservationID));
			if (request.isSet("delete")) {
				try {
					Reservation removedReservation = reservationService.remove(reservationID);
					request.attr("reservationDeleted", true);
					request.attr("reservationDeletedResourceName", removedReservation.getResource().getName());
					request.attr("reservationDeletedStart", removedReservation.getReservationStartLabel());
					request.attr("reservationDeletedStop", removedReservation.getReservationStopLabel());
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Reservation can't be removed", e);
					request.attr("reservationDeleteError", true);
					request.attr("reservationDeleteErrorMessage", e.getMessage());
				}
			}
		}
		return "/jsp/pages/reservations/delete-reservation.jsp";
	}
}

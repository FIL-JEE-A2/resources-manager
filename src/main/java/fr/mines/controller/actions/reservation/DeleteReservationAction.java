package fr.mines.controller.actions.reservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;
import fr.mines.service.ReservationService;
import fr.mines.service.ResourceService;
import fr.mines.service.ServiceExecutionException;

public class DeleteReservationAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteReservationAction.class);

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("id") != null) {
			Long reservationID = Long.parseLong(request.getParameter("id"));
			LOGGER.info("Will ask for the reservation remove \"{}\"", request.getParameter("id"));
			Reservation reservation = ReservationService.getInstance().get(reservationID);
			request.setAttribute("reservationToDelete", reservation);
			if (request.getParameter("delete") != null) {
				try {
					Reservation removedReservation = ReservationService.getInstance().remove(reservationID);
					request.setAttribute("reservationDeleted", true);
					request.setAttribute("reservationDeletedResourceName", removedReservation.getResource().getName());
					request.setAttribute("reservationDeletedStart", removedReservation.getReservationStart());
					request.setAttribute("reservationDeletedStop", removedReservation.getReservationStop());
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Reservation can't be removed", e);
					request.setAttribute("reservationDeleteError", true);
					request.setAttribute("reservationDeleteErrorMessage", e.getMessage());
				}
			}
		}
		return "/jsp/pages/resources/delete-reservation.jsp";
	}

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

}

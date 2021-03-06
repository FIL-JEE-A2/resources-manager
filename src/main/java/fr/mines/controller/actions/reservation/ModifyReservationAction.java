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

public class ModifyReservationAction extends AbstractFrontAction{
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyReservationAction.class);

	public String handle(HttpServletRequestDecorator request, HttpServletResponse response) throws Exception {
		if (request.isSet("id")) {
			Long reservationID = Long.parseLong(request.param("id"));
			request.attr("previousReservation", reservationService.get(reservationID));
			if (request.isSet("selectedResource")) {
				Long userID = request.connectedUser().getId();
				Long resourceID = Long.parseLong(request.param("selectedResource"));
				//Get modified version
				Date reservationStart = Reservation.FIELD_DATE_FORMAT.parse(request.param("reservationStart"));
				Date reservationStop = Reservation.FIELD_DATE_FORMAT.parse(request.param("reservationStop"));
				Reservation reservationToUpdate = new Reservation(reservationStart, reservationStop);
				reservationToUpdate.setUser(userService.get(userID));
				reservationToUpdate.setResource(resourceService.get(resourceID));
				//Try to update
				try {
					reservationService.update(reservationID, reservationToUpdate);
					request.attr("reservationModified", true);
					request.attr("reservationModifiedResourceName",
							reservationToUpdate.getResource() != null ? reservationToUpdate.getResource().getName() : "inconnue");
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Problem while modify the reservation", e);
					request.attr("reservationModifiedResourceName",
							reservationToUpdate.getResource() != null ? reservationToUpdate.getResource().getName() : "inconnue");
					request.attr("reservationModifyError", true);
					request.attr("reservationModifyErrorMessage", e.getMessage());
				}
			}
		}
		List<Resource> resourceList = resourceService.getAll();
		request.attr("resourceList", resourceList);
		return "/jsp/pages/reservations/add-modify-reservation.jsp";
	}

	@Override
	public String getID() {
		return "modify-reservation";
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
}
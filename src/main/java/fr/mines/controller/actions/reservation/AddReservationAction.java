package fr.mines.controller.actions.reservation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;
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
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		//List resource type
		List<ResourceType> resourceTypeList = resourceTypeService.getAll();
		rq.attr("resourceTypeList", resourceTypeList);
		//List resource between two dates
		if (rq.isSet("reservationStart")) {
			Long resourceTypeID = Long.parseLong(rq.param("selectedResourceType"));
			String reservationStartString = rq.param("reservationStart");
			String reservationStopString = rq.param("reservationStop");
			if (StringUtils.isBlank(reservationStartString) || StringUtils.isBlank(reservationStopString)) {
				rq.attr("reservationResearchError", true);
				rq.attr("reservationResearchErrorMessage", "Les dates saisies sont invalides");
			}
			Date reservationStart = Reservation.FIELD_DATE_FORMAT.parse(reservationStartString);
			Date reservationStop = Reservation.FIELD_DATE_FORMAT.parse(reservationStopString);

			//Set filter param
			rq.attr("selectedResourceType", resourceTypeID);
			rq.attr("reservationStartMillis", reservationStart.getTime());
			rq.attr("reservationStopMillis", reservationStop.getTime());

			//Create reservation if possible
			if (rq.isSet("reservedResource")) {
				Long resourceID = Long.parseLong(rq.param("reservedResource"));
				Reservation reservation = new Reservation(reservationStart, reservationStop);
				reservation.setUser(userService.get(rq.connectedUser().getId()));
				reservation.setResource(resourceService.get(resourceID));
				try {
					reservationService.create(reservation);
					rq.attr("reservationAdded", true);
					rq.attr("reservationAddedResourceName", reservation.getResource().getName());
					rq.attr("reservationAddedStart", reservation.getReservationStartLabel());
					rq.attr("reservationAddedStop", reservation.getReservationStopLabel());
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Problem while adding the reservation", e);
					rq.attr("reservationAddedResourceName", reservation.getResource() != null ? reservation.getResource().getName() : "inconnue");
					rq.attr("reservationAddError", true);
					rq.attr("reservationAddErrorMessage", e.getMessage());
				}
			}

			//Search for every free resources
			try {
				List<Resource> all = resourceService.getFreeResourceByDateAndResourceType(resourceTypeID, reservationStart, reservationStop);
				rq.attr("freeResourceList", all);
				rq.attr("resourceSearched", true);
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while searching the reservation", e);
				rq.attr("reservationResearchError", true);
				rq.attr("reservationResearchErrorMessage", e.getMessage());

			}
		}
		return "/jsp/pages/reservations/add-modify-reservation.jsp";
	}

}

package fr.mines.controller.actions.reservation;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;
import fr.mines.entitites.User;
import fr.mines.service.ReservationService;
import fr.mines.service.ResourceService;
import fr.mines.service.ResourceTypeService;
import fr.mines.service.ServiceExecutionException;
import fr.mines.service.UserService;

public class ModifyReservationAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyReservationAction.class);

	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("id") != null) {
			request.setAttribute("modifyReservation", true);
			Long reservationID = Long.parseLong(request.getParameter("id"));
			//Set the modified reservation
			LOGGER.info("Modify the reservation \"{}\"", request.getParameter("id"));
			Reservation reservation = ReservationService.getInstance().get(reservationID);
			request.setAttribute("previousReservation", reservation);
			//Update in DB
			if (request.getParameter("reservationStart") != null && request.getParameter("reservationStop") != null
					&& request.getParameter("resource") != null) {
				Long userID = Long.parseLong(request.getParameter("user"));
				Long resourceID = Long.parseLong(request.getParameter("resource"));
				LOGGER.info("Will update the reservation \"{}\"", request.getParameter("id"));

				Date reservationStart = new Date(request.getParameter("reservationStart"));
				Date reservationStop = new Date(request.getParameter("reservationStop"));

				Reservation updateReservation = null;//new Reservation(reservationStart, reservationStop, (User) request.getAttribute("user"),
						//(Resource) request.getAttribute("resource"));
				try {
					ReservationService.getInstance().update(reservationID, updateReservation, userID, resourceID);
					request.setAttribute("reservationModified", true);
					request.setAttribute("reservationModifiedResourceName", reservation.getResource().getName());
					request.setAttribute("reservationModifiedStart", reservation.getReservationStart());
					request.setAttribute("reservationModifiedStop", reservation.getReservationStop());
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Problem while modify the reservation", e);
					request.setAttribute("reservationModifyError", true);
					request.setAttribute("reservationModifyErrorMessage", e.getMessage());
				}
			}
		}
		List<User> userList = UserService.getInstance().getAll();
		request.setAttribute("userList", userList);
		List<ResourceType> resourceTypeList = ResourceTypeService.getInstance().getAll();
		request.setAttribute("resourceTypeList", resourceTypeList);
		return "/jsp/pages/resources/add-modify-resource.jsp";
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
	public boolean isSecured() {
		return true;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.RESERVATION;
	}

	@Override
	public String handle(HttpServletRequestDecorator request, HttpServletResponse response) throws Exception {
		List<Resource> resourceList = ResourceService.getInstance().getAll();
		request.attr("resourceList", resourceList);
		return "/jsp/pages/reservations/add-modify-reservation.jsp";
	}

}

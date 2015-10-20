package fr.mines.controller.actions.reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.Reservation;
import fr.mines.entitites.Resource;
import fr.mines.entitites.User;
import fr.mines.service.ReservationService;
import fr.mines.service.ResourceService;
import fr.mines.service.ServiceExecutionException;

public class AddReservationAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddReservationAction.class);

	private User getConnectedUser(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return user;
		} else return null;
	}

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("selectedResource") != null) {

			Long userID = getConnectedUser(request).getId();
			Long resourceID = Long.parseLong(request.getParameter("selectedResource"));

			DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date reservationStart = df.parse(request.getParameter("reservationStart"));
			Date reservationStop = df.parse(request.getParameter("reservationStop"));

			Reservation reservation = new Reservation(reservationStart, reservationStop, (User) request.getAttribute("reservationUser"),
					(Resource) request.getAttribute("reservationResource"));
			request.setAttribute("previousResource", reservation);
			LOGGER.info("Reservation {}", reservation);
			try {
				ReservationService.getInstance().create(reservation, userID, resourceID);
				request.setAttribute("reservationAdded", true);
				request.setAttribute("reservationAddedResourceName", reservation.getResource().getName());
				request.setAttribute("reservationAddedStart", reservation.getReservationStart());
				request.setAttribute("reservationAddedStop", reservation.getReservationStop());
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while adding the reservation", e);
				request.setAttribute("reservationAddError", true);
				request.setAttribute("reservationAddErrorMessage", e.getMessage());
			}
		}
		List<Resource> resourceList = ResourceService.getInstance().getAll();
		request.setAttribute("resourceList", resourceList);
		return "/jsp/pages/reservations/add-modify-reservation.jsp";
	}

	@Override
	public String getID() {
		return "add-reservation";
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

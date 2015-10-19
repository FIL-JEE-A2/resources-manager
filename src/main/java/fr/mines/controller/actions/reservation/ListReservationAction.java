package fr.mines.controller.actions.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.Reservation;
import fr.mines.service.ReservationService;

public class ListReservationAction implements FrontActionI {

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Reservation> reservationList = ReservationService.getInstance().getAll();
		request.setAttribute("reservationList", reservationList);
		return "/jsp/pages/resources/list-reservation.jsp";
	}

	@Override
	public String getID() {
		return "reservations";
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

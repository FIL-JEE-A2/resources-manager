package fr.mines.controller.actions.reservation;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.entitites.Reservation;
import fr.mines.service.ReservationService;

public class ListReservationAction implements FrontActionI {

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

	@Override
	public String handle(HttpServletRequestDecorator request, HttpServletResponse response) throws Exception {
		List<Reservation> reservationList = ReservationService.getInstance().getAll();
		request.attr("reservationList", reservationList);
		return "/jsp/pages/reservations/list-reservation.jsp";
	}

}

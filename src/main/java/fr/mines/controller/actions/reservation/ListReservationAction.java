package fr.mines.controller.actions.reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.Reservation;

public class ListReservationAction extends AbstractFrontAction {

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
		if (request.isSet("filter")) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			String resource = request.param("resource");
			Date dateStart = null;
			String dateStartOperator = null;
			if (!request.param("dateStart").equals("")) {
				dateStartOperator = request.param("dateStartOperator");
				dateStart = df.parse(request.param("dateStart"));
			}
			Date dateStop = null;
			String dateStopOperator = null;
			if (!request.param("dateStop").equals("")) {
				dateStopOperator = request.param("dateStopOperator");
				dateStop = df.parse(request.param("dateStop"));
			}
			String userFirstName = request.param("userFirstName");
			String userLastName = request.param("userLastName");
			
			request.attr("reservationList", reservationService.getReservationWithFilter(resource, dateStartOperator, dateStart, dateStopOperator, dateStop, userFirstName, userLastName));
		} else {
			request.attr("reservationList", reservationService.getAll());
		}
		return "/jsp/pages/reservations/list-reservation.jsp";
	}

}

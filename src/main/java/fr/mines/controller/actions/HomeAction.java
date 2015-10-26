package fr.mines.controller.actions;

import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.HttpServletRequestDecorator;

public class HomeAction extends AbstractFrontAction {

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		rq.attr("reservationList", reservationService.getByUser(rq.connectedUser().getId(), 3));
		rq.attr("nbReservations", reservationService.getNbByUser(rq.connectedUser().getId()));
		return "/jsp/pages/home.jsp";
	}

	@Override
	public String getID() {
		return "home";
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
		return ActionCategory.HOME;
	}

}

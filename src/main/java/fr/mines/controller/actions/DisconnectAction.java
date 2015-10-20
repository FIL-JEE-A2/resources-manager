package fr.mines.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.controller.HttpServletRequestDecorator;

public class DisconnectAction extends AbstractFrontAction {

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		rq.request().getSession(true).invalidate();
		return "/jsp/pages/disconnected.jsp";
	}

	@Override
	public String getID() {
		return "disconnect";
	}

	@Override
	public boolean isTemplateView() {
		return false;
	}

	@Override
	public boolean isSecured() {
		return false;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.NONE;
	}
}

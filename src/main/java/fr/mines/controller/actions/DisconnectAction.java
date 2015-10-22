package fr.mines.controller.actions;

import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;

public class DisconnectAction extends AbstractFrontAction {

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		rq.session().invalidate();
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
	public ActionSecurity getSecurityLevel() {
		return ActionSecurity.NONE;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.NONE;
	}
}

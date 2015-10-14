package fr.mines.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.FrontActionI;

public class DisconnectAction implements FrontActionI {

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(true).invalidate();
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
}

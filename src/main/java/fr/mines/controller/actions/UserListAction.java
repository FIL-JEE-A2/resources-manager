package fr.mines.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.FrontActionI;

public class UserListAction implements FrontActionI {

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/pages/users/list-user.jsp";
	}

	@Override
	public String getID() {
		return "users";
	}

	@Override
	public boolean isTemplateView() {
		return true;
	}

	@Override
	public boolean isSecured() {
		return true;
	}
}

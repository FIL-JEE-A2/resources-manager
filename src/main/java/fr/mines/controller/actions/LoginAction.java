package fr.mines.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.mines.controller.FrontActionI;
import fr.mines.entitites.User;

public class LoginAction implements FrontActionI {

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userID = request.getParameter("userId");
		String unauthorizedAction = request.getParameter("unauthorizedAction");
		if (Boolean.parseBoolean(unauthorizedAction)) {
			request.setAttribute("unauthorizedAction", true);
		}
		if (userID != null) {
			if ("math".equals(userID)) {//TODO : real check in DB
				request.setAttribute("loginSuccess", true);
				HttpSession session = request.getSession(true);
				session.setAttribute("user", new User("Mathieu", "THEBAUD", "mathieu.test.123456@yopmail.com", "123456", "math", "pass", true, null));
			} else {
				request.setAttribute("loginFailed", true);
			}
		}
		return "/jsp/pages/login.jsp";
	}

	@Override
	public String getID() {
		return "login";
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

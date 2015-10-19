package fr.mines.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.User;
import fr.mines.service.UserService;

public class LoginAction implements FrontActionI {

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userLogin = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String unauthorizedAction = request.getParameter("unauthorizedAction");
		if (Boolean.parseBoolean(unauthorizedAction)) {
			request.setAttribute("unauthorizedAction", true);
		}
		if (userLogin != null) {
			User connectedUser = UserService.getInstance().checkPassword(userLogin, userPassword);
			if (connectedUser != null) {
				request.setAttribute("loginSuccess", true);
				HttpSession session = request.getSession(true);
				session.setAttribute("user", connectedUser);
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

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.NONE;
	}

}

package fr.mines.controller.actions;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.entitites.User;
import fr.mines.service.UserService;

public class LoginAction extends AbstractFrontAction {

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		String userLogin = rq.param("userId");
		String userPassword = rq.param("userPassword");
		String unauthorizedAction = rq.param("unauthorizedAction");
		if (Boolean.parseBoolean(unauthorizedAction)) {
			rq.attr("unauthorizedAction", true);
		}
		if (userLogin != null) {
			User connectedUser = UserService.getInstance().checkPassword(userLogin, userPassword);
			if (connectedUser != null) {
				rq.attr("loginSuccess", true);
				HttpSession session = rq.request().getSession(true);
				session.setAttribute("user", connectedUser);
			} else {
				rq.attr("loginFailed", true);
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

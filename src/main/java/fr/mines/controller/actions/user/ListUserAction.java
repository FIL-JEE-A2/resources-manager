package fr.mines.controller.actions.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.User;
import fr.mines.service.UserService;

public class ListUserAction implements FrontActionI {

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> userList = UserService.getInstance().getAll();
		request.setAttribute("userList", userList);
		return "/jsp/pages/users/list-user.jsp";
	}

	@Override
	public String getID() {
		return "list-user";
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
		return ActionCategory.USER;
	}

}

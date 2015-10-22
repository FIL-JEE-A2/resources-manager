package fr.mines.controller.actions.user;

import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;

public class ListUserAction extends AbstractFrontAction
{

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		rq.attr("userList", userService.getAll());
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
	public ActionSecurity getSecurityLevel() {
		return ActionSecurity.ADMIN;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.USER;
	}

}

package fr.mines.controller.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.controller.HttpServletRequestDecorator;

public class HomeAction extends AbstractFrontAction {

	@Override
	public String handle(HttpServletRequestDecorator request, HttpServletResponse response) throws Exception {
		return "/jsp/common/home.jsp";
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

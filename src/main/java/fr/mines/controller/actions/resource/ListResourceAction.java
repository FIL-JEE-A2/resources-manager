package fr.mines.controller.actions.resource;

import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;

public class ListResourceAction extends AbstractFrontAction
{
	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception
	{
		rq.attr("resourceList", resourceService.getAll());
		return "/jsp/pages/resources/list-resource.jsp";
	}

	@Override
	public String getID() {
		return "resources";
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
		return ActionCategory.RESSOURCE;
	}

}

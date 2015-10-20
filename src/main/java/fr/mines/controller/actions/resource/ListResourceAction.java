package fr.mines.controller.actions.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.entitites.Resource;
import fr.mines.service.ResourceService;

public class ListResourceAction implements FrontActionI {

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		List<Resource> resourceList = ResourceService.getInstance().getAll();
		rq.attr("resourceList", resourceList);
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

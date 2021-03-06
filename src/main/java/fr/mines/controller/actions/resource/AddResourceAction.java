package fr.mines.controller.actions.resource;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.Resource;
import fr.mines.service.ServiceExecutionException;

public class AddResourceAction extends AbstractFrontAction{
	private static final Logger LOGGER = LoggerFactory.getLogger(AddResourceAction.class);

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		if (rq.isSet("resourceName")) {
			Long managerID = Long.parseLong(rq.param("managerUser"));
			Long resourceTypeID = Long.parseLong(rq.param("resourceType"));
			Resource resource = new Resource(rq.param("resourceName"),
                    rq.param("resourceDescription"), rq.param("resourceLocalisation"),
                    userService.get(managerID), resourceTypeService.get(resourceTypeID));
			rq.attr("previousResource", resource);
			try {
				resourceService.create(resource);
				rq.attr("resourceAdded", true);
				rq.attr("resourceAddedName", resource.getName());
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while adding the resource", e);
				rq.attr("resourceAddError", true);
				rq.attr("resourceAddErrorMessage", e.getMessage());
			}
		}
		rq.attr("userList", userService.getAll());
		rq.attr("resourceTypeList", resourceTypeService.getAll());
		return "/jsp/pages/resources/add-modify-resource.jsp";
	}

	@Override
	public String getID() {
		return "add-resource";
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
		return ActionCategory.RESSOURCE;
	}

}

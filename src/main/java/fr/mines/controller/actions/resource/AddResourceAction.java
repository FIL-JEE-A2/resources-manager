package fr.mines.controller.actions.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.HttpServletRequestDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;
import fr.mines.entitites.User;
import fr.mines.service.ResourceService;
import fr.mines.service.ResourceTypeService;
import fr.mines.service.ServiceExecutionException;
import fr.mines.service.UserService;

public class AddResourceAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddResourceAction.class);

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		if (rq.isSet("resourceName")) {
			Long managerID = Long.parseLong(rq.param("managerUser"));
			Long resourceTypeID = Long.parseLong(rq.param("resourceType"));
			Resource resource = new Resource(rq.param("resourceName"), rq.param("resourceDescription"),
					rq.param("resourceLocalisation"), null, null);
			rq.attr("previousResource", resource);
			try {
				ResourceService.getInstance().create(resource, managerID, resourceTypeID);
				rq.attr("resourceAdded", true);
				rq.attr("resourceAddedName", resource.getName());
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while adding the resource", e);
				rq.attr("resourceAddError", true);
				rq.attr("resourceAddErrorMessage", e.getMessage());
			}
		}
		List<User> userList = UserService.getInstance().getAll();
		rq.attr("userList", userList);
		List<ResourceType> resourceTypeList = ResourceTypeService.getInstance().getAll();
		rq.attr("resourceTypeList", resourceTypeList);
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
	public boolean isSecured() {
		return true;
	}

	@Override
	public ActionCategory getCategory() {
		return ActionCategory.RESSOURCE;
	}

}

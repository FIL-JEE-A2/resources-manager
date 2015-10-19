package fr.mines.controller.actions.resource;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.Resource;
import fr.mines.entitites.User;
import fr.mines.service.ResourceService;
import fr.mines.service.ServiceExecutionException;
import fr.mines.service.UserService;

public class AddResourceAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddResourceAction.class);

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("resourceName") != null) {
			Long managerID = Long.parseLong(request.getParameter("managerUser"));
			Resource resource = new Resource(request.getParameter("resourceName"), request.getParameter("resourceDescription"),
					request.getParameter("resourceLocalisation"), null, null);
			request.setAttribute("previousResource", resource);
			try {
				ResourceService.getInstance().create(resource, managerID);
				request.setAttribute("resourceAdded", true);
				request.setAttribute("resourceAddedName", resource.getName());
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while adding the resource", e);
				request.setAttribute("resourceAddError", true);
				request.setAttribute("resourceAddErrorMessage", e.getMessage());
			}
		}
		List<User> userList = UserService.getInstance().getAll();
		request.setAttribute("userList", userList);
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

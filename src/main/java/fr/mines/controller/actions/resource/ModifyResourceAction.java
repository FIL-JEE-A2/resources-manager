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

public class ModifyResourceAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(ModifyResourceAction.class);

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("id") != null) {
			request.setAttribute("modifyResource", true);
			Long resourceID = Long.parseLong(request.getParameter("id"));
			//Set the modified user
			LOGGER.info("Modify the resource \"{}\"", request.getParameter("id"));
			Resource resource = ResourceService.getInstance().get(resourceID);
			request.setAttribute("previousResource", resource);
			//Update in DB
			if (request.getParameter("resourceName") != null) {
				Long managerID = Long.parseLong(request.getParameter("managerUser"));
				LOGGER.info("Will update the resource \"{}\"", request.getParameter("id"));
				Resource updateResource = new Resource(request.getParameter("resourceName"), request.getParameter("resourceDescription"),
						request.getParameter("resourceLocalisation"), null, null);
				try {
					ResourceService.getInstance().update(resourceID, updateResource, managerID);
					request.setAttribute("resourceModified", true);
					request.setAttribute("resourceModifiedName", updateResource.getName());
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Problem while modify the resource", e);
					request.setAttribute("resourceModifyError", true);
					request.setAttribute("resourceModifyErrorMessage", e.getMessage());
				}
			}
		}
		List<User> userList = UserService.getInstance().getAll();
		request.setAttribute("userList", userList);
		return "/jsp/pages/resources/add-modify-resource.jsp";
	}

	@Override
	public String getID() {
		return "modify-resource";
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

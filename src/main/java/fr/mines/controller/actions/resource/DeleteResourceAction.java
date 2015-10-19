package fr.mines.controller.actions.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.Resource;
import fr.mines.service.ResourceService;
import fr.mines.service.ServiceExecutionException;

public class DeleteResourceAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteResourceAction.class);

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("id") != null) {
			Long resourceID = Long.parseLong(request.getParameter("id"));
			LOGGER.info("Will ask for the resource remove \"{}\"", request.getParameter("id"));
			Resource resource = ResourceService.getInstance().get(resourceID);
			request.setAttribute("resourceToDelete", resource);
			if (request.getParameter("delete") != null) {
				try {
					Resource removedResource = ResourceService.getInstance().remove(resourceID);
					request.setAttribute("resourceDeleted", true);
					request.setAttribute("resourceDeletedName", removedResource.getName());
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Resource can't be removed", e);
					request.setAttribute("resourceDeleteError", true);
					request.setAttribute("resourceDeleteErrorMessage", e.getMessage());
				}
			}
		}
		return "/jsp/pages/resources/delete-resource.jsp";
	}

	@Override
	public String getID() {
		return "delete-resource";
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

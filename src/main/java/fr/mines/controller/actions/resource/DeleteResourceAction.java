package fr.mines.controller.actions.resource;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.Resource;
import fr.mines.service.ServiceExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

public class DeleteResourceAction extends AbstractFrontAction
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteResourceAction.class);

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		if (rq.isSet("id")) {
			Long resourceID = Long.parseLong(rq.param("id"));
			LOGGER.info("Will ask for the resource remove \"{}\"", rq.param("id"));
			rq.attr("resourceToDelete", resourceService.get(resourceID));
			if (rq.isSet("delete")) {
				try {
					Resource removedResource = resourceService.remove(resourceID);
					rq.attr("resourceDeleted", true);
					rq.attr("resourceDeletedName", removedResource.getName());
				} catch (ServiceExecutionException e) {
					LOGGER.warn("Resource can't be removed", e);
					rq.attr("resourceDeleteError", true);
					rq.attr("resourceDeleteErrorMessage", e.getMessage());
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

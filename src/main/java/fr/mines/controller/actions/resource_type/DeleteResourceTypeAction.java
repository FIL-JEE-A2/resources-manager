package fr.mines.controller.actions.resource_type;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ServiceExecutionException;

/**
 * Created by valentin on 19/10/15.
 */
public class DeleteResourceTypeAction extends AbstractFrontAction {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DeleteResourceTypeAction.class);

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		if (!rq.isSet("id"))
			rq.attr("noId", true);
		else {
			Long id = Long.decode(rq.param("id"));
			ResourceType rt = resourceTypeService.get(id);
			if (rt == null)
				rq.attr("noId", true);
			else {
				rq.attr("resourceType", rt);

				//Modification d'un type de ressource
				if (rq.isSet("delete")) {
					LOGGER.info("Delete resource type \"{}\"", rq.param("typeName"));

					try {
						resourceTypeService.remove(id);
						rq.attr("success", true);
						rq.attr("resourceTypeName", rt.getType());
					} catch (ServiceExecutionException e) {
						LOGGER.warn("Problem while removing the resource type", e);
						rq.attr("error", true);
						rq.attr("errorMessage", e.getMessage());
					}
				} else LOGGER.info("No resource type to remove");
			}
		}

		return "/jsp/pages/resource-type/delete-resource-type.jsp";
	}

	@Override
	public String getID() {
		return "delete-resource-type";
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
		return ActionCategory.RESSOURCE_TYPE;
	}
}

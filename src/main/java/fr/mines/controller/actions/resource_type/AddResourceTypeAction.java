package fr.mines.controller.actions.resource_type;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.ActionSecurity;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ServiceExecutionException;

public class AddResourceTypeAction extends AbstractFrontAction {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AddResourceTypeAction.class);

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		rq.attr("ajout", true);

		//Ajout d'un type de ressource
		if (rq.isSet("typeName")) {
			LOGGER.info("Add resource type \"{}\"", rq.param("typeName"));

			ResourceType rt = new ResourceType(rq.param("typeName"));
			try {
				resourceTypeService.create(rt);
				rq.attr("success", true);
				rq.attr("resourceTypeName", rt.getType());
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while adding the resource type", e);
				rq.attr("error", true);
				rq.attr("resourceType", rt);
				rq.attr("errorMessage", e.getMessage());
			}
		}
		LOGGER.info("No resource type to add");
		return "/jsp/pages/resource-type/add-modify-resource-type.jsp";
	}

	@Override
	public String getID() {
		return "add-resource-type";
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

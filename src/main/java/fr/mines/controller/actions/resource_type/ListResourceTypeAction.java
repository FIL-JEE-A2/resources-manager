package fr.mines.controller.actions.resource_type;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ResourceTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by valentin on 19/10/15.
 */
public class ListResourceTypeAction implements FrontActionI {

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ResourceType> resourceTypesList = ResourceTypeService.getInstance().getAll();
        request.setAttribute("resourceTypeList", resourceTypesList);
        return "/jsp/pages/resource-type/list-resource-type.jsp";
    }

    @Override
    public String getID() {
        return "list-resource-type";
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
        return ActionCategory.RESSOURCE_TYPE;
    }

}

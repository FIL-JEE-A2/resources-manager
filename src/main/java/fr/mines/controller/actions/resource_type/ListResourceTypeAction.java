package fr.mines.controller.actions.resource_type;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.Resource;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ResourceTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by valentin on 19/10/15.
 */
public class ListResourceTypeAction extends AbstractFrontAction
{
    @Override
    public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception
    {
        rq.attr("resourceTypeList", resourceTypeService.getAll());
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

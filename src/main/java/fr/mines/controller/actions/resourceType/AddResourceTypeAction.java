package fr.mines.controller.actions.resourceType;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ResourceTypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by valentin on 19/10/15.
 */
public class AddResourceTypeAction implements FrontActionI
{

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        Object idObject = request.getAttribute("id");
        if(idObject != null && idObject instanceof String)
        {
            Long id = Long.decode((String) idObject);
            ResourceType rt = ResourceTypeService.getInstance();
        }
        return null;
    }

    @Override
    public String getID()
    {
        return "add-resource-type";
    }

    @Override
    public boolean isTemplateView()
    {
        return true;
    }

    @Override
    public boolean isSecured()
    {
        return true;
    }

    @Override
    public ActionCategory getCategory()
    {
        return ActionCategory.RESSOURCE_TYPE;
    }
}

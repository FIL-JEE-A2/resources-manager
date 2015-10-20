package fr.mines.controller.actions.resourceType;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ResourceTypeService;
import fr.mines.service.ServiceExecutionException;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by valentin on 19/10/15.
 */
public class AddResourceTypeAction implements FrontActionI
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AddResourceTypeAction.class);
    private HttpServletRequest request;

    @Override
    public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception
    {
        rq.attr("ajout", true);

        //Ajout d'un type de ressource
        if(rq.isSet("typeName"))
        {
            LOGGER.info("Add resource type \"{}\"", rq.param("typeName"));

            ResourceType rt = new ResourceType(rq.param("typeName"));
            try {
                ResourceTypeService.getInstance().create(rt);
                rq.attr("success", true);
                rq.attr("resourceTypeName", rt.getType());
            } catch (ServiceExecutionException e) {
                LOGGER.warn("Problem while adding the resource type", e);
                rq.attr("error", true);
                rq.attr("errorMessage", e.getMessage());
            }
        }
        LOGGER.info("No resource type to add");
        return "/jsp/pages/resource-type/add-modify-resource-type.jsp";
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

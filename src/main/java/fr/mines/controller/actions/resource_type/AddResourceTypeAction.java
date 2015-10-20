package fr.mines.controller.actions.resource_type;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ResourceTypeService;
import fr.mines.service.ServiceExecutionException;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 * Created by valentin on 19/10/15.
 */
public class AddResourceTypeAction implements FrontActionI
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AddResourceTypeAction.class);
    private HttpServletRequest request;

    @Override
    public String handle(HttpServletRequest rq, HttpServletResponse rp) throws Exception
    {
        request = rq;
        attr("ajout", true);

        //Ajout d'un type de ressource
        if(isSet("typeName"))
        {
            LOGGER.info("Add resource type \"{}\"", param("typeName"));

            ResourceType rt = new ResourceType(param("typeName"));
            try {
                ResourceTypeService.getInstance().create(rt);
                attr("success", true);
                attr("resourceTypeName", rt.getType());
            } catch (ServiceExecutionException e) {
                LOGGER.warn("Problem while adding the resource type", e);
                attr("error", true);
                attr("errorMessage", e.getMessage());
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

    private boolean isSet(String param)
    {
        return request.getParameter(param) != null;
    }

    private String param(String param)
    {
        return request.getParameter(param);
    }

    private Object attr(String attr)
    {
        return request.getAttribute(attr);
    }

    private void attr(String attr, Object value)
    {
        request.setAttribute(attr, value);
    }
}

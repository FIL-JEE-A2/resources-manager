package fr.mines.controller.actions.resource_type;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ResourceTypeService;
import fr.mines.service.ServiceExecutionException;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by valentin on 19/10/15.
 */
public class DeleteResourceTypeAction implements FrontActionI
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AddResourceTypeAction.class);
    private HttpServletRequest request;

    @Override
    public String handle(HttpServletRequest rq, HttpServletResponse rp) throws Exception
    {
        request = rq;

        if (!isSet("id")) attr("noId", true);
        else
        {
            Long id = Long.decode(param("id"));
            ResourceType rt = ResourceTypeService.getInstance().get(id);
            if (rt == null) attr("noId", true);
            else
            {
                attr("resourceType", rt);

                //Modification d'un type de ressource
                if (isSet("delete"))
                {
                    LOGGER.info("Delete resource type \"{}\"", param("typeName"));

                    try
                    {
                        ResourceTypeService.getInstance().remove(id);
                        attr("success", true);
                        attr("resourceTypeName", rt.getType());
                    } catch (ServiceExecutionException e)
                    {
                        LOGGER.warn("Problem while removing the resource type", e);
                        attr("error", true);
                        attr("errorMessage", e.getMessage());
                    }
                } else LOGGER.info("No resource type to remove");
            }
        }

        return "/jsp/pages/resource-type/delete-resource-type.jsp";
    }

    @Override
    public String getID()
    {
        return "delete-resource-type";
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

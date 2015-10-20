package fr.mines.controller.actions.resource_type;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import fr.mines.entitites.ResourceType;
import fr.mines.service.ResourceTypeService;
import fr.mines.service.ServiceExecutionException;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by valentin on 19/10/15.
 */
public class ModifyResourceTypeAction extends AbstractFrontAction
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ModifyResourceTypeAction.class);

    @Override
    public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception
    {
        rq.attr("modification", true);

        if (!rq.isSet("id")) rq.attr("noId", true);
        else
        {
            Long id = Long.decode(rq.param("id"));
            ResourceType rt = resourceTypeService.get(id);
            if (rt == null) rq.attr("noId", true);
            else
            {
                rq.attr("resourceType", rt);

                //Modification d'un type de ressource
                if (rq.isSet("typeName"))
                {
                    LOGGER.info("Modify resource type \"{}\"", rq.param("typeName"));

                    ResourceType updatedRt = new ResourceType(rq.param("typeName"));
                    try
                    {
                        resourceTypeService.update(id, updatedRt);
                        rq.attr("success", true);
                        rq.attr("resourceTypeName", updatedRt.getType());
                    } catch (ServiceExecutionException e)
                    {
                        LOGGER.warn("Problem while modifying the resource type", e);
                        rq.attr("error", true);
                        rq.attr("errorMessage", e.getMessage());
                    }
                } else LOGGER.info("No resource type to modify");
            }
        }

        return "/jsp/pages/resource-type/add-modify-resource-type.jsp";
    }

    @Override
    public String getID()
    {
        return "modify-resource-type";
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

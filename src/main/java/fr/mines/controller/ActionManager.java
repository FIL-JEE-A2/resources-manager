package fr.mines.controller;

import fr.mines.controller.actions.DisconnectAction;
import fr.mines.controller.actions.HomeAction;
import fr.mines.controller.actions.LoginAction;
import fr.mines.controller.actions.reservation.AddReservationAction;
import fr.mines.controller.actions.reservation.DeleteReservationAction;
import fr.mines.controller.actions.reservation.ListReservationAction;
import fr.mines.controller.actions.reservation.ModifyReservationAction;
import fr.mines.controller.actions.resource.AddResourceAction;
import fr.mines.controller.actions.resource.DeleteResourceAction;
import fr.mines.controller.actions.resource.ListResourceAction;
import fr.mines.controller.actions.resource.ModifyResourceAction;
import fr.mines.controller.actions.resource_type.AddResourceTypeAction;
import fr.mines.controller.actions.resource_type.DeleteResourceTypeAction;
import fr.mines.controller.actions.resource_type.ListResourceTypeAction;
import fr.mines.controller.actions.resource_type.ModifyResourceTypeAction;
import fr.mines.controller.actions.user.AddUserAction;
import fr.mines.controller.actions.user.DeleteUserAction;
import fr.mines.controller.actions.user.ListUserAction;
import fr.mines.controller.actions.user.ModifyUserAction;
import javassist.NotFoundException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ActionManager implements Serializable
{
    private final Map<String, FrontActionI> actions = new HashMap<>();
    
    public ActionManager()
    {
        //Login
        addAction(new LoginAction());
        addAction(new DisconnectAction());
        //Home
        addAction(new HomeAction());
        //User
        addAction(new ListUserAction());
        addAction(new AddUserAction());
        addAction(new ModifyUserAction());
        addAction(new DeleteUserAction());
        //Resource
        addAction(new ListResourceAction());
        addAction(new AddResourceAction());
        addAction(new DeleteResourceAction());
        addAction(new ModifyResourceAction());
        //ResourceType
        addAction(new ListResourceTypeAction());
        addAction(new AddResourceTypeAction());
        addAction(new ModifyResourceTypeAction());
        addAction(new DeleteResourceTypeAction());
        //Reservation
        addAction(new ListReservationAction());
        addAction(new AddReservationAction());
        addAction(new ModifyReservationAction());
        addAction(new DeleteReservationAction());
    }

    private void addAction(FrontActionI action)
    {
        actions.put(action.getID(), action);
    }

    /**
     * To get the action name from a path
     *
     * @param pathInfo the complete path to analyze
     * @return the action from the path, or null if action was not found
     */
    String getActionId(String pathInfo)
    {
        if (pathInfo != null && pathInfo.startsWith("/")) pathInfo = pathInfo.substring(1, pathInfo.length());
        return pathInfo;
    }

    public FrontActionI getAction(String pathInfo) throws NotFoundException
    {
        String actionId = getActionId(pathInfo);
        if (actionId == null) return actions.get("home");

        FrontActionI action = actions.get(actionId);
        if (action != null) return action;

        throw new NotFoundException("La page demandée est introuvable.");
    }
}
package fr.mines.controller.actions;

import fr.mines.controller.FrontActionI;
import fr.mines.service.ReservationService;
import fr.mines.service.ResourceService;
import fr.mines.service.ResourceTypeService;
import fr.mines.service.UserService;

/**
 * Created by valentin on 20/10/15.
 */
public abstract class AbstractFrontAction implements FrontActionI
{
    protected final static UserService userService = UserService.getInstance();
    protected final static ResourceService resourceService = ResourceService.getInstance();
    protected final static ResourceTypeService resourceTypeService = ResourceTypeService.getInstance();
    protected final static ReservationService reservationService = ReservationService.getInstance();
}

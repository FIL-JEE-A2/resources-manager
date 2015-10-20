package fr.mines.controller.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.mines.controller.HttpServletRequestDecorator;
import fr.mines.controller.actions.AbstractFrontAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.User;
import fr.mines.service.ServiceExecutionException;
import fr.mines.service.UserService;

public class AddUserAction extends AbstractFrontAction
{
	private static final Logger LOGGER = LoggerFactory.getLogger(AddUserAction.class);

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		if (rq.isSet("userLastName")) {
			LOGGER.info("Add a user \"{}\"", rq.param("userLastName"));
			//Add a user
			User user = new User(rq.param("userFirstName"), rq.param("userLastName"), rq.param("userEmail"),
					rq.param("userPhone"), rq.param("userLogin"), rq.param("userPassword"),
					Boolean.parseBoolean(rq.param("userIsAdmin")));
			rq.attr("previousUser", user);
			try {
				userService.create(user);
				rq.attr("userAdded", true);
				rq.attr("userAddedName", user.getFirstName() + " " + user.getLastName() + " (" + user.getLogin() + ")");
			} catch (ServiceExecutionException e) {
				LOGGER.warn("Problem while adding the user", e);
				rq.attr("userAddError", true);
				rq.attr("userAddErrorMessage", e.getMessage());
			}
		} else {
			LOGGER.info("No user add");
		}
		return "/jsp/pages/users/add-modify-user.jsp";
	}

	@Override
	public String getID() {
		return "add-user";
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
		return ActionCategory.USER;
	}

}

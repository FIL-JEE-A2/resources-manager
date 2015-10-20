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

public class DeleteUserAction extends AbstractFrontAction
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserAction.class);

	@Override
	public String handle(HttpServletRequestDecorator rq, HttpServletResponse response) throws Exception {
		if (rq.isSet("id")) {
			Long userID = Long.parseLong(rq.param("id"));
			//Set the modified user
			LOGGER.info("Will ask for the user remove the user \"{}\"", rq.param("id"));
			rq.attr("userToDelete", userService.get(userID));
			if (rq.isSet("delete")) {
				try {
					User removedUser = userService.remove(userID);
					rq.attr("userDeleted", true);
					rq.attr("userDeletedName",
							removedUser.getFirstName() + " " + removedUser.getLastName() + " (" + removedUser.getLogin() + ")");
				} catch (ServiceExecutionException e) {
					LOGGER.warn("User can't be removed", e);
					rq.attr("userDeleteError", true);
					rq.attr("userDeleteErrorMessage", e.getMessage());
				}
			}
		}
		return "/jsp/pages/users/delete-user.jsp";
	}

	@Override
	public String getID() {
		return "delete-user";
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

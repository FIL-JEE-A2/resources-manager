package fr.mines.controller.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mines.controller.ActionCategory;
import fr.mines.controller.FrontActionI;
import fr.mines.entitites.User;
import fr.mines.service.ServiceExecutionException;
import fr.mines.service.UserService;

public class DeleteUserAction implements FrontActionI {
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserAction.class);

	@Override
	public String handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getParameter("id") != null) {
			Long userID = Long.parseLong(request.getParameter("id"));
			//Set the modified user
			LOGGER.info("Will ask for the user remove the user \"{}\"", request.getParameter("id"));
			User user = UserService.getInstance().get(userID);
			request.setAttribute("userToDelete", user);
			if (request.getParameter("delete") != null) {
				try {
					User removedUser = UserService.getInstance().remove(userID);
					request.setAttribute("userDeleted", true);
					request.setAttribute("userDeletedName",
							removedUser.getFirstName() + " " + removedUser.getLastName() + " (" + removedUser.getLogin() + ")");
				} catch (ServiceExecutionException e) {
					LOGGER.warn("User can't be removed", e);
					request.setAttribute("userDeleteError", true);
					request.setAttribute("userDeleteErrorMessage", e.getMessage());
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
